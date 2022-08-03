import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class Enter {
    public WebDriver driver;
    public Enter(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver=driver;
    }
    public void login(String login) throws InterruptedException {
        WebElement enterLogin =driver.findElement(By.xpath("//*[@id=\"email\"]"));
        enterLogin.sendKeys(login);
    }
    public void password(String password) throws InterruptedException {
        WebElement enterPassword = driver.findElement(By.xpath("//*[@id=\"pass\"]"));
        enterPassword.sendKeys(password);
    }
    public void eye(){
        WebElement seePassword=driver.findElement(By.xpath("//div[@class=\"_9lsa\"]"));
        seePassword.click();
    }
    public void enterBtn() throws InterruptedException {
        WebElement enterKeys = driver.findElement(By.xpath("//button[@data-testid=\"royal_login_button\"]"));
        enterKeys.click();
    }
    public void checkError(String xpass) throws InterruptedException {
        WebElement checkErr = driver.findElement(By.xpath(xpass));
        checkErr.isDisplayed();
    }    public void nummberPeople(String number){
        System.out.println(number);
    }
}
