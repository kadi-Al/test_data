import yaml
from flask import Flask, request, jsonify

app = Flask(__name__)

# Mock function to simulate fetching the price based on product code.
def get_price_for_product_code(product_code):
    # In a real application, this could be an API call or database query.
    prices = {
        "A123": 10.00,
        "B456": 20.00,
        "C789": 30.00
    }
    return prices.get(product_code, None)

@app.route('/calculate-price', methods=['GET'])
def calculate_price():
    # Extract the YAML payload from the query parameter.
    yaml_payload = request.args.get('payload')
    
    if not yaml_payload:
        return jsonify({"error": "No payload provided"}), 400
    
    try:
        payload_dict = yaml.safe_load(yaml_payload)
    except yaml.YAMLError as e:
        return jsonify({"error": f"Invalid YAML format: {e}"}), 400
    
    # Extract product code and quantity from the payload.
    product_code = payload_dict.get('product_code')
    quantity = payload_dict.get('quantity', 1)  # Default to 1 if not provided
    
    if not product_code:
        return jsonify({"error": "Product code is missing"}), 400
    
    # Fetch the price for the product code.
    price = get_price_for_product_code(product_code)
    
    if price is None:
        return jsonify({"error": "Unknown product code"}), 404
    
    # Calculate total price.
    total_price = price * quantity
    
    # Return the result as a YAML payload.
    response = {
        "total_price": total_price,
        "product_code": product_code,
        "quantity": quantity
    }
    
    return jsonify(response)

if __name__ == '__main__':
    app.run(debug=True)