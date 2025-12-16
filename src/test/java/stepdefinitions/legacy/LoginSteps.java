package stepdefinitions.legacy;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import stepdefinitions.hooks.Hooks;

public class LoginSteps {

    LoginPage loginPage;

    @Given("user launches ParaBank application")
    public void lunchApp() {
        loginPage = new LoginPage(Hooks.driver);
    }
    
    @When("user logs in using {string} and {string}")
    public void login(String username, String password) {

        LoginPage loginPage = new LoginPage(Hooks.driver);
        loginPage.login(username, password);

       
        WebDriverWait wait =
                new WebDriverWait(Hooks.driver, Duration.ofSeconds(15));
        
        // Fail fast if login error appears
        if (Hooks.driver.getPageSource()
                .contains("could not be verified")) {
            Assert.fail("Login failed: Invalid username or password");
        }
        wait.until(driver ->
        driver.getPageSource().contains("Welcome")
        || driver.findElements(By.linkText("Accounts Overview")).size() > 0
    );

    }


    @Then("user should login successfully")
    public void verifyLogin() {
        Assert.assertTrue(
            Hooks.driver.getPageSource().contains("Welcome"),
            "Login failed"
        );
    }
}
