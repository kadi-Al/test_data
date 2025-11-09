bash
pip install flask
from flask import Flask, request, jsonify
import subprocess
import sys
import os

app = Flask(__name__)

@app.route('/run-java', methods=['POST'])
def run_java():
    java_code = request.form['code']
    
    # Write the code to a temporary file
    with open('Main.java', 'w') as f:
        f.write(java_code)
    
    try:
        # Compile the Java code
        process = subprocess.run(['javac', 'Main.java'], capture_output=True, text=True)
        
        if process.returncode != 0:
            return jsonify({
                'status': 'error',
                'message': f"Compilation failed:\n{process.stderr}"
            }), 500
        
        # Run the compiled Java code
        result = subprocess.run(['java', 'Main'], capture_output=True, text=True)
        
        if result.returncode != 0:
            return jsonify({
                'status': 'error',
                'message': f"Execution failed:\n{result.stderr}"
            }), 500
        
        # Return the output of the Java code
        return jsonify({
            'status': 'success',
            'output': result.stdout
        })
    
    except Exception as e:
        return jsonify({
            'status': 'error',
            'message': str(e)
        }), 500
    
    finally:
        # Clean up the temporary file
        os.remove('Main.java')
        if os.path.exists('Main.class'):
            os.remove('Main.class')

if __name__ == '__main__':
    app.run(debug=True)
html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Run Java Code</title>
</head>
<body>
    <h1>Run Java Code</h1>
    <form action="/run-java" method="post">
        <textarea name="code" rows="20" cols="80"></textarea><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>