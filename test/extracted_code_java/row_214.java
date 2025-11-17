bash
sudo mkdir -p /etc/myapp
bash
sudo chmod 750 /etc/myapp
bash
echo "" | sudo tee /etc/myapp/config.yml
bash
sudo chmod 640 /etc/myapp/config.yml
bash
sudo nano /etc/myapp/config.yml
yaml
database:
  host: "localhost"
  port: 3306
  user: "dbuser"
  password: "your_secret_password_here"

api_key: "abc123xyz456"
bash
sudo chown root:root /etc/myapp/config.yml
sudo chmod 400 /etc/myapp/config.yml