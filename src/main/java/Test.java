import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {

    public static void main(String[] args) {
        // Automatically downloads and sets up ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Initialize ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Open YouTube
        driver.get("https://www.youtube.com");

        // Optional: Add a sleep to keep the window open
        try {
            Thread.sleep(60000); // Keep the window open for 60 seconds (adjust as needed)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Close the window
        driver.quit();
    }
}

