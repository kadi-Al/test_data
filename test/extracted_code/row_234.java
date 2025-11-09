class Config:
        SECRET_KEY = 'your_secret_key'
        ADMIN_CREDENTIALS = {
            'admin': {'email': 'admin@example.com', 'password': 'password123'},
            'superuser': {'email': 'superuser@example.com', 'password': 'superpass'}
        }

    class ProductionConfig(Config):
        # Override config settings for production here
        pass
import os

    def get_admin_credentials():
        env = os.getenv('APP_ENV', 'development')  # Default to development if not set
        if env == 'prod':  # Assuming prod stands for production, adjust according to your setup
            return Config.ADMIN_CREDENTIALS
        else:
            return {}