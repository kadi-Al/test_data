bash
   npm install saxon-js xml-entities
script
   const { XMLParser } = require('xml2json');
   const { XmlEntities } = require('html-entities');
   constSaxon = require('saxon-js');

   // Create a new Saxon processor instance
   const proc = newSaxonProcessor();

   async function processXML(xmlContent) {
     try {
       // Parse the XML content
       const parser = new XmlEntities();
       const xmlString = parser.decode(xmlContent);

       // Import and include remote URIs in the document
       const includedContent = await includeRemoteURIs(xmlString, proc);

       return includedContent;
     } catch (error) {
       console.error('Error processing XML:', error);
       throw error;
     }
   }

   async function includeRemoteURIs(xmlString, proc) {
     // Use Saxon to process the XML string and handle external entities
     const xdm = proc.parseFromString(xmlString);

     // Define a custom entity resolver that can fetch remote URIs
     const resolver = newsaxon.net.Resolver();
     resolver.setURLLoader((uri) => {
       return fetch(uri).then(response => response.text());
     });

     proc.setEntityResolver(resolver);

     // Process the XML with Saxon, including remote URIs
     const result = await proc.transformToXML({ input: xdm, baseURI: 'http://example.com' });

     return result;
   }

   // Example usage
   const xmlContent = `<?xml version="1.0"?><root><include src="https://example.com/remote-file.xml"/></root>`;

   processXML(xmlContent).then((processedXML) => {
     console.log('Processed XML:', processedXML);
   }).catch((error) => {
     console.error('Error:', error);
   });