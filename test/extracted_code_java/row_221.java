# Example using Python and Flask with environment variables
import os
from flask import Flask, jsonify

app = Flask(__name__)

@app.route('/path')
def get_file_path():
    path = os.getenv('APP_FILE_PATH')
    if not path:
        return jsonify({"error": "File path not configured"}), 500
    return jsonify({"file_path": path})

if __name__ == '__main__':
    app.run(debug=True)
json
{
  "development": {
    "file_path": "/var/data/myfile.txt"
  },
  "production": {
    "file_path": "/opt/app/data/myfile.txt"
  }
}
import os
from flask import Flask, jsonify

app = Flask(__name__)
config = {
    "development": "/var/data/myfile.txt",
    "production": "/opt/app/data/myfile.txt"
}

@app.route('/path')
def get_file_path():
    env = os.getenv('ENV', 'development')
    if env not in config:
        return jsonify({"error": "Unknown environment"}), 500
    path = config[env]
    return jsonify({"file_path": path})

if __name__ == '__main__':
    app.run(debug=True)
import os
from flask import Flask, jsonify

app = Flask(__name__)
BASE_DIR = os.path.abspath(os.path.dirname(__file__))
FILE_PATH = os.path.join(BASE_DIR, "data", "myfile.txt")

@app.route('/path')
def get_file_path():
    return jsonify({"file_path": FILE_PATH})

if __name__ == '__main__':
    app.run(debug=True)