import java.util.Optional;

public byte[] getBuffer(HttpServletRequest req) {
    String sizeStr = req.getParameter("size");
    
    // Optional<Integer> to avoid NullPointerException if sizeStr is null or empty
    Optional<Integer> maybeSize = Optional.ofNullable(sizeStr).map(Integer::parseInt);
    
    // Check for presence and valid range
    int size;
    if (maybeSize.isPresent() && (size = maybeSize.get()) > 0) {
        byte[] buffer = new byte[size];
        return buffer;
    } else {
        // Handle invalid input: log, throw exception, or use a default value
        System.err.println("Invalid size parameter received.");
        throw new IllegalArgumentException("Size must be a positive integer.");
        // Alternatively, you could initialize with a safe small default size like 1024 bytes:
        // return new byte[1024];
    }
}