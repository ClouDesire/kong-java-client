package com.github.vaibhavsinha.kong.internal.admin;

import com.github.vaibhavsinha.kong.model.admin.api.Api;
import com.github.vaibhavsinha.kong.model.admin.api.ApiList;
import com.github.vaibhavsinha.kong.model.admin.plugin.PluginList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by vaibhav on 12/06/17.
 */
public interface RetrofitApiService {

    @POST("apis/")
    Call<Api> createApi(@Body Api request);

    @GET("apis/{id}")
    Call<Api> getApi(@Path("id") String nameOrId);

    @PATCH("apis/{id}")
    Call<Api> updateApi(@Path("id") String nameOrId, @Body Api request);

    /**
     * @deprecated use {@link #createApi(Api)} or {@link #updateApi(String, Api)}
     */
    @Deprecated
    @PUT("apis/")
    Call<Api> createOrUpdateApi(@Body Api request);

    @DELETE("apis/{id}")
    Call<Void> deleteApi(@Path("id") String nameOrId);

    @GET("apis/")
    Call<ApiList> listApis(@Query("id") String id, @Query("upstream_url") String upstreamUrl, @Query("name") String name, @Query("retries") Long retries,  @Query("size") Long size, @Query("offset") String offset);


    @GET("apis/{id}/plugins")
    Call<PluginList> listApiPlugins(@Path("id") String nameOrId);
}
