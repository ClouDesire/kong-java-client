package com.github.vaibhavsinha.kong.internal.admin;

import com.github.vaibhavsinha.kong.model.admin.plugin.Plugin;
import com.github.vaibhavsinha.kong.model.admin.service.Route;
import com.github.vaibhavsinha.kong.model.admin.service.Service;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitServiceService
{
    @POST("services")
    Call<Service> createService(@Body Service request);

    @GET("services/{id}")
    Call<Service> getService(@Path("id") String nameOrId);

    @POST("services/{service}/plugins")
    Call<Plugin> createServicePlugin(@Path("service") String service, @Body Plugin request);

    @POST("services/{service}/routes")
    Call<Route> createServiceRoute(@Path("service") String service, @Body Route request);
}
