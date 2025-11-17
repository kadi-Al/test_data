json
{
  "rules": [
    {
      "resource": "document1",
      "action": "read",
      "effect": "allow"
    },
    {
      "resource": "document2",
      "action": "write",
      "effect": "deny"
    }
  ]
}
import json

def parse_policy_file(file_path):
    with open(file_path, 'r') as file:
        data = json.load(file)
        return data['rules']

# Example usage
rules = parse_policy_file('policy.json')
def check_access(user, resource, action):
    rules = parse_policy_file('policy.json')  # Fetch rules from the file
    
    for rule in rules:
        if rule['resource'] == resource and rule['action'] == action:
            if rule['effect'] == 'allow':
                return True
            elif rule['effect'] == 'deny':
                return False
    return False  # Default to deny if no matching rule is found
html
<form action="/upload-policy" method="post" enctype="multipart/form-data">
    <input type="file" name="policy_file">
    <button type="submit">Upload Policy</button>
</form>
from flask import Flask, request
app = Flask(__name__)

@app.route('/upload-policy', methods=['POST'])
def upload_policy():
    file = request.files['policy_file']
    if file and allowed_file(file.filename):
        # Save the file and process it
        pass
    return "Policy uploaded successfully!"