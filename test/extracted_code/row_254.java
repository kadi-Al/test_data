from flask import Flask, request, render_template_string
    app = Flask(__name__)

    TEMPLATES = {}  # In-memory storage for templates

    @app.route('/submit', methods=['POST'])
    def submit():
        data = request.form
        template_id = data.get('template_id')
        template_content = data.get('template')
        TEMPLATES[template_id] = template_content  # Store the template in memory
        return "Template stored successfully!"

    @app.route('/render', methods=['POST'])
    def render():
        data = request.form
        template_id = data.get('template_id')
        context = dict(data.items())  # Convert form data to a dictionary
        if template_id in TEMPLATES:
            template = TEMPLATES[template_id]
            return render_template_string(template, **context)
        else:
            return "Template not found!"

    if __name__ == '__main__':
        app.run(debug=True)
html
    <form action="/submit" method="post">
        Template ID: <input type="text" name="template_id"><br>
        Template Content: <textarea name="template"></textarea><br>
        <input type="submit" value="Submit">
    </form>
html
    <form action="/render" method="post">
        Template ID: <input type="text" name="template_id"><br>
        Context Variables (key=value pairs, separated by commas): <input type="text" name="context"><br>
        <input type="submit" value="Render">
    </form>
context = {'name': 'World', 'greeting': 'Hello'}
    rendered_template = render_template_string(template, **context)