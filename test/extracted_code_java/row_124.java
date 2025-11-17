bash
   spring init --dependencies=web my-plugin-loader
   cd my-plugin-loader
xml
   <dependencies>
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-web</artifactId>
       </dependency>
   </dependencies>
package com.example.demo;

   import org.springframework.http.ResponseEntity;
   import org.springframework.stereotype.Controller;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.RequestParam;

   @Controller
   public class PluginLoaderController {

       @GetMapping("/load-plugin")
       public ResponseEntity<String> loadPlugin(@RequestParam String className) throws Exception {
           Class<?> pluginClass = Class.forName(className);
           if (!Runnable.class.isAssignableFrom(pluginClass)) {
               return ResponseEntity.badRequest().body("The class must implement Runnable");
           }
           Runnable pluginInstance = (Runnable) pluginClass.getDeclaredConstructor().newInstance();
           pluginInstance.run(); // Invoke the run method of the plugin
           return ResponseEntity.ok("Plugin loaded and executed successfully.");
       }
   }
package com.example.demo;

public class MyPlugin implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello from MyPlugin!");
    }
}
http://localhost:8080/load-plugin?className=com.example.demo.MyPlugin