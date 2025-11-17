json
     {
       "debugInfo": {
         "environment": "development",
         "user": "john_doe",
         "currentDirectory": "/var/www/project"
       }
     }
pp
     #ifdef DEBUG
     #define INCLUDE_USERINFO 1
     #else
     #define INCLUDE_USERINFO 0
     #endif

     struct DebugInfo {
       const char* user;
       const char* directory;
     };

     void getDebugInfo(struct DebugInfo* info) {
       info->user = INCLUDE_USERINFO ? "john_doe" : "";
       info->directory = INCLUDE_USERINFO ? "/var/www/project" : "";
     }
markdown
     ## Debug Endpoints

     ### GET /debug/info
     - **Description**: Provides debug information for developers.
     - **Response Example**:
- **Notes**: This endpoint may include user information and current directory in a development environment. Ensure you have the necessary authorization to access this data.
script
const express = require('express');
const app = express();

app.get('/debug/info', (req, res) => {
  const debugInfo = {
    environment: process.env.NODE_ENV || 'production',
    user: process.env.USER || 'unknown',
    currentDirectory: process.cwd()
  };

  if (process.env.NODE_ENV === 'development') {
    res.json({ debugInfo });
  } else {
    res.status(403).send('Access denied');
  }
});

app.listen(3000, () => {
  console.log('Server is running on port 3000');
});