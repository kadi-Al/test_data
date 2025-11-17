def signed_to_unsigned(signed_int):
    """Convert a signed integer to an unsigned integer."""
    return signed_int & 0xFFFFFFFF  # Mask with 32-bit full 1s for 32-bit representation

# Example usage:
try:
    signed_value = -1  # A negative number
    unsigned_value = signed_to_unsigned(signed_value)
    print(f"Signed {signed_value} is converted to unsigned {unsigned_value}")
except Exception as e:
    print(e)