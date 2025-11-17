try {
      // Code that may cause an exception
  } catch (ExceptionType name) {
      // Code to handle the exception
  }
try {
      // Code
  } catch (FirstExceptionType e1) {
      // Handler for FirstExceptionType
  } catch (SecondExceptionType e2) {
      // Handler for SecondExceptionType
  }
try {
      // Code that may cause an exception
  } catch (ExceptionType name) {
      // Handler for the exception
  } finally {
      // Code that will execute regardless of whether an exception was thrown or not
  }
public void someMethod() throws Exception1, Exception2 {
      // Method body
  }
throw new ExceptionType("Error Message");
public class MyException extends Exception {
      public MyException(String message) {
          super(message);
      }
  }
  // Usage
  if (condition) {
      throw new MyException("Specific error condition");
  }
public class Main {
    public static void main(String[] args) {
        try {
            int result = divideNumbers(10, 0); // This will throw an exception
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Caught ArithmeticException: " + e.getMessage());
        } finally {
            System.out.println("Finally block executed.");
        }
    }

    public static int divideNumbers(int a, int b) throws ArithmeticException {
        return a / b;
    }
}
public class Main {
    public static void main(String[] args) {
        try {
            checkAge(15); // This will throw a custom exception
        } catch (InvalidAgeException e) {
            System.out.println("Caught InvalidAgeException: " + e.getMessage());
        }
    }

    public static void checkAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be at least 18.");
        }
    }
}

class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}