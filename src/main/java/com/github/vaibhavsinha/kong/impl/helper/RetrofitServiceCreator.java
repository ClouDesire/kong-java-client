package com.github.vaibhavsinha.kong.impl.helper;


import com.github.vaibhavsinha.kong.utils.HttpsUtil;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import java.lang.reflect.Proxy;

public class RetrofitServiceCreator {

    private Retrofit retrofit;

	public RetrofitServiceCreator(String baseUrl) {

		retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(initOkHttpClient(baseUrl.toLowerCase().startsWith("https"))) // support https
				.addConverterFactory(CustomGsonConverterFactory.create()) // replace GsonConverterFactory
				.build();

	}

    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> serviceInterface, Class<?> retrofitServiceInterface) {
        Object proxied = retrofit.create(retrofitServiceInterface);
        return (T) Proxy.newProxyInstance(
                RetrofitServiceCreator.class.getClassLoader(),
                new Class[] { serviceInterface },
                new RetrofitBodyExtractorInvocationHandler(proxied));
    }

    public <T> T createRetrofitService(Class<T> retrofitServiceInterface) {
        return retrofit.create(retrofitServiceInterface);
    }

    // -------------------------------------------------------------------

    private OkHttpClient initOkHttpClient(boolean supportHttps) {

        if (supportHttps) {
            HttpsUtil.SSLParams sslParams = HttpsUtil.getSslSocketFactory(null, null, null);
            return new OkHttpClient.Builder()
                    .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                    .build();
        }

        return new OkHttpClient.Builder().build();
    }
}
