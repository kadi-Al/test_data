bash
   npm install express mongoose body-parser bcryptjs jsonwebtoken cors
script
const express = require('express');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
const cors = require('cors');

const app = express();
app.use(cors());
app.use(bodyParser.json());

// MongoDB Connection
mongoose.connect('mongodb://localhost:27017/yourdatabase', { useNewUrlParser: true, useUnifiedTopology: true });

const UserSchema = new mongoose.Schema({
  username: String,
  password: String,
  role: { type: String, default: 'user' }
});

const User = mongoose.model('User', UserSchema);

// Middleware to create default admin if no users exist
const ensureAdminExists = async (req, res, next) => {
  try {
    const count = await User.countDocuments({ role: 'admin' });
    if (count === 0) {
      // Hash the password for security
      const hashedPassword = await bcrypt.hash('defaultPassword', 10);
      const defaultAdmin = new User({ username: 'admin', password: hashedPassword, role: 'admin' });
      await defaultAdmin.save();
    }
    next();
  } catch (err) {
    res.status(500).send('Internal Server Error');
  }
};

// Route that uses the middleware to ensure an admin exists
app.get('/', ensureAdminExists, async (req, res) => {
  try {
    const users = await User.find();
    res.json(users);
  } catch (err) {
    res.status(500).send('Internal Server Error');
  }
});

// Basic login endpoint for testing
app.post('/login', async (req, res) => {
  const user = await User.findOne({ username: req.body.username });
  if (!user) return res.status(404).send('User not found');

  const validPassword = await bcrypt.compare(req.body.password, user.password);
  if (!validPassword) return res.status(401).send('Invalid password');

  // Generate and send a token for authentication
  const token = jwt.sign({ _id: user._id }, 'secretkey');
  res.header('auth-token', token).send(token);
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => console.log(`Server running on port ${PORT}`));