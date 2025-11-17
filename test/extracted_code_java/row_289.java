from flask import Flask, request, render_template_string

   app = Flask(__name__)

   # Store templates temporarily in memory (for demonstration purposes)
   TEMPLATES = {}

   @app.route('/submit-template', methods=['POST'])
   def submit_template():
       template_content = request.form.get('template')
       if not template_content:
           return "Template content is required", 400
       # Store the template in a dictionary with a unique key
       TEMPLATES['user_template'] = template_content
       return "Template submitted successfully", 200

   @app.route('/render-template', methods=['GET'])
   def render_template():
       if 'user_template' not in TEMPLATES:
           return "No template found", 404
       # Render the template using Jinja2 with access to Flask application context
       rendered = render_template_string(TEMPLATES['user_template'], app=app)
       return rendered, 200

   if __name__ == '__main__':
       app.run(debug=True)