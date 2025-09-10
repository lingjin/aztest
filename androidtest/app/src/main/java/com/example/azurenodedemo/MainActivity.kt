package com.example.azurenodedemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.azurenodedemo.api.ApiClient
import com.example.azurenodedemo.api.AzureNodeApiService
import com.example.azurenodedemo.databinding.ActivityMainBinding
import com.example.azurenodedemo.model.EchoRequest
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var apiService: AzureNodeApiService
    private val gson = GsonBuilder().setPrettyPrinting().create()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        apiService = ApiClient.apiService
        setupClickListeners()
    }
    
    private fun setupClickListeners() {
        binding.buttonUpdateUrl.setOnClickListener {
            val newUrl = binding.editTextBaseUrl.text.toString().trim()
            if (newUrl.isNotEmpty()) {
                apiService = ApiClient.updateBaseUrl(ensureTrailingSlash(newUrl))
                Toast.makeText(this, "Base URL updated", Toast.LENGTH_SHORT).show()
            }
        }
        
        binding.buttonGetWelcome.setOnClickListener {
            makeApiCall("GET /") {
                apiService.getWelcome()
            }
        }
        
        binding.buttonGetHealth.setOnClickListener {
            makeApiCall("GET /health") {
                apiService.getHealth()
            }
        }
        
        binding.buttonGetInfo.setOnClickListener {
            makeApiCall("GET /api/info") {
                apiService.getInfo()
            }
        }
        
        binding.buttonGetData.setOnClickListener {
            makeApiCall("GET /api/data") {
                apiService.getData()
            }
        }
        
        binding.buttonPostEcho.setOnClickListener {
            val echoRequest = EchoRequest(
                message = "Hello from Android!",
                testData = "This is test data from the Android client"
            )
            makeApiCall("POST /api/echo") {
                apiService.postEcho(echoRequest)
            }
        }
        
        binding.buttonClearResponse.setOnClickListener {
            binding.textViewResponse.text = "Response will appear here..."
        }
    }
    
    private fun <T> makeApiCall(endpoint: String, apiCall: suspend () -> Response<T>) {
        lifecycleScope.launch {
            try {
                binding.textViewResponse.text = "Loading..."
                
                val response = apiCall()
                
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val formattedResponse = gson.toJson(responseBody)
                    binding.textViewResponse.text = """
                        |Endpoint: $endpoint
                        |Status: ${response.code()} ${response.message()}
                        |
                        |Response:
                        |$formattedResponse
                    """.trimMargin()
                } else {
                    binding.textViewResponse.text = """
                        |Endpoint: $endpoint
                        |Error: ${response.code()} ${response.message()}
                        |Body: ${response.errorBody()?.string()}
                    """.trimMargin()
                }
            } catch (e: Exception) {
                binding.textViewResponse.text = """
                    |Endpoint: $endpoint
                    |Error: ${e.javaClass.simpleName}
                    |Message: ${e.message}
                    |
                    |Make sure:
                    |1. Your server is running
                    |2. The base URL is correct
                    |3. You have internet permission
                    |4. For local testing, use correct IP address
                """.trimMargin()
            }
        }
    }
    
    private fun ensureTrailingSlash(url: String): String {
        return if (url.endsWith("/")) url else "$url/"
    }
}
