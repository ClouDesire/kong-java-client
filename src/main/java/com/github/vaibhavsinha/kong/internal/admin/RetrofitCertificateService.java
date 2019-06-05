package com.github.vaibhavsinha.kong.internal.admin;

import com.github.vaibhavsinha.kong.model.admin.certificate.Certificate;
import com.github.vaibhavsinha.kong.model.admin.certificate.CertificateList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by vaibhav on 12/06/17.
 */
public interface RetrofitCertificateService {

    @POST("certificates/")
    Call<Certificate> createCertificate(@Body Certificate request);

    @GET("certificates/{id}")
    Call<Certificate> getCertificate(@Path("id") String sniOrId);

    @PATCH("certificates/{id}")
    Call<Certificate> updateCertificate(@Path("id") String sniOrId, @Body Certificate request);

    @PUT("certificates/")
    Call<Certificate> createOrUpdateCertificate(@Body Certificate request);

    @DELETE("certificates/{id}")
    Call<Void> deleteCertificate(@Path("id") String sniOrId);

    @GET("certificates/")
    Call<CertificateList> listCertificates();

}
