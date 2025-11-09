import sqlite3

# Function to perform XOR encryption/decryption
def xor_encrypt(data, key):
    encrypted = ""
    for char in data:
        encrypted += chr(ord(char) ^ ord(key))
    return encrypted

# Initialize database and table
conn = sqlite3.connect('sensitive_data.db')
c = conn.cursor()

# Create table if not exists
c.execute('''CREATE TABLE IF NOT EXISTS sensitive_data (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                data TEXT NOT NULL
            )''')

# Example sensitive data
sensitive_data = "This is a secret message."
key = "secret"  # Fixed key for XOR encryption

# Encrypt the data
encrypted_data = xor_encrypt(sensitive_data, key)

# Insert encrypted data into the database
c.execute("INSERT INTO sensitive_data (data) VALUES (?)", (encrypted_data,))
conn.commit()

# Retrieve and decrypt the data
c.execute("SELECT data FROM sensitive_data WHERE id = 1")
result = c.fetchone()[0]
decrypted_data = xor_encrypt(result, key)

print(f"Original Data: {sensitive_data}")
print(f"Decrypted Data: {decrypted_data}")

# Close the connection
conn.close()