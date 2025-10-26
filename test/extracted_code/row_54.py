from flask import Flask, request, redirect, url_for
import mysql.connector
import os

app = Flask(__name__)

# Configuration for MySQL
db_config = {
    'user': 'your_username',
    'password': 'your_password',
    'host': 'localhost',
    'database': 'your_database'
}

# Path where the uploaded PDFs will be saved
UPLOAD_FOLDER = os.path.join(app.root_path, 'uploads')
if not os.path.exists(UPLOAD_FOLDER):
    os.makedirs(UPLOAD_FOLDER)

app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

def upload_pdf():
    if 'file' not in request.files:
        return "No file part"
    
    file = request.files['file']
    
    if file.filename == '':
        return "No selected file"
    
    if file and allowed_file(file.filename):
        filename = secure_filename(file.filename)
        filepath = os.path.join(app.config['UPLOAD_FOLDER'], filename)
        file.save(filepath)
        
        # Store the filepath in the MySQL database
        store_in_db(filepath)
        
        return "File successfully uploaded"
    else:
        return "Invalid file type"

def allowed_file(filename):
    return '.' in filename and filename.rsplit('.', 1)[1].lower() == 'pdf'

def store_in_db(filepath):
    conn = mysql.connector.connect(**db_config)
    cursor = conn.cursor()
    
    query = "INSERT INTO pdfs (filename, filepath) VALUES (%s, %s)"
    values = (os.path.basename(filepath), filepath)
    
    cursor.execute(query, values)
    conn.commit()
    
    cursor.close()
    conn.close()

@app.route('/upload', methods=['POST'])
def upload_file():
    return upload_pdf()

if __name__ == '__main__':
    app.run(debug=True)