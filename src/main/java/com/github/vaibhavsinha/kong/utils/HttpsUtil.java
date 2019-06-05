package com.github.vaibhavsinha.kong.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * Refers to : https://github.com/hongyangAndroid/okhttputils
 */
public class HttpsUtil
{
    private static final Logger log = LoggerFactory.getLogger( HttpsUtil.class );

    private static final String HTTPS_SSL_TYPE_TLS = "TLS";
    private static final String CERTIFICATE_TYPE_X509 = "X.509";
    private static final String LOCAL_KEY_STORE_TYPE_BKS = "BKS";

    private HttpsUtil()
    {
    }

    public static SSLParams getSslSocketFactory( InputStream[] certificates, InputStream bksFile, String password )
    {
        SSLParams sslParams = new SSLParams();
        try
        {
            TrustManager[] trustManagers = prepareTrustManager( certificates );
            KeyManager[] keyManagers = prepareKeyManager( bksFile, password );
            SSLContext sslContext = SSLContext.getInstance( HTTPS_SSL_TYPE_TLS );
            X509TrustManager trustManager;
            if ( trustManagers != null )
            {
                trustManager = new MyTrustManager( chooseTrustManager( trustManagers ) );
            }
            else
            {
                trustManager = new UnSafeTrustManager();
            }
            sslContext.init( keyManagers, new TrustManager[] { trustManager }, null );
            sslParams.sslSocketFactory = sslContext.getSocketFactory();
            sslParams.trustManager = trustManager;
            return sslParams;
        }
        catch ( NoSuchAlgorithmException | KeyManagementException | KeyStoreException e )
        {
            throw new AssertionError( e );
        }
    }

    private static TrustManager[] prepareTrustManager( InputStream... certificates )
    {
        if ( certificates == null || certificates.length <= 0 ) return new TrustManager[0];
        try
        {

            CertificateFactory certificateFactory = CertificateFactory.getInstance( CERTIFICATE_TYPE_X509 );
            KeyStore keyStore = KeyStore.getInstance( KeyStore.getDefaultType() );
            keyStore.load( null );
            int index = 0;
            for ( InputStream certificate : certificates )
            {
                String certificateAlias = Integer.toString( index++ );
                keyStore.setCertificateEntry( certificateAlias, certificateFactory.generateCertificate( certificate ) );
                closeSilently( certificate );
            }
            TrustManagerFactory trustManagerFactory = TrustManagerFactory
                    .getInstance( TrustManagerFactory.getDefaultAlgorithm() );
            trustManagerFactory.init( keyStore );

            return trustManagerFactory.getTrustManagers();
        }
        catch ( Exception e )
        {
            log.error( "Unable to prepare trust manager", e );
        }
        return new TrustManager[0];
    }

    private static void closeSilently( InputStream certificate )
    {
        try
        {
            if ( certificate != null ) certificate.close();
        }
        catch ( IOException e )
        {
            //ignore
        }
    }

    private static KeyManager[] prepareKeyManager( InputStream bksFile, String password )
    {
        try
        {
            if ( bksFile == null || password == null ) return new KeyManager[0];

            KeyStore clientKeyStore = KeyStore.getInstance( LOCAL_KEY_STORE_TYPE_BKS );
            clientKeyStore.load( bksFile, password.toCharArray() );
            KeyManagerFactory keyManagerFactory = KeyManagerFactory
                    .getInstance( KeyManagerFactory.getDefaultAlgorithm() );
            keyManagerFactory.init( clientKeyStore, password.toCharArray() );
            return keyManagerFactory.getKeyManagers();

        }
        catch ( Exception e )
        {
            log.error( "Unable to prepare KeyManager", e );
        }
        return new KeyManager[0];
    }

    private static X509TrustManager chooseTrustManager( TrustManager[] trustManagers )
    {
        for ( TrustManager trustManager : trustManagers )
        {
            if ( trustManager instanceof X509TrustManager )
            {
                return (X509TrustManager) trustManager;
            }
        }
        return null;
    }

    public static class SSLParams
    {
        private SSLSocketFactory sslSocketFactory;
        private X509TrustManager trustManager;

        public SSLSocketFactory getSslSocketFactory()
        {
            return sslSocketFactory;
        }

        public X509TrustManager getTrustManager()
        {
            return trustManager;
        }
    }

    @SuppressWarnings( "squid:S4424" )
    private static class UnSafeTrustManager implements X509TrustManager
    {
        @Override
        public void checkClientTrusted( X509Certificate[] chain, String authType )
        {
            // void implementation
        }

        @Override
        public void checkServerTrusted( X509Certificate[] chain, String authType )
        {
            // void implementation
        }

        @Override
        public X509Certificate[] getAcceptedIssuers()
        {
            return new java.security.cert.X509Certificate[0];
        }
    }

    @SuppressWarnings( "squid:S4424" )
    private static class MyTrustManager implements X509TrustManager
    {
        private X509TrustManager defaultTrustManager;
        private X509TrustManager localTrustManager;

        MyTrustManager( X509TrustManager localTrustManager ) throws NoSuchAlgorithmException, KeyStoreException
        {
            TrustManagerFactory var4 = TrustManagerFactory.getInstance( TrustManagerFactory.getDefaultAlgorithm() );
            var4.init( (KeyStore) null );
            defaultTrustManager = chooseTrustManager( var4.getTrustManagers() );
            this.localTrustManager = localTrustManager;
        }

        @Override
        public void checkClientTrusted( X509Certificate[] chain, String authType )
        {
            // void implementation
        }

        @Override
        public void checkServerTrusted( X509Certificate[] chain, String authType ) throws CertificateException
        {
            try
            {
                defaultTrustManager.checkServerTrusted( chain, authType );
            }
            catch ( CertificateException ce )
            {
                localTrustManager.checkServerTrusted( chain, authType );
            }
        }

        @Override
        public X509Certificate[] getAcceptedIssuers()
        {
            return new X509Certificate[0];
        }
    }
}
