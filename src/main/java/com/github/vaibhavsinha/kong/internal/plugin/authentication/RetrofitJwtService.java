package com.github.vaibhavsinha.kong.internal.plugin.authentication;

import com.github.vaibhavsinha.kong.model.plugin.authentication.jwt.JwtCredential;
import com.github.vaibhavsinha.kong.model.plugin.authentication.jwt.JwtCredentialList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by vaibhav on 16/06/17.
 *
 * Updated by dvilela on 17/10/17.
 */
public interface RetrofitJwtService {

    @POST("consumers/{consumer}/jwt")
    Call<JwtCredential> addCredentials(@Path("consumer") String consumerIdOrUsername, @Body JwtCredential request);

    @DELETE("consumers/{consumer}/jwt/{id}")
    Call<Void> deleteCredentials(@Path("consumer") String consumerIdOrUsername, @Path("id") String id);

    @GET("consumers/{consumer}/jwt")
    Call<JwtCredentialList> listCredentials(@Path("consumer") String consumerIdOrUsername, @Query("size") Long size, @Query("offset") String offset);
}
