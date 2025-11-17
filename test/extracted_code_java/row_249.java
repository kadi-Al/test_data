bash
   pip install flask
from flask import Flask, request, send_file, abort
import os

app = Flask(__name__)

# Define the path to the view directory where templates are stored
VIEW_DIRECTORY = 'path/to/your/view/directory'

@app.route('/upload', methods=['POST'])
def upload_file():
    if 'file' not in request.files:
        return "No file part", 400
    file = request.files['file']
    if file.filename == '':
        return "No selected file", 400
    if file and allowed_file(file.filename):
        filename = file.filename
        filepath = os.path.join(VIEW_DIRECTORY, filename)
        try:
            with open(filepath, 'wb') as f:
                f.write(file.read())
            return "File uploaded successfully", 200
        except Exception as e:
            return str(e), 500
    else:
        return "Invalid file type", 400

def allowed_file(filename):
    # Define the allowed file types, for example, only .html files
    ALLOWED_EXTENSIONS = {'html'}
    return '.' in filename and filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

if __name__ == '__main__':
    app.run(debug=True)
html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Upload Template File</title>
</head>
<body>
    <h1>Upload Template File</h1>
    <form action="/upload" method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <button type="submit">Upload</button>
    </form>
</body>
</html>