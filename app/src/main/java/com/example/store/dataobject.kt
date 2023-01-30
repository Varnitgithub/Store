package com.example.store

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object dataobject {

val obj by lazy {
    Retrofit.Builder().baseUrl("https://fakestoreapi.com/").addConverterFactory(GsonConverterFactory.create()).build()
}
    val newobj by lazy {
        obj.create(dataInterface::class.java)
    }
}

