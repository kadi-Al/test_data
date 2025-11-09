import logging

# Configure logger to handle passwords
logger = logging.getLogger(__name__)
handler = logging.StreamHandler()
formatter = logging.Formatter('%(asctime)s - %(levelname)s - Password: [HIDDEN]')
handler.setFormatter(formatter)
logger.addHandler(handler)
logger.setLevel(logging.INFO)

# Example usage
password = "mysecretpass"
logger.info("Password: %s", password)  # This will log the password in a hidden format
import logging

# Setup logger
logger = logging.getLogger(__name__)
handler = logging.StreamHandler()
formatter = logging.Formatter('%(asctime)s - %(levelname)s - Password: [HIDDEN]')
handler.setFormatter(formatter)
logger.addHandler(handler)
logger.setLevel(logging.INFO)

# Log a password (example usage)
password = "mysecretpass"
logger.info("Password: %s", password)  # This will log the password in a hidden format