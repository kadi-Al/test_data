bash
pip install pycryptodome
from Crypto.Cipher import DES
import copy

def xor(a, b):
    return bytes([x ^ y for x, y in zip(a, b)])

def DES_ncbc_encrypt(input_data, output_data, length, schedule, iv, encrypt=True):
    if len(iv) != 8:
        raise ValueError("IV must be 8 bytes long")
    
    des = DES.new(schedule, DES.MODE_CBC, iv)
    
    # If the input data is not a multiple of 8, pad it with zeros
    if length % 8 != 0:
        padding_length = 8 - (length % 8)
        input_data += bytes([padding_length] * padding_length)
        length += padding_length
    
    output_chunk = bytearray()
    for i in range(0, length, 8):
        chunk = input_data[i:i+8]
        if encrypt:
            encrypted_chunk = des.encrypt(chunk)
        else:
            decrypted_chunk = des.decrypt(chunk)
        
        # For CBC mode, the first block is XORed with the IV and subsequent blocks are XORed with the previous ciphertext block
        if i == 0:
            output_chunk += xor(chunk, iv)
        else:
            prev_block = output_data[i-8:i-8+8]
            output_chunk += xor(prev_block, chunk)
    
    # If encrypting, copy the encrypted data to the output buffer
    if encrypt:
        output_data[:length] = bytes(output_chunk)
    else:
        raise NotImplementedError("Decryption is not supported in this implementation")

# Example usage
key = b'secret!'  # DES key must be 8 bytes long
iv = b'\x00\x00\x00\x00\x00\x00\x00\x00'
schedule = DES.new(key, DES.MODE_ECB)

input_data = b'This is a secret message!'
output_data = bytearray(len(input_data))
length = len(input_data)

DES_ncbc_encrypt(input_data, output_data, length, schedule, iv, encrypt=True)
print("Encrypted:", output_data.hex())