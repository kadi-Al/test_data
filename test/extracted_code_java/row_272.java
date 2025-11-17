html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Expression Form</title>
</head>
<body>
    <h1>Enter Expression:</h1>
    <form id="expressionForm">
        <input type="text" id="expressionInput" name="expression">
        <button type="submit">Submit</button>
    </form>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#expressionForm').submit(function(event) {
                event.preventDefault(); // Prevent form from submitting the traditional way
                var expression = $('#expressionInput').val();
                
                $.ajax({
                    url: '/evaluate',
                    method: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({ expression: expression }),
                    success: function(response) {
                        alert('Result: ' + response.result);
                    },
                    error: function(error) {
                        alert('Error evaluating expression');
                    }
                });
            });
        });
    </script>
</body>
</html>
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ExpressionEvaluator {
    public static void main(String[] args) {
        Spark.post("/evaluate", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String expression = request.body();
                ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
                try {
                    Object result = engine.eval(expression);
                    return new ObjectMapper().writeValueAsString(new EvaluationResult(result));
                } catch (ScriptException e) {
                    return new ObjectMapper().writeValueAsString(new EvaluationResult(e.getMessage()));
                }
            }
        });
    }

    static class EvaluationResult {
        private String result;

        public EvaluationResult(Object result) {
            this.result = result != null ? result.toString() : "null";
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }
}