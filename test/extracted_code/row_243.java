script
const express = require('express');
const app = express();

// Middleware to log the entire request body
app.use((req, res, next) => {
  const method = req.method;
  const url = req.originalUrl;
  const headers = req.headers;
  const body = req.body;

  console.log(`Method: ${method}`);
  console.log(`URL: ${url}`);
  console.log(`Headers:`, headers);
  console.log(`Body:`, body);

  next();
});

app.post('/endpoint', (req, res) => {
  // Your endpoint logic here
  res.send('Hello World!');
});

app.listen(3000, () => {
  console.log('Server is running on port 3000');
});
from flask import Flask, request, jsonify
import logging

app = Flask(__name__)

# Configure logging to include the whole request body
logging.basicConfig(level=logging.DEBUG)
logger = logging.getLogger('werkzeug')  # Use werkzeug's logger for capturing requests

@app.route('/endpoint', methods=['POST'])
def endpoint():
    req_body = request.get_json()
    logger.debug(f"Request Body: {req_body}")
    return jsonify({"message": "Hello World!"})

if __name__ == '__main__':
    app.run(port=5000)
sharp
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Http;
using System.IO;
using System.Text;
using System.Threading.Tasks;

public class RequestResponseLoggingMiddleware
{
    private readonly RequestDelegate _next;

    public RequestResponseLoggingMiddleware(RequestDelegate next)
    {
        _next = next;
    }

    public async Task InvokeAsync(HttpContext context)
    {
        // Log the request body
        var originalBodyStream = context.Response.Body;
        using (var responseBody = new MemoryStream())
        {
            context.Response.Body = responseBody;

            // Call the next middleware in the pipeline
            await _next(context);

            // Log the response body
            var responseBodyContent = await new StreamReader(context.Response.Body).ReadToEndAsync();
            context.Response.Body.Seek(0, SeekOrigin.Begin);

            // Log the request and response details here as needed
            Console.WriteLine($"Request: {context.Request.Method} {context.Request.Path}{context.Request.QueryString}");
            Console.WriteLine($"Request Body: {await new StreamReader(context.Request.Body).ReadToEndAsync()}");
            Console.WriteLine($"Response Body: {responseBodyContent}");

            await responseBody.CopyToAsync(originalBodyStream);
        }
    }
}

public class Startup
{
    public void Configure(IApplicationBuilder app)
    {
        app.UseMiddleware<RequestResponseLoggingMiddleware>();
        // Other middleware and routes
    }
}
go
package main

import (
    "bytes"
    "encoding/json"
    "io/ioutil"
    "net/http"

    "github.com/sirupsen/logrus"
)

func main() {
    http.HandleFunc("/endpoint", func(w http.ResponseWriter, r *http.Request) {
        body, err := ioutil.ReadAll(r.Body)
        if err != nil {
            logrus.Errorf("Failed to read request body: %v", err)
            return
        }
        r.Body = ioutil.NopCloser(bytes.NewBuffer(body))

        logEntry := logrus.WithFields(logrus.Fields{
            "method":   r.Method,
            "url":      r.URL.String(),
            "headers":  r.Header,
            "body":     string(body),
        })

        logEntry.Info("Request received")

        // Your endpoint logic here
    })

    http.ListenAndServe(":8080", nil)
}