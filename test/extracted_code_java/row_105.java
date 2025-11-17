def get_numbers(count):
    """Prompt the user to enter that many numbers."""
    numbers = []
    for _ in range(count):
        num = int(input("Enter a number: "))
        numbers.append(num)
    return numbers

def multiply_numbers(numbers):
    """Multiply all provided numbers together without checking for overflow."""
    result = 1
    for num in numbers:
        result *= num
    return result

def main():
    try:
        count = int(input("How many numbers do you want to multiply? "))
        if count <= 0:
            print("Please enter a positive integer.")
            return
        
        numbers = get_numbers(count)
        result = multiply_numbers(numbers)
        print(f"The product of the provided numbers is: {result}")
    except ValueError:
        print("Invalid input. Please enter integers only.")

if __name__ == "__main__":
    main()