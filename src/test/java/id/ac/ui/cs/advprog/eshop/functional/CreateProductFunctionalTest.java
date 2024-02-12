package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    /**
     * The port number assigned to the running application during test execution.
     * Set automatically during each test run by Spring Framework's test context.
     */
    @LocalServerPort
    private int serverPort;

    /**
     * The base URL for testing. Default to (@code http://localhost).
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d/product/list", testBaseUrl, serverPort);
    }

    @Test
    void pageTitle_isCorrect(ChromeDriver driver) throws Exception {
        // Exercise
        driver.get(baseUrl);
        String pageTitle = driver.getTitle();

        // Verify
        assertEquals("Product List", pageTitle);
    }

    @Test
    void welcomeMessage_productListPage_isCorrect(ChromeDriver driver) throws Exception {
        // Exercise
        driver.get(baseUrl);
        String welcomeMessage = driver.findElement(By.tagName("h2")).getText();

        // Verify
        assertEquals("Product' List", welcomeMessage);
    }

    @Test
    void createProduct_isCorrect(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.tagName("a")).click();

        String pageTitle = driver.getTitle();
        assertEquals("Create New Product", pageTitle);

        String welcomeMessage = driver.findElement(By.tagName("h3")).getText();
        assertEquals("Create New Product", welcomeMessage);

        WebElement productName = driver.findElement(By.id("nameInput"));
        productName.clear();
        productName.sendKeys("Indomie");
        
        WebElement productQuantity = driver.findElement(By.id("quantityInput"));
        productQuantity.clear();
        productQuantity.sendKeys("10");

        driver.findElement(By.tagName("button")).click();

        pageTitle = driver.getTitle();
        assertEquals("Product List", pageTitle);

        welcomeMessage = driver.findElement(By.tagName("h2")).getText();
        assertEquals("Product' List", welcomeMessage);

        String productNameInList = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[1]")).getText();
        assertEquals(productNameInList, "Indomie");

        String productQuantityInList = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]")).getText();
        assertEquals(productQuantityInList, "10");
    }
}
