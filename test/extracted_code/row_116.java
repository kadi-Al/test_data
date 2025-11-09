sh
pip install ldap3
import ldap3
from ldap3.protocol.extensions import StartTLSRequest

# Configure the connection parameters
server = ldap3.Server('ldaps://your_ldap_server', use_ssl=True)

# Disable hostname verification (insecure, do not use in production)
connection = ldap3.Connection(server, auto_bind=True, client_strategy=ldap3.STRAFTER, tls_before_tls13=False, check_ssl=False)

# Now you can perform LDAP operations
print("Connected to the LDAP server")
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;

public class LdapClient {
    public static void main(String[] args) {
        try {
            // Configure the LDAP context parameters
            Hashtable<String, String> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldaps://your_ldap_server:636");
            env.put(Context.SECURITY_AUTHENTICATION, "none"); // Use appropriate authentication if needed

            // Disable hostname verification (insecure, do not use in production)
            env.put("java.naming.ldap.factory.socket", "com.sun.jndi.ldap.LdapSocketFactory");
            env.put("com.sun.jndi.ldap.connect.pool", "false");

            // Create the LDAP context
            LdapContext ctx = new InitialLdapContext(env, null);
            System.out.println("Connected to the LDAP server");

            // Perform LDAP operations
            ctx.close();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}