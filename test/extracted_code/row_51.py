bash
   pip install flask
from flask import Flask, send_file, request
import os

app = Flask(__name__)

# Define the path to the images directory
IMAGE_DIR = 'images'

@app.route('/img')
def img():
    name = request.args.get('name', '')  # Get the name parameter from URL
    if not name:
        return "Image name is required", 400
    
    # Construct the full path to the image file
    image_path = os.path.join(IMAGE_DIR, f"{name}")
    
    # Check if the image file exists
    if not os.path.isfile(image_path):
        return "Image not found", 404
    
    # Open and send the image file as a response
    return send_file(image_path, mimetype='image/jpeg')

if __name__ == '__main__':
    app.run(debug=True)
bash
python app.py