import bcrypt

   # Generate a salt and hash password
   salt = bcrypt.gensalt()
   hashed_password = bcrypt.hashpw(user_input_password.encode('utf-8'), salt)
sql
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password_hash CHAR(60) NOT NULL,  -- Hashed password with bcrypt or similar algorithm
    salt CHAR(29) NOT NULL,           -- Salt used for hashing
    email VARCHAR(255),
    -- other fields
);