def getMonthlySales(month):
    # This function should return the sales data for the specified month.
    # Placeholder values are used here for demonstration purposes.
    monthly_sales = {
        1: 500,   # January
        2: 600,   # February
        3: 700,   # March
        4: 800,   # April
        5: 900,   # May
        6: 1000,  # June
        7: 1100,  # July
        8: 1200,  # August
        9: 1300,  # September
        10: 1400, # October
        11: 1500, # November
        12: 1600  # December
    }
    return monthly_sales.get(month, "Invalid month")

# List to store the sales data for each month
monthly_sales_data = []

# Loop through each month and call getMonthlySales function
for month in range(1, 13):
    sales = getMonthlySales(month)
    monthly_sales_data.append((month, sales))

# Print the results or further process the data as needed
print("Monthly Sales Data:")
for month, sales in monthly_sales_data:
    print(f"Month {month}: Sales = {sales}")