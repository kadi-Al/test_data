public class MyRunnable implements Runnable {
    // This method will be executed by the thread
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Hello from MyRunnable " + i);
            try {
                Thread.sleep(1000); // Sleep for 1 second to simulate some work being done
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        // Create an instance of MyRunnable
        MyRunnable myRunnable = new MyRunnable();
        
        // Pass the Runnable to a Thread constructor and start the thread
        Thread thread = new Thread(myRunnable);
        
        // Start the thread
        thread.start();
    }
}