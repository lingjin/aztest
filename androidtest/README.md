# Azure Node Demo - Android Client

This Android app demonstrates how to connect to and test the Azure Node.js service endpoints.

## Features

- Test all API endpoints (GET /, /health, /api/info, /api/data, POST /api/echo)
- Configurable base URL for easy switching between local and Azure deployment
- Pretty-printed JSON responses
- Error handling and connection diagnostics

## Setup and Usage

### 1. Open the Project

Open the `androidtest` folder in Android Studio.

### 2. Configure the Base URL

In `app/src/main/java/com/example/azurenodedemo/api/ApiClient.kt`, update the BASE_URL:

```kotlin
// For Azure deployment:
private const val BASE_URL = "https://your-app-name.azurewebsites.net/"

// For local testing with emulator:
private const val BASE_URL = "http://10.0.2.2:3000/"

// For local testing with physical device:
private const val BASE_URL = "http://YOUR_COMPUTER_IP:3000/"
```

### 3. Build and Run

1. Connect an Android device or start an emulator
2. Click "Run" in Android Studio
3. The app will install and launch

### 4. Using the App

1. **Update Base URL**: Enter your Azure service URL in the text field and click "Update Base URL"
2. **Test Endpoints**: Click any of the buttons to test different API endpoints
3. **View Responses**: The response will appear in the bottom section with formatted JSON

## Testing Different Scenarios

### Local Development
1. Start your Node.js server locally: `npm start` in the `azuresvr` folder
2. Use the appropriate local URL based on your testing device
3. Make sure your computer and Android device are on the same network

### Azure Deployment
1. Deploy your Node.js service to Azure
2. Update the base URL to your Azure app URL
3. Test all endpoints to ensure proper connectivity

## Troubleshooting

### Connection Refused
- Verify the server is running
- Check the base URL is correct
- For local testing, ensure firewall allows connections

### Network Security Error
- The app allows cleartext traffic for testing
- For production, use HTTPS only

### Timeout Errors
- Check your internet connection
- Verify the Azure service is running
- Try increasing timeout values in ApiClient.kt

## Dependencies

- Retrofit 2.9.0 - HTTP client
- OkHttp 4.12.0 - HTTP & HTTP/2 client
- Gson 2.9.0 - JSON serialization
- Coroutines 1.7.3 - Asynchronous programming
- Material Components - UI components

## Minimum Requirements

- Android API 24 (Android 7.0 Nougat)
- Target SDK: 34 (Android 14)
