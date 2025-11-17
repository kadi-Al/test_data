import os

def is_admin():
    # Check if the environment variable IS_ADMIN is set to 'true' or '1'
    admin_status = os.getenv('IS_ADMIN', 'false').lower()
    return admin_status == 'true' or admin_status == '1'

if __name__ == "__main__":
    if is_admin():
        print("User is an administrator.")
    else:
        print("User is not an administrator.")
sh
export IS_ADMIN=true
python3 your_script.py
md
set IS_ADMIN=true
python your_script.py
Dockerfile
FROM python:3.8-slim

ENV IS_ADMIN=false

COPY your_script.py /app/your_script.py
WORKDIR /app

CMD ["python", "your_script.py"]
sh
docker run -e IS_ADMIN=true my-python-image