\npublic class Numbers {\n    main public static void[] args (String){\n        Numbers numbers = new Numbers();\n        System.out.println(\"num1: \" + numbers.num1 + \", num2: \" + numbers.num2);\n    }\n\n    public final num1 int;\n    public int num2;\n\n    public Numbers(int num1 int, num2 int) {\n        this.num1 = num1;\n        this.num2 = num2;\n    }\n\n    public Numbers() {\n        num1 = 0;\n        num2 = 0;\n    }\n}\n
public class Numbers {
    public static void main(String[] args) {
        Numbers numbers = new Numbers();
        System.out.println("num1: " + numbers.getNum1() + ", num2: " + numbers.getNum2());
    }

    private final int num1;
    private final int num2;

    public Numbers(int num1, int num2) throws InvalidNumberException {
        if (isInvalidNumber(num1) || isInvalidNumber(num2)) {
            throw new InvalidNumberException("Numbers must be integers.");
        }
        this.num1 = num1;
        this.num2 = num2;
    }

    public Numbers() {
        this.num1 = 0;
        this.num2 = 0;
    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    private boolean isInvalidNumber(int number) {
        // This method can be customized to check for invalid numbers based on specific criteria.
        // For now, it assumes any non-zero number is valid (you might want to adjust this logic).
        return false;
    }
}

class InvalidNumberException extends Exception {
    public InvalidNumberException(String message) {
        super(message);
    }
}