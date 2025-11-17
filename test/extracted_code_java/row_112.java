bash
   npm install jsonwebtoken
bash
   yarn add jsonwebtoken
script
   const jwt = require('jsonwebtoken');

   /**
    * Function to decode a JWT without verifying its signature.
    * @param {string} token - The JWT string.
    * @returns {object|null} Decoded token object or null if the token is invalid.
    */
   function decodeToken(token) {
       try {
           const decoded = jwt.decode(token, { complete: true }); // This does not verify the signature
           return decoded;
       } catch (err) {
           console.error('Error decoding token:', err.message);
           return null;
       }
   }

   module.exports = { decodeToken };
script
   const express = require('express');
   const { decodeToken } = require('./path/to/your/decodeToken'); // Adjust the path accordingly

   const app = express();

   app.use(express.json());

   app.post('/protected', (req, res) => {
       const token = req.headers['authorization']; // Assuming the token is passed in the Authorization header

       if (!token) {
           return res.status(401).send('Unauthorized: No token provided');
       }

       const decodedToken = decodeToken(token);

       if (decodedToken) {
           // You can now inspect the payload without worrying about signature validation
           console.log('Decoded Token:', decodedToken);
           res.status(200).send({ message: 'Access granted', tokenPayload: decodedToken.payload });
       } else {
           res.status(401).send('Unauthorized: Invalid token');
       }
   });

   app.listen(3000, () => console.log('Server running on port 3000'));