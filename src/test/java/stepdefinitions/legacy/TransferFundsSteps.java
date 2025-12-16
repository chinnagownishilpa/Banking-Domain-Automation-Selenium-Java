package stepdefinitions.legacy;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Pages.LoginPage;
import Pages.TransferFundsPage;
import Utils.ExcelUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import stepdefinitions.hooks.Hooks;

public class TransferFundsSteps {

    TransferFundsPage transferPage;

    @Given("user is logged into ParaBank")
    public void login() {

        LoginPage loginPage = new LoginPage(Hooks.driver);

        String user = ExcelUtil.getCellData("Login", 1, 0);
        String pass = ExcelUtil.getCellData("Login", 1, 1);

        loginPage.login(user, pass);

        WebDriverWait wait =
            new WebDriverWait(Hooks.driver, Duration.ofSeconds(15));

        // Strong login confirmation
        wait.until(ExpectedConditions
            .presenceOfElementLocated(By.linkText("Accounts Overview")));

        transferPage = new TransferFundsPage(Hooks.driver);
    }

    @When("user transfers amount using excel data")
    public void transferAmount() {
        transferPage.transferAmount(
            ExcelUtil.getCellData("Transfer", 1, 0)
        );
    }

    @Then("transfer should be successful")
    public void verifyTransfer() {
        Assert.assertTrue(
            transferPage.isTransferSuccessful(),
            "Transfer failed"
        );
    }
}
