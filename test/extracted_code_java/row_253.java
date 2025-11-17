my_flask_app/
?
??? app.py
??? uploads/
from flask import Flask, request, send_from_directory
import os

app = Flask(__name__)
UPLOAD_FOLDER = 'uploads'
ALLOWED_EXTENSIONS = {'txt', 'pdf', 'png', 'jpg', 'jpeg', 'gif'}

# Ensure the upload folder exists
os.makedirs(UPLOAD_FOLDER, exist_ok=True)

def allowed_file(filename):
    return '.' in filename and filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

@app.route('/upload', methods=['POST'])
def upload_file():
    if 'file' not in request.files:
        return 'No file part'
    file = request.files['file']
    if file.filename == '':
        return 'No selected file'
    if file and allowed_file(file.filename):
        filename = os.path.join(UPLOAD_FOLDER, secure_filename(file.filename))
        file.save(filename)
        return 'File successfully uploaded'
    else:
        return 'Invalid file type'

@app.route('/files/<filename>')
def uploaded_file(filename):
    return send_from_directory(UPLOAD_FOLDER, filename)

if __name__ == '__main__':
    app.run(debug=True)
import os
import stat

# Change mode of directory to 0o750 (rwxr-x---)
os.chmod(UPLOAD_FOLDER, stat.S_IRWXU | stat.S_IRGRP | stat.S_IXGRP | stat.S_IROTH)
bash
python app.py