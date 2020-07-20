package com.mochamadahya.detikcom.service

import android.widget.Toast
import com.mochamadahya.detikcom.model.ResponseNews
import retrofit2.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

object RetrofitBuilder {

    private val client: OkHttpClient = OkHttpClient.Builder().build()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun getService() = retrofit.create(TopHeadLines::class.java)
}

interface TopHeadLines{
    @Headers("Authorization: 02cbb4b6f57f409e98ac23ddd9b1174b")
    @GET("v2/top-headlines?country=id")
    fun feachHeadLines(): Call<ResponseNews>


}