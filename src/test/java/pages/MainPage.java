package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import logger.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigReader;
import utilities.Driver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.time.Duration;
import static org.testng.AssertJUnit.assertEquals;


public class MainPage {

    WebDriverWait wait = null;

    ChromeOptions options = new ChromeOptions();
    Log log = new Log();
    private static int timeout = 5;

    public MainPage() {
        PageFactory.initElements(Driver.getDriver(), this);

    }

    public static final By COOKIE_BUTTON = By.id("onetrust-accept-btn-handler");

    public static final By ERKEK_BUTTON = By.id("genderManButton");

    public static final By PRODUCT_SELECTED = By.xpath("(//*[@class='col-sm-4 col-md-4 col-lg-4 col-xl-4 col-xxl-3 o-productList__itemWrapper'])[" + rnd() + "]");


    @FindBy(xpath = "(//*[text()='Tüm Çerezleri Kabul Et'])[1]")
    public WebElement acceptCookiesLocator2;

    @FindBy(xpath = "(//*[@class='a-primaryButton genderPopup__button'])[2]")
    public WebElement genderManButton;

    @FindBy(xpath = "//*[@class='default-input o-header__search--input']")
    public WebElement searchbox;

    @FindBy(xpath = "//*[@class='m-productCard__title']")
    public List<WebElement> productList;

    @FindBy(xpath = "//*[@class='m-productCard__desc']")
    public List<WebElement> acilanProductList;

    @FindBy(xpath = "//*[@class='m-productInfo__detail']")
    public WebElement productDetail;

    @FindBy(id = "priceNew")
    public WebElement price;

    @FindBy(id = "addBasket")
    public WebElement addBasket;


    @FindBy(xpath = "(//*[@class='o-header__userInfo--text'])[3]")
    public WebElement sepeteGit;

    @FindBy(xpath = "//*[@class='m-productPrice__salePrice']")
    public WebElement sepettekiFiyat;

    @FindBy(xpath = "//select[@class='a-selectControl -small']")
    public WebElement sepetAdetDropDown;

    @FindBy(id = "removeCartItemBtn0-key-0")
    public WebElement sepettenSil;

    @FindBy(xpath = "//*[text()='Sepetinizde Ürün Bulunmamaktadır']")
    public WebElement sepetteUrunBulunmamaktadir;


    public void setUp() {

//        - www.beymen.com sitesi açılır.
//        - Ana sayfanın açıldığı kontrol edilir.
        Driver.getDriver().get(ConfigReader.getProperty("beymenUrl"));
        wait(3);
        log.info(Driver.getDriver().getCurrentUrl() + "   test Url acildi");
        Control(Driver.getDriver().getCurrentUrl().contains("www.beymen.com"), "www.beymen.com url acildi",
                "www.beymen.com url acilamadi !!! acilan url=" + Driver.getDriver().getCurrentUrl());

        Driver.getDriver().manage().getCookies(); // Returns the List of all Cookies
        Driver.getDriver().manage().deleteAllCookies(); // Deletes all the cookies

//Add chrome switch to disable notification - "**--disable-notifications**"
        options.addArguments("--disable-notifications");


        try {
            wait(1);
            erkekBtnClick();
            options.addArguments("--disable-notifications");
            Driver.getDriver().manage().deleteAllCookies(); // Deletes all the cookies
            //   ifExistClick(genderManButton);
            //   ifExistClick(acceptCookiesLocator2);
        } catch (Exception e) {
            log.error("Erkek butonu tiklanamadi. ");
        }
        try {
            wait(1);
            cookieClick();
            options.addArguments("--disable-notifications");
            Driver.getDriver().manage().deleteAllCookies(); // Deletes all the cookies
            //   ifExistClick(genderManButton);
            //   ifExistClick(acceptCookiesLocator2);
        } catch (Exception e) {
            log.error("Cerezler kabul edilemedi.");
        }
        Driver.getDriver().manage().window().maximize();

    }

    public MainPage cookieClick() {
           click(COOKIE_BUTTON);
        return this;
    }
    public MainPage erkekBtnClick() {
     click(ERKEK_BUTTON);
     assertEquals("Beymen.com – Türkiye’nin Tek Dijital Lüks Platformu", Driver.getDriver().getTitle());
        return this;
    }

    public void click(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
        findElement(by).click();
    }

