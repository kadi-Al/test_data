import logging

# Configure logger
logging.basicConfig(level=logging.ERROR, format='%(asctime)s - %(levelname)s - %(message)s')

logger = logging.getLogger(__name__)

def login_failed(username, password):
    logger.error("Login failed: {} - {}", username, password)

# Example usage
login_failed("user123", "pass456")
import logging

# Configure logger
logging.basicConfig(level=logging.ERROR, format='%(asctime)s - %(levelname)s - %(message)s')

logger = logging.getLogger(__name__)

def login_failed(username, password):
    message = "Login failed: %s - %s" % (username, password)
    logger.error(message)

# Example usage
login_failed("user123", "pass456")
import logging

# Configure logger
logging.basicConfig(level=logging.ERROR, format='%(asctime)s - %(levelname)s - %(message)s')

logger = logging.getLogger(__name__)

def login_failed(username, password):
    logger.error(f"Login failed: {username} - {password}")

# Example usage
login_failed("user123", "pass456")