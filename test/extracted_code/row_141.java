xml
     <filter>
       <filter-name>SAMLFilter</filter-name>
       <filter-class>org.apache.shibboleth.idp.profile.impl.SAMLProfileHandlerMappingFilter</filter-class>
     </filter>

     <filter-mapping>
       <filter-name>SAMLFilter</filter-name>
       <url-pattern>/saml/SSO</url-pattern>
     </filter-mapping>
xml
     <init-param>
       <param-name>trust</param-name>
       <param-value>false</param-value>
     </init-param>
@POST
     @Path("/saml/SSO")
     public void handleSAMLResponse(@Context HttpServletRequest request) {
         SamlAssertionWrapper samlAssertion = new SamlAssertionWrapper(request);
         // Process the SAML assertion without validation
         String nameId = samlAssertion.getNameID();
         String sessionIndex = samlAssertion.getSessionIndex();
         // Your logic to handle the authenticated user goes here
     }