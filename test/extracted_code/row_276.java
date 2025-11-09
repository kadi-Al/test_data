bash
   pip install flask requests
from flask import Flask, request, redirect, url_for, send_file, render_template
import requests
import os

app = Flask(__name__)
UPLOAD_FOLDER = 'uploads'
if not os.path.exists(UPLOAD_FOLDER):
    os.makedirs(UPLOAD_FOLDER)

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/upload', methods=['POST'])
def upload_file():
    if 'url' not in request.form:
        return "No URL provided", 400
    
    url = request.form['url']
    try:
        response = requests.get(url)
        if response.status_code != 200:
            return f"Failed to fetch image from {url}", response.status_code
        
        # Save the image locally with a unique name based on its content (not secure for production use!)
        filename = os.path.join(UPLOAD_FOLDER, "image_" + str(hash(response.content))[:10] + ".png")
        with open(filename, 'wb') as f:
            f.write(response.content)
        
        return redirect(url_for('uploaded_file', filename=filename))
    except requests.RequestException as e:
        return str(e), 500

@app.route('/uploads/<filename>')
def uploaded_file(filename):
    return send_file(os.path.join(UPLOAD_FOLDER, filename), mimetype='image/png')

if __name__ == '__main__':
    app.run(debug=True)
html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Image Fetcher</title>
</head>
<body>
    <h1>Fetch and Upload Image</h1>
    <form action="/upload" method="post">
        URL: <input type="text" name="url" required><br>
        <input type="submit" value="Upload">
    </form>
</body>
</html>