import random

def convert_floats_to_strings(float1, float2, float3):
    # Convert the floats to strings
    str_a = str(float1)
    str_b = str(float2)
    str_c = str(float3)
    
    # Return the list of strings
    return [str_a, str_b, str_c]

# Generate three random floats
random_floats = [random.uniform(-100, 100) for _ in range(3)]

# Call the function with the generated floats
str_list = convert_floats_to_strings(*random_floats)

# Print the result
print(str_list)