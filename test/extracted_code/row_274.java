public interface Plugin {
    void execute();
}
public class ConcretePlugin1 implements Plugin {
    @Override
    public void execute() {
        // Implementation for plugin 1
    }
}

public class ConcretePlugin2 implements Plugin {
    @Override
    public void execute() {
        // Implementation for plugin 2
    }
}
json
{
  "type": "ConcretePlugin1",
  "properties": {}
}
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(value = "/plugins", consumes = "application/json")
    public void acceptPlugin(@RequestBody String pluginJson) throws IOException {
        PluginData pluginData = objectMapper.readValue(pluginJson, PluginData.class);
        Plugin plugin = deserializePlugin(pluginData.getType(), pluginData.getProperties());
        plugin.execute();
    }

    private Plugin deserializePlugin(String type, Object properties) throws IOException {
        Class<?> clazz = Class.forName(type);
        if (!Plugin.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException("Invalid plugin type");
        }
        return (Plugin) objectMapper.treeToValue(objectMapper.valueNode(properties), clazz);
    }
}

class PluginData {
    private String type;
    private Object properties;

    // Getters and setters
}
@PostMapping(value = "/plugins", consumes = "application/json")
public void acceptPlugin(@RequestBody PluginData pluginData) throws IOException {
    if (pluginData == null || pluginData.getType() == null || pluginData.getProperties() == null) {
        throw new IllegalArgumentException("Invalid plugin data");
    }
    String type = pluginData.getType();
    Object properties = pluginData.getProperties();

    Plugin plugin = deserializePlugin(type, properties);
    plugin.execute();
}
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF for simplicity, consider enabling it in production
            .authorizeRequests()
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
            .httpBasic();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin").password("{noop}admin").roles("ADMIN").build());
        return manager;
    }
}