    public WebElement findElement(By by) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return Driver.getDriver().findElement(by);
    }

    public MainPage productSelect() {
        click(PRODUCT_SELECTED);
        return this;
    }

    public void ifExistClick(WebElement element) {
        try {
            wait(5);
            //waitForVisibility(element,5);
            if (element.isDisplayed()) {
                log.info(element.getText() + " elementi gozuktu");
                waitAndClick(element, 5);
                log.info(element.getText() + " elementine tiklandi");
            }

        } catch (Exception e) {
            log.error(element.getText()+"   elementine tiklanamadi");
        }
    }

    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public  void waitAndClick(WebElement element, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                log.info(element.getText()+" elementine tiklandi");
                return;
            } catch (WebDriverException e) {
                wait(1);
                log.error(element.getText()+" elementine tiklanamadi!!!");
            }
        }
    }

    public static void wait(int secs) {

        try {
            Thread.sleep(1000 * secs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pressEnter() {
        searchbox.sendKeys(Keys.ENTER);
    }


    public void sendKeys(WebElement element, String text) {
        sendKeys(element, text);
        pressEnter();
    }

    public void alanTemizle(WebElement element) {

        while (!element.getAttribute("value").equals("")) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

    public int belirliAraliktaSayiUret(int low, int high) {
        Random rand = new Random();

        int R = rand.nextInt(high - low) + low;

        return R;

    }

    public void listedenRandomElementSec(List<WebElement> list) {
        options.addArguments("--disable-notifications");
        int listedekiUrunSayisi = list.size();
        log.info("ilk listedekiUrunSayisi = " + listedekiUrunSayisi);
        int randomSayi = belirliAraliktaSayiUret(0, 3);
        String belirlenenUrunStr = String.valueOf(randomSayi);
        log.info("2. acilan product listesinde belirlenen urun " + belirlenenUrunStr + "  siradaki urundur");
        for (int i = 0; i <randomSayi ; i++) {
            String dinamikHucreXpath = "(//*[@class='m-productCard__title'])[" + belirlenenUrunStr + "]";
            if (Driver.getDriver().findElement(By.xpath(dinamikHucreXpath)).isDisplayed() ) {
                elementiGoreneKadarKaydirVeTikla(Driver.getDriver().findElement(By.xpath(dinamikHucreXpath)));
                break;
            }
        }
        wait(2);
        cookieKabul2();


    }

    public void listedenRandomElementSec2(List<WebElement> list) {
     //   cookieKabul2();
          options.addArguments("--disable-notifications");
        int listedekiUrunSayisi = list.size();
        log.info("ikinci listedekiUrunSayisi = " + listedekiUrunSayisi);

        int randomSayi = belirliAraliktaSayiUret(0, 3);
        String belirlenenUrunStr = String.valueOf(randomSayi);
        log.info("2. acilan product listesinde belirlenen urun " + belirlenenUrunStr + "  siradaki urundur");
        for (int i = 0; i <randomSayi ; i++) {
            String dinamikHucreXpath = "(//*[@class='m-productCard__desc'])[" + belirlenenUrunStr + "]";
            if (Driver.getDriver().findElement(By.xpath(dinamikHucreXpath)).isDisplayed() ) {
                elementiGoreneKadarKaydirVeTikla(Driver.getDriver().findElement(By.xpath(dinamikHucreXpath)));
                break;
            }
        }

        wait(2);
        cookieKabul2();

    }
    public static void scrollIntoVIewJS(WebElement element) {
        JavascriptExecutor jsexecutor = ((JavascriptExecutor) Driver.getDriver());
        jsexecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void elementiGoreneKadarKaydirVeTikla(WebElement element){
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript("arguments[0].scrollIntoView()",element);
        jse.executeScript("arguments[0].click();", element);
    }

    public void sepetAdetSec() {
        Select select = new Select(sepetAdetDropDown);
        select.selectByIndex(1);
        log.info("2 adet secildi");


    }

    static String urunBilgisi;
    static String tutarBilgisi;
    static String urunveTutar;
public void urunveTutarBilgisiGetirveYazdir(){
    cookieKabul2();
    wait(5);
    urunBilgisi = productDetail.getText();
    log.info("urunBilgisi = " + urunBilgisi);
    tutarBilgisi = price.getText();
    log.info("tutarBilgisi = " + tutarBilgisi);
    urunveTutar = urunBilgisi+" "+tutarBilgisi;
    txtDosyasinaYaz(urunveTutar);
    wait(5);
}

public void urunveTutarKarsilastir(){
    String sepettekiFiyatStr= sepettekiFiyat.getText();
    Control(tutarBilgisi.equals(sepettekiFiyatStr),
            "Sepetteki fiyat urun fiyatina esit",
            "Sepetteki fiyat urun fiyatina esit degil !!! Sepettekifiyat="+sepettekiFiyatStr+
                    "--Urun fiyati="+tutarBilgisi);
}


    public void txtDosyasinaYaz(String urunBilgisi) {
        String filename = "fileToRead.txt";
        FileWriter fw = null;
        try {
            fw = new FileWriter(filename, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);

            String text = urunBilgisi;
            out.write(text);
            log.info("txt dosyasina yazildi");
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//do stuff
//open streams again and write
    }

    public void Control(boolean statement, String onTrue, String onFalse) {
        if (statement == true) {
            log.info(onTrue);
        } else {
            log.info(onFalse);
        }
    }

    public void bedenOlcusuSec() {
        wait(5);
        for (int i = 1; i < 4; i++) {
            String dinamikHucreXpath = "(//*[@class='m-variation__item'])[" + i + "]";
            WebElement bedenOlcusu = Driver.getDriver().findElement(By.xpath(dinamikHucreXpath));
            if (bedenOlcusu.isDisplayed()) {
                bedenOlcusu.click();
                break;
            }
        }


    }

    public void sepetBosMu() {
        if (sepetteUrunBulunmamaktadir.isDisplayed()) {
            log.info("sepette urun bulunmamaktadir");
        } else {
            log.info("sepette urun mevcut");
        }


    }
    public static int rnd(){
        Random rnd=new Random();
        return rnd.nextInt(1,49);
    }

public void cookieKabul2 () {
    if (acceptCookiesLocator2.isDisplayed()) {
        acceptCookiesLocator2.click();
    }
}
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }
}


