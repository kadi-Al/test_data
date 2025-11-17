script
const express = require('express');
const app = express();

app.use((err, req, res, next) => {
  if (err) {
    console.error(err.stack); // Log the error for debugging purposes
    res.status(500).send({ error: 'Internal Server Error', stack: err.stack });
  } else {
    next();
  }
});

app.get('/', (req, res) => {
  throw new Error('This is a test error'); // Trigger an error to see the middleware in action
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});
from flask import Flask, request, jsonify

app = Flask(__name__)

@app.before_first_request
def initialize():
    raise Exception("This is a test error") # Trigger an error to see the middleware in action

@app.errorhandler(Exception)
def handle_exception(e):
    print(str(e))  # Log the error for debugging purposes
    return jsonify({
        "success": False,
        "message": str(e),
        "stack_trace": str(e)
    }), 500

@app.route('/')
def index():
    pass

if __name__ == '__main__':
    app.run()
from django.http import JsonResponse
import logging

logger = logging.getLogger(__name__)

class ExceptionMiddleware:
    def __init__(self, get_response):
        self.get_response = get_response

    def __call__(self, request):
        response = self.get_response(request)
        return response

    def process_exception(self, request, exception):
        logger.error(str(exception), exc_info=True)  # Log the error for debugging purposes
        return JsonResponse({
            "success": False,
            "message": str(exception),
            "stack_trace": str(exception)
        }, status=500)
ruby
class ApplicationController < ActionController::Base
  rescue_from Exception, with: :handle_exception

  private

  def handle_exception(e)
    Rails.logger.error e.full_message # Log the error for debugging purposes
    render json: { error: 'Internal Server Error', message: e.message, stack_trace: e.backtrace }, status: :internal_server_error
  end
end
sharp
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.Logging;
using System;
using System.Threading.Tasks;

public class Startup
{
    public void Configure(IApplicationBuilder app, IWebHostEnvironment env, ILogger<Startup> logger)
    {
        app.UseExceptionHandler(errorApp =>
        {
            errorApp.Run(async context =>
            {
                context.Response.StatusCode = StatusCodes.Status500InternalServerError;
                context.Response.ContentType = "application/json";

                var exceptionHandlerPathFeature = context.Features.Get<Microsoft.AspNetCore.Diagnostics.IExceptionHandlerPathFeature>();
                if (exceptionHandlerPathFeature?.Error != null)
                {
                    logger.LogError($"Path: {exceptionHandlerPathFeature.Path}, Error: {exceptionHandlerPathFeature.Error}");
                    await context.Response.WriteAsync(new
                    {
                        error = "Internal Server Error",
                        message = exceptionHandlerPathFeature.Error.Message,
                        stack_trace = exceptionHandlerPathFeature.Error.StackTrace
                    }.ToString());
                }
            });
        });

        app.UseRouting();

        app.UseEndpoints(endpoints =>
        {
            endpoints.MapGet("/", async context =>
            {
                throw new Exception("This is a test error"); // Trigger an error to see the middleware in action
            });
        });
    }
}