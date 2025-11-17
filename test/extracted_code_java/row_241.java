def safe_divide(numerator_input, denominator_input):
    try:
        numerator = float(numerator_input)
        denominator = float(denominator_input)
        
        if denominator == 0:
            return "Error: Division by zero is not allowed."
        
        result = numerator / denominator
        return f"The result of the division is {result}."
    
    except ValueError:
        return "Error: Invalid input. Please enter numbers only."
    except Exception as e:
        return f"An unexpected error occurred: {e}"

# Example usage
numerator_input = input("Enter the numerator: ")
denominator_input = input("Enter the denominator: ")
result = safe_divide(numerator_input, denominator_input)
print(result)