apache
<IfModule mod_ssl.c>
    LogLevel alert rewrite:trace6
</IfModule>

<IfModule !mod_authz_core.c>
    LogLevel alert rewrite:trace6
</IfModule>
nginx
http {
    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  /var/log/nginx/access.log  main;
    error_log   /var/log/nginx/error.log debug;
}
script
app.use((err, req, res, next) => {
    console.error(err.stack);
    res.status(500).send('Something broke!');
});
ruby
class ApplicationController < ActionController::Base
  rescue_from Exception, with: :render_500

  def render_500(exception)
    logger.error(exception.full_message)
    respond_to do |format|
      format.html { render template: 'errors/internal_server_error', layout: 'layouts/application', status: 500 }
      # Add other formats like json, etc.
    end
  end
end
@app.errorhandler(Exception)
def handle_exception(e):
    app.logger.error(f'Unhandled exception: {str(e)}')
    return 'An unexpected error occurred.', 500
php
public function render($request, Exception $exception)
{
    if ($exception instanceof \Exception) {
        Log::error($exception->getMessage() . ' - Stack Trace: ' . $exception->getTraceAsString());
    }

    return parent::render($request, $exception);
}
sharp
if (env.IsDevelopment())
{
    app.UseDeveloperExceptionPage();
}
else
{
    app.UseExceptionHandler("/Home/Error");
    app.UseHsts();
}
sharp
public IActionResult Error()
{
    var exceptionHandlerPathFeature = HttpContext.Features.Get<IExceptionHandlerPathFeature>();
    if (exceptionHandlerPathFeature?.Error is Exception ex)
    {
        // Log the error and stack trace
        Console.WriteLine(ex.ToString());
    }
    return View();
}