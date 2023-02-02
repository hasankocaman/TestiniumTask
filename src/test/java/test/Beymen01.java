package test;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.MainPage;
import utilities.ReusableMethods;
import logger.Log;


public class Beymen01 {

    MainPage mainPage = new MainPage();
    Log log = new Log();

    @Test
    public void beymenSeleniumTest() {
        String path = "src/test/java/resources/testinium.xlsx";
        String sortKelimesi = ReusableMethods.hucreGetir(path, "Sayfa1", 0, 0).toString();
        String gomlekKelimesi = ReusableMethods.hucreGetir(path, "Sayfa1", 0, 1).toString();
        System.out.println("sortKelimesi = " + sortKelimesi);
        log.info("sort kelimesi gozuktu");
        mainPage.setUp();
//        - Arama kutucuğuna “şort” kelimesi girilir.(Not = Şort kelimesi excel dosyası
//        üzerinden 1 sütun 1 satırdan alınarak yazılmalıdır. )
        mainPage.searchbox.sendKeys(sortKelimesi);
        //        - Arama kutucuğuna girilen “şort” kelimesi silinir.
        alanTemizle(mainPage.searchbox);
//        - Arama kutucuğuna “gömlek” kelimesi girilir.(Not = Gömlek kelimesi excel dosyası
//        üzerinden 2 sütun 1 satırdan alınarak yazılmalıdır. )
        mainPage.searchbox.sendKeys(gomlekKelimesi);
//        - Klavye üzerinden “enter” tuşuna bastırılır
        mainPage.pressEnter();
//        - Sonuca göre sergilenen ürünlerden rastgele bir ürün seçilir.
        mainPage.listedenRandomElementSec(mainPage.productList);
        mainPage.listedenRandomElementSec2(mainPage.acilanProductList);
//        - Seçilen ürünün ürün bilgisi ve tutar bilgisi txt dosyasına yazılır.
String urunBilgisi = mainPage.productDetail.getText();
        log.info("urunBilgisi = " + urunBilgisi);
String tutarBilgisi = mainPage.price.getText();
        log.info("tutarBilgisi = " + tutarBilgisi);
String urunveTutar = urunBilgisi+" "+tutarBilgisi;
mainPage.txtDosyasinaYaz(urunveTutar);
        mainPage.wait(5);
//        - Seçilen ürün sepete eklenir.
        mainPage.bedenOlcusuSec();
        mainPage.addBasket.click();
        mainPage.wait(5);
        mainPage.sepeteGit.click();
        mainPage.wait(5);
String sepettekiFiyatStr= mainPage.sepettekiFiyat.getText();
//        - Ürün sayfasındaki fiyat ile sepette yer alan ürün fiyatının doğruluğu karşılaştırılır.
mainPage.Control(tutarBilgisi.equals(sepettekiFiyatStr),
        "Sepetteki fiyat urun fiyatina esit",
        "Sepetteki fiyat urun fiyatina esit degil !!! Sepettekifiyat="+sepettekiFiyatStr+
        "--Urun fiyati="+tutarBilgisi);
//        - Adet arttırılarak ürün adedinin 2 olduğu doğrulanır.
mainPage.sepetAdetSec();
        mainPage.wait(2);
        String sepetAdet = mainPage.sepetAdetDropDown.getAttribute("value");
        log.info("sepetAdet = " + sepetAdet);
        mainPage.Control(sepetAdet.equals("2"),"urun adedi 2 adet",
                "urun adedi 2 adet degil !!!  urun adedi="+ sepetAdet);
//        - Ürün sepetten silinerek sepetin boş olduğu kontrol edilir.
mainPage.sepettenSil.click();
mainPage.sepetBosMu();

    }
    public void alanTemizle(WebElement element) {

        while (!element.getAttribute("value").equals("")) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

}