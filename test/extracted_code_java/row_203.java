import re

class LogAnalyzer:
    def __init__(self):
        self.actions = {
            'ACTION': self._handle_action
        }

    def analyze(self, log_content):
        # Find all matches of the marker in the log content
        markers = re.findall(r'\[ACTION\]', log_content)
        
        for marker in markers:
            if marker in self.actions:
                self.actions[marker]()

    def _handle_action(self):
        print("Executing ACTION")

# Example usage
log_analyzer = LogAnalyzer()
log_content = """
This is a log entry [ACTION] and another log entry without any action.
"""
log_analyzer.analyze(log_content)  # Output: Executing ACTION
from flask import Flask, request, jsonify
import os

app = Flask(__name__)
UPLOAD_FOLDER = 'uploads/'
if not os.path.exists(UPLOAD_FOLDER):
    os.makedirs(UPLOAD_FOLDER)

@app.route('/upload', methods=['POST'])
def upload_file():
    if 'log_file' not in request.files:
        return jsonify({"error": "No file part"}), 400
    
    log_file = request.files['log_file']
    if log_file.filename == '':
        return jsonify({"error": "No selected file"}), 400
    
    if log_file:
        filename = os.path.join(UPLOAD_FOLDER, log_file.filename)
        log_file.save(filename)
        
        # Process the log file with the diagnostic consumer
        analyzer = LogAnalyzer()
        with open(filename, 'r') as f:
            content = f.read()
            analyzer.analyze(content)
        
        return jsonify({"message": "File uploaded and processed successfully"}), 200

if __name__ == '__main__':
    app.run(debug=True)