package pages;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigReader;
import utilities.Driver;


public class MainPage {

    WebDriver driver;
    public MainPage() {
        PageFactory.initElements(driver,this);

    }
    Actions action;

    {
        action = new Actions(driver);
    }

    @FindBy(id="onetrust-accept-btn-handler")
    public WebElement acceptCookiesLocator;



@FindBy(xpath="//*[@class='default-input o-header__search--input']")
    public WebElement searchbox;


    @BeforeAll
    public void setUp (){

        Driver.getDriver().get(ConfigReader.getProperty("beymenUrl"));
        Driver.getDriver().manage().window().maximize();
        if (acceptCookiesLocator.isDisplayed()) {
            acceptCookiesLocator.click();
        }



    }
    public void clickEnter(WebElement element){
        action.click(element)
                .sendKeys(Keys.ENTER)
                .perform();
    }

    @AfterAll
    public void tearDown(){
        driver.quit();
    }

}
