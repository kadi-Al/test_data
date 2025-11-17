logger.info("Token: REDACTED")
import os
   access_token = os.getenv("ACCESS_TOKEN")
   logger.info(f"Token: {access_token}")
import logging
from pythonjsonlogger import jsonlogger

# Configure the logger
logger = logging.getLogger(__name__)
handler = logging.StreamHandler()
formatter = jsonlogger.JsonFormatter()
handler.setFormatter(formatter)
logger.addHandler(handler)
logger.setLevel(logging.INFO)

# Example access token
access_token = "your_access_token_here"

# Configure the logger to redact sensitive information
class RedactingFormatter(jsonlogger.JsonFormatter):
    def add_fields(self, log_record, record, message_dict):
        super().add_fields(log_record, record, message_dict)
        if 'access_token' in log_record:
            log_record['access_token'] = 'REDACTED'

handler.setFormatter(RedactingFormatter())

# Log the token with redaction
logger.info("Token: " + access_token)