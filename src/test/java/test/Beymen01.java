package test;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.MainPage;
import utilities.Driver;
import utilities.ReusableMethods;
import logger.Log;


public class Beymen01 {

    MainPage mainPage = new MainPage();
    Log log = new Log();

    @Test (description = "Beymen UI Testi")
    public void beymenSeleniumTest() {
        log.info("Beymen UI Testi basladi");
        String path = "src/test/java/resources/testinium.xlsx";
        String sortKelimesi = ReusableMethods.hucreGetir(path, "Sayfa1", 0, 0).toString();
        String gomlekKelimesi = ReusableMethods.hucreGetir(path, "Sayfa1", 0, 1).toString();
        System.out.println("sortKelimesi = " + sortKelimesi);
        log.info("şort kelimesi excelden alindi");
        mainPage.setUp();
//        - Arama kutucuğuna “şort” kelimesi girilir.(Not = Şort kelimesi excel dosyası
//        üzerinden 1 sütun 1 satırdan alınarak yazılmalıdır. )
        mainPage.searchbox.sendKeys(sortKelimesi);
        //        - Arama kutucuğuna girilen “şort” kelimesi silinir.
        alanTemizle(mainPage.searchbox);
//        - Arama kutucuğuna “gömlek” kelimesi girilir.(Not = Gömlek kelimesi excel dosyası
//        üzerinden 2 sütun 1 satırdan alınarak yazılmalıdır. )
        log.info("gömlek kelimesi excelden alindi");
        mainPage.searchbox.sendKeys(gomlekKelimesi);
//        - Klavye üzerinden “enter” tuşuna bastırılır
        mainPage.pressEnter();
//        - Sonuca göre sergilenen ürünlerden rastgele bir ürün seçilir.
      //  mainPage.productSelect();
        mainPage.listedenRandomElementSec(mainPage.productList);
 //       mainPage.listedenRandomElementSec2(mainPage.acilanProductList);
        mainPage.bedenOlcusuSec();
//        - Seçilen ürünün ürün bilgisi ve tutar bilgisi txt dosyasına yazılır.
        //        - Seçilen ürün sepete eklenir.
//mainPage.urunveTutarBilgisiGetirveYazdir();
//        - Ürün sayfasındaki fiyat ile sepette yer alan ürün fiyatının doğruluğu karşılaştırılır.
        mainPage.sepeteGit();

mainPage.urunveTutarKarsilastir();
//        - Adet arttırılarak ürün adedinin 2 olduğu doğrulanır.
        mainPage.wait(1);
mainPage.sepetAdetSec();
        mainPage.wait(2);
        String sepetAdet = mainPage.sepetAdetDropDown.getAttribute("value");
        log.info("sepetAdet = " + sepetAdet);
        mainPage.Control(sepetAdet.equals("2"),"urun adedi 2 adet",
                "urun adedi 2 adet degil !!!  urun adedi="+ sepetAdet);
//        - Ürün sepetten silinerek sepetin boş olduğu kontrol edilir.
mainPage.sepettenSil.click();
mainPage.sepetBosMu();
        log.info("Beymen UI Testi sona erdi");
        Driver.getDriver().quit();

    }
    public void alanTemizle(WebElement element) {

        while (!element.getAttribute("value").equals("")) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

}