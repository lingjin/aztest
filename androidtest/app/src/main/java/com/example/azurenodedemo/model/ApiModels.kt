package com.example.azurenodedemo.model

import com.google.gson.annotations.SerializedName

// Root endpoint response
data class WelcomeResponse(
    val message: String,
    val timestamp: String,
    val endpoints: EndpointsInfo
)

data class EndpointsInfo(
    val health: String,
    val info: String,
    val echo: String,
    val data: String
)

// Health check response
data class HealthResponse(
    val status: String,
    val uptime: Double,
    val timestamp: String
)

// Info endpoint response
data class InfoResponse(
    val name: String,
    val version: String,
    val node: String,
    val platform: String
)

// Echo endpoint response
data class EchoResponse(
    val message: String,
    val receivedData: Any?,
    val timestamp: String
)

// Data endpoint response
data class DataResponse(
    val data: List<DataItem>,
    val count: Int,
    val timestamp: String
)

data class DataItem(
    val id: Int,
    val name: String,
    val value: Int
)

// Request body for echo endpoint
data class EchoRequest(
    val message: String,
    val testData: String
)
