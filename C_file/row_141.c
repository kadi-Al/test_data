import ipaddress
import socket
import requests

def get_internal_ips():
    # Get all network interfaces and their IPs
    interfaces = socket.if_nameindex()
    internal_ips = []
    
    for interface, index in interfaces:
        try:
            if_addr = socket.if_addr(interface)
            for addr in if_addr:
                ip = addr['addr']
                net = ipaddress.ip_network(ip + '/24')  # Assuming /24 subnet mask for internal IPs
                if net.is_reserved or net.is_multicast or net.is_loopback:
                    continue
                internal_ips.append(ip)
        except Exception as e:
            print(f"Error getting IP address for interface {interface}: {e}")
    
    return internal_ips

def reveal_internal_ips():
    if not has_permission():
        raise PermissionError("You do not have permission to view internal IP addresses.")
    
    internal_ips = get_internal_ips()
    print("Internal IP Addresses:")
    for ip in internal_ips:
        print(ip)

def has_permission():
    # Implement your permission check here
    # For example, you might check if the user is an administrator or has a specific role
    return True  # Replace with actual permission logic

if __name__ == "__main__":
    try:
        reveal_internal_ips()
    except PermissionError as e:
        print(e)