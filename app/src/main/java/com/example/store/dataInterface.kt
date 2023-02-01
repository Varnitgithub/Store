package com.example.store

import retrofit2.Call
import retrofit2.http.*

interface dataInterface {
    @GET("products")
    fun getdata():Call<ArrayList<dataitemItem>>

    @GET("products/{id}")
    fun idgetdata(@Path("id")id:Int):Call<ArrayList<dataitemItem>>

    @POST("products")
    fun postdata(@Body dataitemItem: dataitemItem):Call<ArrayList<dataitemItem>>

    @DELETE("products/{id}")
    fun deletedata(@Path("id")id:Int):Call<Unit>

    @PUT("products/{id}")
    fun putdata(@Path("id")id:Int,@Body dataitemItem: dataitemItem):Call<ArrayList<dataitemItem>>

    @PATCH("products/{id}")
    fun patchdata(@Path("id")id:Int,@Body dataitem: dataitem):Call<ArrayList<dataitemItem>>
}