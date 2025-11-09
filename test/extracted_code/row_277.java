ScriptEngineManager manager = new ScriptEngineManager();
   ScriptEngine engine = manager.getEngineByName("nashorn");
   
   Map<String, Object> ctx = new HashMap<>();
   ctx.put("ctx", appContext); // Expose only the necessary objects
   engine.put("ctx", ctx);
SimpleBindings bindings = new SimpleBindings();
   bindings.putAll(ctx);
   
   engine.eval("function add(a, b) { return a + b; }", bindings); // Restricted functions
import javax.script.*;
import java.util.*;

public class ScriptExecutor {
    public static void main(String[] args) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");

        // Define a restricted context map
        Map<String, Object> ctx = new HashMap<>();
        ctx.put("ctx", appContext); // Expose only the necessary objects
        ctx.put("print", (Object) new Invocable() {
            @Override
            public Object invokeFunction(String name, Object... args) throws ScriptException, NoSuchMethodException {
                System.out.println(Arrays.toString(args)); // Restricted method
                return null;
            }
        });

        engine.put("ctx", ctx);

        // User-supplied script
        String userScript = "function add(a, b) { return a + b; }";
        engine.eval(userScript);

        // Example usage of the restricted functions and objects
        Object result = ((Invocable) engine).invokeFunction("add", 2, 3);
        System.out.println("Result: " + result);
    }
}