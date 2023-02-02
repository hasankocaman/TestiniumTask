package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.MainPage;
import utilities.ReusableMethods;

public class Beymen01 {

    WebDriver driver;
    MainPage mainPage= new MainPage();


    @Test
    public void search_a_product(){

        String path="src/test/java/resources/testinium.xlsx";

        String sortKelimesi= ReusableMethods.hucreGetir(path,"Sayfa1",0,0).toString();
        mainPage.searchbox.sendKeys(sortKelimesi);
        mainPage.clickEnter(mainPage.searchbox);

    }
}
