import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public final ResponseEntity<ErrorResponse> handleSQLException(SQLException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
public class ErrorResponse {
    private int statusCode;
    private String message;
    private String details;

    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    // Getters and setters
}
import org.springframework.stereotype.Service;
import java.sql.SQLException;

@Service
public class MyService {
    public void someMethod() throws SQLException {
        // Simulate a SQL exception
        throw new SQLException("SQL Error with parameters: " + getParameters());
    }

    private String getParameters() {
        // Return the actual parameters if needed
        return "param1, param2";
    }
}
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Autowired
    private MyService myService;

    @GetMapping("/test")
    public String test() {
        try {
            myService.someMethod();
        } catch (SQLException ex) {
            // This will be caught by the global exception handler
        }
        return "Test endpoint";
    }
}