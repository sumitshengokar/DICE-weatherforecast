# Weather Forecast Spring Boot Application

This Spring Boot application integrates with the Rapid API to provide weather forecast information for cities. The application exposes RESTful APIs to retrieve forecast summaries and hourly details for a given city.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Configuration](#configuration)
- [Endpoints](#endpoints)
- [Build and Run](#build-and-run)
- [Usage](#usage)
- [Exception Handling](#exception-handling)
- [Authentication Header](#Authentication-Headers)

## Prerequisites

Before running the application, make sure you have the following installed:

- Java 8 or higher
- Maven
- Git

## Configuration

1. Clone the repository:
   git clone https://github.com/yourusername/weather-forecast-app.git

2. Open src/main/resources/application.properties and replace YOUR_RAPID_API_KEY with your actual Rapid API key.

## Endpoints

The application exposes the following RESTful APIs:
a. Get Forecast Summary:
Endpoint: /weather/forecast/city/{city}
Method: GET
Path Parameter: city (e.g., /weather/forecast/city/{city=Berlin})

b.  Get Hourly Forecast:
Endpoint: /weather/forecast/hourly/{city}
Method: GET
Path Parameter: city (e.g., /weather/forecast/hourly/{city=London})

## BUILD AND RUN

mvn clean install

## USAGE

# Get Forecast Summary
curl http://localhost:8080/weather/forecast-summary?cityName=Berlin

# Get Hourly Forecast
curl http://localhost:8080/weather/hourly-forecast?cityName=London

This Spring Boot application utilizes a header-based authentication mechanism for secure access to its APIs. Authentication is based on two mandatory headers: `client-id` and `client-secret`. These headers must be included in each API request for proper authorization.

## Exception Handling
The application handles exceptions, including a custom CityNotFoundException for cases where the provided city is not found (returns a 404 status code).
Other than that normal Try Catch Based Exception Handling with proper Error Messages throw

## Authentication Headers

- `client-id`: Any randomly generated client identifier.
- `client-secret`: A randomly generated client secret.

 # Authentication Process

The application uses a custom authentication filter to validate the presence and non-empty nature of the `client-id` and `client-secret` headers. The filter extends the standard Servlet Filter and is responsible for intercepting incoming requests, extracting the headers, and performing basic validation.

 # Basic Validation Rules

- Both `client-id` and `client-secret` headers must be present in the request.
- Both headers must not be null or empty.

 # Handling Unauthorized Requests

If the authentication fails (headers are missing or empty), the application returns a `401 Unauthorized` status code along with a JSON response containing the error details.

```json
{
  "errorcode": "Unauthorized",
  "message": "Client ID and Client Secret headers are required for authentication.",
  "statusCode": "401"
}





