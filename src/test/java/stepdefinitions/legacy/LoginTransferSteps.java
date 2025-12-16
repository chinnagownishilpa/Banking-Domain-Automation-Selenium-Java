package stepdefinitions.legacy;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Pages.RegisterPage;
import Pages.TransferFundsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import stepdefinitions.hooks.Hooks;

public class LoginTransferSteps {

    TransferFundsPage transferPage;
    static String generatedUser;
    static String generatedPass;

    @Given("user launches ParaBank application")
    public void launchApp() {
        Hooks.driver.get("https://parabank.parasoft.com/parabank/index.htm");
    }

    @Given("user registers a new account")
    public void registerUser() {

        generatedUser = "user" + System.currentTimeMillis();
        generatedPass = "Pass@123";

        RegisterPage registerPage = new RegisterPage(Hooks.driver);
        registerPage.register(generatedUser, generatedPass);
    }
    @And("user handles post registration error if present")
    public void handlePostRegistrationError() {

        WebDriverWait wait =
            new WebDriverWait(Hooks.driver, Duration.ofSeconds(8));

        try {
            // Wait briefly to allow error to appear
            wait.until(driver ->
                driver.getPageSource().contains("An internal error")
            );

            // Do nothing — error is non-blocking
            System.out.println("Post-registration error detected. Ignoring.");

        } catch (Exception e) {
            // Error did not appear — perfectly fine
            System.out.println("No post-registration error shown.");
        }

        // Small stabilization pause (important for ParaBank)
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {}
    }


    @When("user is logged in after registration")
    public void verifyUserLoggedIn() {

        WebDriverWait wait =
            new WebDriverWait(Hooks.driver, Duration.ofSeconds(15));

        // Login is confirmed if left menu exists
        wait.until(ExpectedConditions
            .visibilityOfElementLocated(By.linkText("Accounts Overview")));
    }


    
    @And("user navigates to Transfer Funds page")
    public void navigateToTransferFunds() {

        WebDriverWait wait =
            new WebDriverWait(Hooks.driver, Duration.ofSeconds(15));

        // Re-locate menu after error popup
        wait.until(ExpectedConditions
            .elementToBeClickable(By.linkText("Transfer Funds")));

        Hooks.driver.findElement(By.linkText("Transfer Funds")).click();

        // Confirm page usability
        wait.until(ExpectedConditions
            .visibilityOfElementLocated(By.name("amount")));
    }

    @When("user transfers {string}")
    public void transferAmount(String amount) {

        transferPage = new TransferFundsPage(Hooks.driver);
        transferPage.transferAmount(amount);
    }

    @Then("transfer should be successful")
    public void verifyTransfer() {

        Assert.assertTrue(
            transferPage.isTransferSuccessful(),
            "Transfer was not successful"
        );
    }
    
    
}
