package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransferFundsPage {

    WebDriver driver;
    WebDriverWait wait;

    By transferFundsLink = By.linkText("Transfer Funds");
    By amountField = By.id("amount");
    By transferButton = By.xpath("//input[@value='Transfer']");
    By confirmationText = By.xpath("//*[contains(text(),'Transfer Complete')]");

    public TransferFundsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void transferAmount(String amount) {

        wait.until(ExpectedConditions.elementToBeClickable(transferFundsLink))
            .click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(amountField))
            .clear();
        driver.findElement(amountField).sendKeys(amount);

        wait.until(ExpectedConditions.elementToBeClickable(transferButton))
            .click();
    }
    
    

    public boolean isTransferSuccessful() {

        WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(15));

        return wait.until(driver ->
            driver.getPageSource().contains("Transfer Complete")
            || driver.getPageSource().contains("was successfully transferred")
        );
    }

}
