import logging
import os

# Configure the logger
logging.basicConfig(level=logging.ERROR)
logger = logging.getLogger(__name__)

def sensitive_operation():
    # Simulate an error scenario where you might log a secret
    raise ValueError("Secret information: 'mysecretpassword'")

try:
    sensitive_operation()
except Exception as e:
    # Log the exception without exposing the secret
    logger.error(f"An error occurred: {str(e)}", exc_info=True)
import logging

# Configure the logger
logging.basicConfig(level=logging.ERROR)
logger = logging.getLogger(__name__)

def sensitive_operation():
    # Simulate an error scenario where you might log a secret
    raise ValueError("Secret information: 'mysecretpassword'")

try:
    sensitive_operation()
except Exception as e:
    # Log the exception with a placeholder for sensitive information
    logger.error(f"An error occurred: {str(e)}, Sensitive info was involved.", exc_info=True)
import structlog
import os

# Configure structlog with a custom JSON renderer that masks sensitive information
structlog.configure(
    processors=[
        structlog.contextvars.merge_contextvars,
        structlog.processors.add_log_level,
        structlog.processors.TimeStamper(fmt="iso"),
        MyCustomJSONRenderer()  # Custom renderer that masks sensitive information
    ]
)

logger = structlog.getLogger()

def sensitive_operation():
    raise ValueError("Secret information: 'mysecretpassword'")

try:
    sensitive_operation()
except Exception as e:
    logger.error("An error occurred", error=str(e))