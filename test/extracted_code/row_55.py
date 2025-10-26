import base64
import os

def upload_image(file_path, output_file):
    """
    Reads an image from `file_path`, encodes it in Base64, and writes the encoded image to `output_file`.
    
    :param file_path: Path to the input image file.
    :param output_file: Path where the base64-encoded image will be saved.
    """
    try:
        with open(file_path, 'rb') as image_file:
            encoded_string = base64.b64encode(image_file.read()).decode('utf-8')
        
        with open(output_file, 'w') as output_file:
            output_file.write(encoded_string)
            
        print("Image successfully uploaded and encoded to Base64.")
    except Exception as e:
        print(f"An error occurred: {e}")

# Example usage:
upload_image('path/to/your/image.jpg', 'path/to/output/file.txt')