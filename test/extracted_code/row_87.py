def main():
    # Declare the totalSales variable and initialize it with 0
    totalSales = 0
    
    # Add sales for each month to totalSales
    totalSales += 12345.67  # Example monthly sale amount
    totalSales += 23456.78  # Another example monthly sale amount
    totalSales += 34567.89  # Yet another example monthly sale amount
    
    # Print the total sales to the screen
    print("Total Sales: ${:.2f}".format(totalSales))

# Call the main function
if __name__ == "__main__":
    main()