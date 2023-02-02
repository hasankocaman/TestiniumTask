package pages;

import logger.Log;
import org.openqa.selenium.*;
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
import java.util.List;
import java.util.Random;
import org.openqa.selenium.support.ui.Select;


public class MainPage {


    Log log = new Log();
    private static int timeout = 5;

    public MainPage() {
        PageFactory.initElements(Driver.getDriver(), this);

    }


    @FindBy(id = "onetrust-accept-btn-handler")
    public WebElement acceptCookiesLocator;

    @FindBy(xpath = "(//*[text()='Tüm Çerezleri Kabul Et'])[1]")
    public WebElement acceptCookiesLocator2;

    @FindBy(xpath = "(//*[@class='a-primaryButton genderPopup__button'])[2]")
    public WebElement genderManButton;

    @FindBy(xpath = "//*[@class='default-input o-header__search--input']")
    public WebElement searchbox;

    @FindBy(xpath = "//*[@class='m-productCard__title']")
    public List <WebElement> productList;

    @FindBy(xpath = "//*[@class='m-productCard__desc']")
    public List <WebElement> acilanProductList;

    @FindBy(xpath = "//*[@class='m-productInfo__detail']")
    public WebElement productDetail;

    @FindBy(id = "priceNew")
    public WebElement price;

    @FindBy(id = "addBasket")
    public WebElement addBasket;

//    @FindBy(xpath = "(//*[@class='m-variation__item'])[2]")
//    public WebElement bedenOlcusu;

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
        log.info(Driver.getDriver().getCurrentUrl() + "   test Url acildi");
        Control(Driver.getDriver().getCurrentUrl().contains("www.beymen.com"),"www.beymen.com url acildi",
                "www.beymen.com url acilamadi !!! acilan url="+Driver.getDriver().getCurrentUrl());
        Driver.getDriver().manage().window().maximize();

        try {

            ifExistClick(genderManButton);
            ifExistClick(acceptCookiesLocator2);
        } catch (Exception e) {
            log.error("Something went wrong.");
        }

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
            log.error("Something went wrong.");
        }
    }

    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitAndClick(WebElement element, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                wait(1);
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

//    public void clickEnter(WebElement element){
//        action.click(element)
//                .sendKeys(Keys.ENTER)
//                .perform();
//    }

    public void sendKeys(WebElement element, String text) {
        sendKeys(element, text);
        pressEnter();
    }

    public void alanTemizle(WebElement element) {

        while (!element.getAttribute("value").equals("")) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }
    public int belirliAraliktaSayiUret (int low, int high){
        Random rand = new Random();

        int R = rand.nextInt(high-low) + low;

        return R;

    }

    public void listedenRandomElementSec(List<WebElement> list){
        int listedekiUrunSayisi = list.size();
        log.info("listedekiUrunSayisi = " + listedekiUrunSayisi);
        int belirlenenUrun = belirliAraliktaSayiUret(0,list.size()-1);
        String belirlenenUrunStr = String.valueOf(belirlenenUrun);
        log.info("belirlenen urun "+ belirlenenUrunStr + "  siradaki urundur");
         String dinamikHucreXpath = "(//*[@class='m-productCard__title'])["+belirlenenUrunStr+"]";
        waitAndClick(Driver.getDriver().findElement(By.xpath(dinamikHucreXpath)),5);

//        try {
//
//
//            //log.info("listeden belirlenen urun = "+ button.getText());
//            //list.get(belirlenenUrun).click();
//            if (button.isDisplayed()) {
//                wait(2);
//                button.click();
//            }
//        }
//        catch(org.openqa.selenium.StaleElementReferenceException ex)
//        {
//            WebElement button = Driver.getDriver().findElement(By.xpath(dinamikHucreXpath));
////            log.info("listeden belirlenen urun = "+ button.getText());
////            list.get(belirlenenUrun).click();
//            if (button.isDisplayed()) {
//                wait(2);
//                button.click();
//            }
//
//        }

    }

    public void listedenRandomElementSec2(List<WebElement> list){
        int listedekiUrunSayisi = list.size();
        log.info("listedekiUrunSayisi = " + listedekiUrunSayisi);

        int belirlenenUrun = belirliAraliktaSayiUret(0,list.size()-1);
        String belirlenenUrunStr = String.valueOf(belirlenenUrun);
        log.info("belirlenen urun "+ belirlenenUrunStr + "  siradaki urundur");
        String dinamikHucreXpath = "(//*[@class='m-productCard__desc'])["+belirlenenUrunStr+"]";
        waitAndClick(Driver.getDriver().findElement(By.xpath(dinamikHucreXpath)),5);

//        try {
//            WebElement button = Driver.getDriver().findElement(By.xpath(dinamikHucreXpath));
////            list.get(belirlenenUrun).click();
//            if (button.isDisplayed()) {
//                log.info("listeden belirlenen urun = "+ button.getText());
//                wait(2);
//                button.click();
//            }
//        }
//        catch(org.openqa.selenium.StaleElementReferenceException ex)
//        {
////            WebElement button = Driver.getDriver().findElement(By.xpath(dinamikHucreXpath));
////            log.info("listeden belirlenen urun = "+ button.getText());
//            list.get(belirlenenUrun).click();
//            //button.click();
//        }

    }

    public void sepetAdetSec (){
        Select select = new Select(sepetAdetDropDown);
        select.selectByIndex(1);
        log.info("2 adet secildi");


    }

    public void txtDosyasinaYaz (String urunBilgisi) {
        String filename="fileToRead.txt";
        FileWriter fw=null;
        try {
            fw = new FileWriter(filename,true);
            BufferedWriter bw=new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);

            String text=urunBilgisi;
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
            log.info(onTrue);    }
        else {
            log.info(onFalse);
                }
    }

    public void bedenOlcusuSec(){
        wait(3);
        for (int i = 1; i <4 ; i++) {
            String dinamikHucreXpath = "(//*[@class='m-variation__item'])["+i+"]";
            WebElement bedenOlcusu = Driver.getDriver().findElement(By.xpath(dinamikHucreXpath));
            if (bedenOlcusu.isDisplayed()) {
                bedenOlcusu.click();
                break;
            }
        }



    }

    public void sepetBosMu (){
        if (sepetteUrunBulunmamaktadir.isDisplayed()) {
            log.info("sepette urun bulunmamaktadir");
        } else {
            log.info("sepette urun mevcut");
        }
    }

}
