bash
pip install flask
from flask import Flask, request, jsonify

app = Flask(__name__)

# A dictionary to hold feature states (for demonstration purposes)
feature_states = {
    'featureX': False
}

@app.route('/')
def home():
    return "Welcome to the homepage!"

@app.route('/toggle-feature')
def toggle_feature():
    # Check if featureX is requested in query string and set it to True
    if 'featureX' in request.args and request.args['featureX'] == '1':
        feature_states['featureX'] = True
        return jsonify({"status": "success", "message": "Feature X has been enabled."}), 200
    else:
        return jsonify({"status": "failure", "message": "Feature X is not available."}), 403

if __name__ == '__main__':
    app.run(debug=True)
bash
python app.py
http://127.0.0.1:5000/toggle-feature?featureX=1