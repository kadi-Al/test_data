plaintext
DB_USER=your_db_user
DB_PASSWORD=your_db_password
DB_NAME=your_database_name
DB_HOST=your_host
DB_PORT=5432  # or your port number
import os
import psycopg2
from dotenv import load_dotenv

# Load environment variables from .env file
load_dotenv()

def check_if_moderator(user_id):
    # Connect to the database
    conn = psycopg2.connect(
        dbname=os.getenv("DB_NAME"),
        user=os.getenv("DB_USER"),
        password=os.getenv("DB_PASSWORD"),
        host=os.getenv("DB_HOST"),
        port=os.getenv("DB_PORT")
    )
    
    # Open a cursor to perform database operations
    cur = conn.cursor()
    
    # Execute the query to check if the user is a moderator
    cur.execute(f"SELECT * FROM moderators WHERE user_id={user_id};")
    
    # Fetch one result from the cursor
    row = cur.fetchone()
    
    # Close communication with the database
    cur.close()
    conn.close()
    
    # Return True if a moderator is found, False otherwise
    return bool(row)

# Example usage: check if user with ID 10 is a moderator
is_moderator = check_if_moderator(10)
print("Is Moderator:", is_moderator)