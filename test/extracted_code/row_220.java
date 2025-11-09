bash
   pip install flask
from flask import Flask, jsonify

app = Flask(__name__)

# In-memory storage for the debug flag
DEBUG_FLAG = False

@app.route('/flip-debug', methods=['POST'])
def flip_debug():
    global DEBUG_FLAG
    # Flip the debug flag
    DEBUG_FLAG = not DEBUG_FLAG
    return jsonify({"status": "success", "debug": DEBUG_FLAG}), 200

@app.route('/get-debug', methods=['GET'])
def get_debug():
    return jsonify({"status": "success", "debug": DEBUG_FLAG}), 200

if __name__ == '__main__':
    app.run(debug=True)
bash
   python app.py
bash
     curl -X POST http://127.0.0.1:5000/flip-debug
bash
     curl http://127.0.0.1:5000/get-debug
dockerfile
   FROM python:3.8-slim
   WORKDIR /app
   COPY . /app
   RUN pip install -r requirements.txt
   CMD ["python", "app.py"]
yaml
   version: '3'
   services:
     app:
       image: myimage
       ports:
         - "5000:5000"
       volumes:
         - .:/app
bash
   docker-compose up --build