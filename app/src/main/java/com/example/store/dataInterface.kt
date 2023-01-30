package com.example.store

import retrofit2.Call
import retrofit2.http.GET

interface dataInterface {
    @GET(value = "products")
    fun getdata():Call<dataitem>
}