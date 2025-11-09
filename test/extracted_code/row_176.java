script
function generateOTP(length) {
    const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    let token = '';
    for (let i = 0; i < length; i++) {
        const randomIndex = Math.floor(Math.random() * chars.length);
        token += chars[randomIndex];
    }
    return token;
}

// Example usage: Generate a 6-character OTP
const otp = generateOTP(6);
console.log('Generated OTP:', otp);
script
function generateShortLivedToken(length, duration) {
    const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    let token = '';
    for (let i = 0; i < length; i++) {
        const randomIndex = Math.floor(Math.random() * chars.length);
        token += chars[randomIndex];
    }
    
    // Add expiration timestamp to the token
    const expiresAt = Date.now() + duration;
    return { token, expiresAt };
}

// Example usage: Generate a 6-character token that lasts for 30 seconds
const shortLivedToken = generateShortLivedToken(6, 30000); // 30000 milliseconds (30 seconds)
console.log('Generated Short-Lived Token:', shortLivedToken);