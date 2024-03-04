const express = require('express');
const cors = require('cors');
const { readdirSync } = require('fs');
const dotenv = require('dotenv');

// Load environment variables from .env file
dotenv.config();

// Initialize Express application
const app = express();

// Set the port for the server to listen on
const PORT = process.env.PORT || 5001;

// Middleware to parse JSON requests
app.use(express.json());

// Middleware to enable CORS
app.use(cors());

// Dynamically load and use routes from the 'routes' directory
readdirSync('./routes').map((routeFile) => {
    // Mount routes under the '/api/v1' prefix
    app.use("/api/v1", require(`./routes/${routeFile}`));
});

/**
 * Function to initialize the server.
 * Starts the Express application to listen on the specified port.
 * Logs a message to indicate that the server is running and listening on the specified port.
 */
const server = () => {
    app.listen(PORT, () => {
        console.log("Server listening on port", PORT);
    });
};

// Start the server
server();
