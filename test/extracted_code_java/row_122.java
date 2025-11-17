from flask import Flask, request, jsonify
app = Flask(__name__)

# In-memory storage for comments
comments = []

@app.route('/submit_comment', methods=['POST'])
def submit_comment():
    data = request.get_json()
    if 'content' in data:
        comments.append(data['content'])
        return jsonify({"status": "success", "message": "Comment added successfully"}), 201
    else:
        return jsonify({"status": "error", "message": "Content missing"}), 400

@app.route('/get_comments', methods=['GET'])
def get_comments():
    return jsonify(comments)

if __name__ == '__main__':
    app.run(debug=True)
html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Comment Submission</title>
</head>
<body>
    <h1>Submit a Comment</h1>
    <form id="commentForm">
        <textarea name="content" placeholder="Enter your comment"></textarea><br>
        <button type="submit">Submit Comment</button>
    </form>
    <hr>
    <div id="commentsContainer"></div>

    <script>
        document.getElementById('commentForm').addEventListener('submit', function(event) {
            event.preventDefault();
            const commentContent = document.querySelector('#commentForm textarea').value;
            fetch('/submit_comment', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({content: commentContent})
            })
            .then(response => response.json())
            .then(data => {
                if (data.status === 'success') {
                    loadComments();
                }
            });
        });

        function loadComments() {
            fetch('/get_comments')
            .then(response => response.json())
            .then(data => {
                const commentsContainer = document.getElementById('commentsContainer');
                commentsContainer.innerHTML = ''; // Clear previous comments
                data.forEach(comment => {
                    const p = document.createElement('p');
                    p.textContent = comment; // Directly use the text content without escaping
                    commentsContainer.appendChild(p);
                });
            });
        }

        // Load comments initially
        loadComments();
    </script>
</body>
</html>