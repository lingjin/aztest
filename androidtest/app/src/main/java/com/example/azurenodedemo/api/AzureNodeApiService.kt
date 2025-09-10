package com.example.azurenodedemo.api

import com.example.azurenodedemo.model.*
import retrofit2.Response
import retrofit2.http.*

interface AzureNodeApiService {
    
    @GET("/")
    suspend fun getWelcome(): Response<WelcomeResponse>
    
    @GET("/health")
    suspend fun getHealth(): Response<HealthResponse>
    
    @GET("/api/info")
    suspend fun getInfo(): Response<InfoResponse>
    
    @POST("/api/echo")
    suspend fun postEcho(@Body request: EchoRequest): Response<EchoResponse>
    
    @GET("/api/data")
    suspend fun getData(): Response<DataResponse>
}
