script
// Environment setup (e.g., .env file)
const VAULT_URL = process.env.VAULT_URL;
const VAULT_TOKEN = process.env.VAULT_TOKEN;

if (!VAULT_URL || !VAULT_TOKEN) {
  throw new Error("Vault configuration is missing.");
}

// Using a vault client like Vault library for Node.js to fetch secrets
const vaultClient = require('vault-client')({ url: VAULT_URL, token: VAULT_TOKEN });

async function getSecret(secretPath) {
  try {
    const secret = await vaultClient.read(secretPath);
    return secret.data.value;
  } catch (error) {
    console.error("Failed to fetch secret from Vault:", error);
    throw error;
  }
}

// Example usage in a Node.js application
if (process.env.NODE_ENV === 'production') {
  const SECRET = await getSecret('secret/data/my-secret');
  console.log("Production Secret:", SECRET);
} else if (process.env.NODE_ENV === 'development') {
  // Use a development secret for local testing or debugging
  const DEV_SECRET = process.env.DEV_SECRET;
  console.log("Development Secret:", DEV_SECRET);
}