package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    private By registerLink = By.linkText("Register");
    private By firstName = By.id("customer.firstName");
    private By lastName = By.id("customer.lastName");
    private By address = By.id("customer.address.street");
    private By city = By.id("customer.address.city");
    private By state = By.id("customer.address.state");
    private By zip = By.id("customer.address.zipCode");
    private By phone = By.id("customer.phoneNumber");
    private By ssn = By.id("customer.ssn");
    private By username = By.id("customer.username");
    private By password = By.id("customer.password");
    private By confirm = By.id("repeatedPassword");
    private By registerBtn = By.xpath("//input[@value='Register']");

    public void register(String user, String pass) {

        driver.findElement(registerLink).click();

        driver.findElement(firstName).sendKeys("Test");
        driver.findElement(lastName).sendKeys("User");
        driver.findElement(address).sendKeys("Street");
        driver.findElement(city).sendKeys("City");
        driver.findElement(state).sendKeys("State");
        driver.findElement(zip).sendKeys("12345");
        driver.findElement(phone).sendKeys("9999999999");
        driver.findElement(ssn).sendKeys("123");

        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(confirm).sendKeys(pass);

        driver.findElement(registerBtn).click();
    }
}
