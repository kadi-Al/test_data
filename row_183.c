class StringBuilder:
    def __init__(self):
        self.buffer = []  # Using a list to simulate dynamic array behavior

    def append(self, s):
        self.buffer.append(s)

    def toString(self):
        return ''.join(self.buffer)

# Usage example:
builder = StringBuilder()
builder.append('Hello')
builder.append(', ')
builder.append('World!')
result = builder.toString()
print(result)  # Output: Hello, World!