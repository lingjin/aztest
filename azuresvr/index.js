const express = require('express');
const cors = require('cors');

const app = express();
const PORT = process.env.PORT || 3000;

// Enable CORS for all origins (configure appropriately for production)
app.use(cors());

// Parse JSON bodies
app.use(express.json());

// Root endpoint
app.get('/', (req, res) => {
  res.json({
    message: 'Welcome to Azure Node.js Demo API',
    timestamp: new Date().toISOString(),
    endpoints: {
      health: '/health',
      info: '/api/info',
      echo: '/api/echo',
      data: '/api/data'
    }
  });
});

// Health check endpoint
app.get('/health', (req, res) => {
  res.json({
    status: 'healthy',
    uptime: process.uptime(),
    timestamp: new Date().toISOString()
  });
});

// Info endpoint
app.get('/api/info', (req, res) => {
  res.json({
    name: 'Azure Node.js Demo',
    version: '1.0.0',
    node: process.version,
    platform: process.platform
  });
});

// Echo endpoint (for testing POST requests from Android)
app.post('/api/echo', (req, res) => {
  res.json({
    message: 'Echo response',
    receivedData: req.body,
    timestamp: new Date().toISOString()
  });
});

// Sample data endpoint
app.get('/api/data', (req, res) => {
  const sampleData = [
    { id: 1, name: 'Item 1', value: 100 },
    { id: 2, name: 'Item 2', value: 200 },
    { id: 3, name: 'Item 3', value: 300 }
  ];
  res.json({
    data: sampleData,
    count: sampleData.length,
    timestamp: new Date().toISOString()
  });
});

// Error handling middleware
app.use((err, req, res, next) => {
  console.error(err.stack);
  res.status(500).json({
    error: 'Something went wrong!',
    message: err.message
  });
});

// 404 handler
app.use((req, res) => {
  res.status(404).json({
    error: 'Not Found',
    message: `Cannot ${req.method} ${req.url}`
  });
});

// Start server
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
  console.log(`Local URL: http://localhost:${PORT}`);
});
