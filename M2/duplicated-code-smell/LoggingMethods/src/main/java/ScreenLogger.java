import java.time.LocalDateTime;

public class ScreenLogger {
    public void timeStampedLog(String message) {
        System.out.println("Screen log for " + message + ": at timestamp: " + LocalDateTime.now());
    }
    public void logToScreen(String message) {
        System.out.println("Screen log for " + message);
    }
}
