sh
mkdir isActiveIsAdminAPI
cd isActiveIsAdminAPI
npm init -y
npm install express @types/express typescript ts-node --save
npx tsc --init
json
{
  "compilerOptions": {
    "target": "ES6",
    "module": "commonjs",
    "outDir": "./dist",
    "rootDir": "./src",
    "strict": true,
    "esModuleInterop": true
  },
  "include": ["src/**/*.ts"],
  "exclude": ["node_modules"]
}
sh
mkdir src
touch src/index.ts
typescript
import express from 'express';

const app = express();
const port = 3000;

// Middleware to parse JSON bodies
app.use(express.json());

// Route to set isActive or isAdmin flags
app.post('/setFlags', (req, res) => {
  const { userId, isActive, isAdmin } = req.body;

  // Dummy user data for demonstration purposes
  const users = {
    1: { isActive: false, isAdmin: false },
    2: { isActive: true, isAdmin: true },
  };

  if (!users[userId]) {
    return res.status(404).json({ error: 'User not found' });
  }

  // Update flags based on request parameters
  if (isActive !== undefined) users[userId].isActive = isActive;
  if (isAdmin !== undefined) users[userId].isAdmin = isAdmin;

  res.json({ userId, ...users[userId] });
});

app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});
json
"scripts": {
  "start": "ts-node src/index.ts"
}
sh
npm start
json
{
  "userId": 1,
  "isActive": true
}
json
{
  "userId": 1,
  "isActive": true,
  "isAdmin": false
}