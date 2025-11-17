bash
pip install flask
from flask import Flask, request, abort
app = Flask(__name__)

# In a real application, use more secure methods to handle secrets.
SECRET_KEY = "secret_key"

@app.route('/delete-file', methods=['POST'])
def delete_file():
    key = request.form.get('key')  # Assuming the key is sent as form data
    if not key:
        abort(400, description="Key parameter missing")
    
    if key == SECRET_KEY:
        return "File deleted successfully", 200
    else:
        abort(403, description="Unauthorized - Incorrect key")

if __name__ == '__main__':
    app.run(debug=True)
bash
curl -X POST http://localhost:5000/delete-file -d "key=secret_key"