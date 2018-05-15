package com.github.vaibhavsinha.kong.internal.admin;

import com.github.vaibhavsinha.kong.model.admin.service.RouteList;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitRouteService
{
    @DELETE("routes/{id}")
    Call<Void> deleteRoute(@Path("id") String nameOrId);

    @GET("routes")
    Call<RouteList> listRoutes();
}
