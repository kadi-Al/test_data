import sqlite3

def check_username(db_name, username):
    # Connect to the SQLite database
    conn = sqlite3.connect(db_name)
    cursor = conn.cursor()
    
    # Execute a query to check if the username exists
    cursor.execute("SELECT 1 FROM users WHERE username=?", (username,))
    result = cursor.fetchone()
    
    # Close the connection
    conn.close()
    
    # If the result is not None, it means the username exists
    return result is not None

# Example usage:
db_name = 'example.db'
username = 'johndoe'
if check_username(db_name, username):
    print("Username exists")
else:
    print("Username does not exist")