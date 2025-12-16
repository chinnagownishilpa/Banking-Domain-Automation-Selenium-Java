package stepdefinitions.flow;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Pages.LoginPage;
import Pages.RegisterPage;
import Pages.TransferFundsPage;
import stepdefinitions.context.TestContext;
import stepdefinitions.hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CustomerFlowSteps {

    /* ================= REGISTER ================= */

    @When("user registers a new customer")
    public void registerCustomer() {

        TestContext.username = "user" + System.currentTimeMillis();
        TestContext.password = "Pass@123";

        RegisterPage registerPage = new RegisterPage(Hooks.driver);
        registerPage.register(TestContext.username, TestContext.password);
    }

    @Then("customer should be registered successfully")
    public void verifyRegistration() {

        Assert.assertTrue(
            Hooks.driver.getPageSource().contains("Welcome"),
            "Registration not successful"
        );
    }

    /* ================= LOGIN ================= */

    @When("user logs in with registered credentials")
    public void loginRegisteredUser() {

        LoginPage loginPage = new LoginPage(Hooks.driver);
        loginPage.login(TestContext.username, TestContext.password);
    }

    @When("user handles post login error if present")
    public void handleLoginErrorIfPresent() {

        if (Hooks.driver.getPageSource()
                .contains("An internal error has occurred")) {
            System.out.println(
                "Known ParaBank error detected. Continuing execution."
            );
        }
    }

    @Then("user should be logged in successfully")
    public void verifyLogin() {

        WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(10));

        boolean loggedIn =
            Hooks.driver.getPageSource().contains("Log Out") ||
            Hooks.driver.getPageSource().contains("Accounts Overview") ||
            Hooks.driver.getPageSource().contains("Transfer Funds");

        Assert.assertTrue(loggedIn, "User not logged in");
    }


    /* ================= TRANSFER ================= */

    @When("user navigates to Transfer Funds page")
    public void navigateToTransferFunds() {

        WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(15));

        // Ignore ParaBank internal error if shown
        if (Hooks.driver.getPageSource().contains("An internal error has occurred")) {
            System.out.println("Ignoring ParaBank internal error");
        }

        By transferFundsLink = By.linkText("Transfer Funds");

        wait.until(ExpectedConditions.presenceOfElementLocated(transferFundsLink));
        wait.until(ExpectedConditions.elementToBeClickable(transferFundsLink)).click();
    }


    @When("user transfers {string}")
    public void transferAmount(String amount) {

        TransferFundsPage transferPage =
            new TransferFundsPage(Hooks.driver);

        transferPage.transferAmount(amount);
    }

    @Then("transfer should be successful")
    public void verifyTransfer() {

        WebDriverWait wait =
            new WebDriverWait(Hooks.driver, Duration.ofSeconds(15));

        boolean success =
            wait.until(driver ->
                driver.getPageSource().contains("Transfer Complete") ||
                driver.getPageSource().contains("has been transferred")
            );

        Assert.assertTrue(success, "Transfer failed");
    }
}
