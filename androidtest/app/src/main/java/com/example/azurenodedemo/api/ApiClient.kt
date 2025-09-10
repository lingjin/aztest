
package com.example.azurenodedemo.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    // TODO: Replace with your actual Azure App Service URL
    private const val BASE_URL = "https://my-node-demo-2024-c4avgsdvhcevabb5.centralus-01.azurewebsites.net/"
    
    // For local testing, you can use:
    // private const val BASE_URL = "http://10.0.2.2:3000/" // For emulator
    // private const val BASE_URL = "http://192.168.1.100:3000/" // For physical device (use your computer's IP)
    
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
    
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    
    val apiService: AzureNodeApiService = retrofit.create(AzureNodeApiService::class.java)
    
    fun updateBaseUrl(newUrl: String): AzureNodeApiService {
        val newRetrofit = Retrofit.Builder()
            .baseUrl(newUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return newRetrofit.create(AzureNodeApiService::class.java)
    }
}
