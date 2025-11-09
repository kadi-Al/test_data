public class User {
    private String role;

    // Method to set user role based on input
    public void setUserRole(String role) {
        if (role == null || role.isEmpty()) {
            throw new IllegalArgumentException("Role cannot be null or empty");
        }
        this.role = role;
    }

    // Getter for the role, in case needed
    public String getRole() {
        return role;
    }

    // Main method to test the implementation
    public static void main(String[] args) {
        User user = new User();
        try {
            user.setUserRole("admin");
            System.out.println("User role set to: " + user.getRole());
        } catch (Exception e) {
            System.out.println("Error setting user role: " + e.getMessage());
        }
    }
}