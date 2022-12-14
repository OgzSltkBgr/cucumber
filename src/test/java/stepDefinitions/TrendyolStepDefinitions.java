package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import pages.TrendyolPage;
import utilities.Driver;
import utilities.ReusableMethods;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TrendyolStepDefinitions {
    TrendyolPage trendyolPage=new TrendyolPage();
    Actions actions=new Actions(Driver.getDriver());
    Select select;
    @Then("trendyol ana sayfasindaki giris yap butonuna tiklar")
    public void trendyolAnaSayfasindakiGirisYapButonunaTiklar() {
        trendyolPage.girisYapButonu.click();
    }

    @Then("eposta ve sifre boxlarini doldurur ve giris yapar")
    public void epostaVeSifreBoxlariniDoldururVeGirisYapar() {
        //ReusableMethods.waitFor(1);
        trendyolPage.eposta.sendKeys("oguzsaltuktest@gmail.com");
       // ReusableMethods.waitFor(1);
        trendyolPage.sifre.sendKeys("Seleniumtest01",Keys.ENTER);
       // ReusableMethods.waitFor(1);

    }
    @Then("Elektronik basligi altinda Bilgisayar & Tablet alt basligina tiklar")
    public void elektronik_basligi_altinda_bilgisayar_tablet_alt_basligina_tiklar() {
        ReusableMethods.waitFor(1);
        actions.moveToElement(trendyolPage.elektronikBaslik).perform();
        ReusableMethods.waitFor(1);
        trendyolPage.bilgisayarTablet.click();
    }

    @Then("sol taraftaki ilgili kategorilerden Bilgisayar a tiklar")
    public void solTaraftakiIlgiliKategorilerdenATiklar() {
        ReusableMethods.waitFor(1);
        trendyolPage.bilgisayar.click();
    }

    @Then("sol tarafta marka kategorisi altinda Apple i secer")
    public void solTaraftaMarkaKategorisiAltindaISecer() {
        ReusableMethods.waitFor(1);
        trendyolPage.appleCheckBox.click();
    }

    @And("ilk urune tiklar")
    public void ilkUruneTiklar() {
        ReusableMethods.waitFor(1);
        actions.sendKeys(Keys.DOWN).perform();
        trendyolPage.ilkUrun.click();
        ArrayList<String> windows = new ArrayList<>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(windows.get(1));
    }

    @And("kullanici urunu incelemek icin urun fotografinin uzerine mouse'la gider")
    public void kullaniciUrunuIncelemekIcinUrunFotografininUzerineMouseLaGider() {
        ReusableMethods.waitFor(1);
        actions.sendKeys(Keys.DOWN).perform();
        actions.moveToElement(trendyolPage.urunResmi).perform();
    }
    @And("urun fotografinin altindaki urunun diger fotograflarini inceler")
    public void urunFotografininAltindakiUrununDigerFotograflariniInceler() {
        ReusableMethods.waitFor(1);
        actions.sendKeys(Keys.DOWN).perform();
        List<WebElement> urunFotolari= trendyolPage.urunFotolariList;
        for (WebElement w:urunFotolari
             ) {
            ReusableMethods.waitFor(1);
            actions.click(w).perform();
            ReusableMethods.waitFor(1);
        }
    }
    @And("urunu sepete ekler")
    public void urunuSepeteEkler() {
        ReusableMethods.waitFor(1);
        trendyolPage.sepeteEkle.click();
    }
    @And("sepete gider")
    public void sepeteGider() {
        ReusableMethods.waitFor(1);
        trendyolPage.sepetim.click();
    }

    @And("sepeti onaylar")
    public void sepetiOnaylar() {
        ReusableMethods.waitFor(1);
        trendyolPage.anladim.click();
        ReusableMethods.waitFor(1);
        trendyolPage.sepetiOnayla.click();
    }

    @And("test adresi radio butonunun secili oldugunu dogrular")
    public void testAdresiRadioButonununSeciliOldugunuDogrular() {
        ReusableMethods.waitForClickablility(trendyolPage.testAdresiRadioButton, 5);
    }
    @And("kaydet ve devam et butonuna tiklar")
    public void kaydetVeDevamEtButonunaTiklar() {
        ReusableMethods.waitFor(1);
        trendyolPage.kaydetDevamEt.click();
    }

    @And("odeme secenekleri sayfasinda oldugunu dogrular")
    public void odemeSecenekleriSayfasindaOldugunuDogrular() {
        ReusableMethods.waitFor(1);
        Assert.assertTrue(trendyolPage.odemeSecenekleri.isDisplayed());
    }

    @And("kart bilgilerini girer")
    public void kartBilgileriniGirer() {
        Faker faker = new Faker();
        ReusableMethods.waitFor(1);
        Driver.getDriver().switchTo().frame(trendyolPage.iframeKart);
        trendyolPage.kartBilgileri.sendKeys(faker.number().digits(16));
        actions.sendKeys(Keys.TAB).sendKeys("10")
                .sendKeys(Keys.TAB).sendKeys("2025")
                .sendKeys(Keys.TAB).sendKeys(Keys.TAB)
                .sendKeys(faker.number().digits(3)).perform();

   }

    @And("Lutfen belirtilen alanlari kontrol ediniz uyarisini dogrular")
    public void lutfenBelirtilenAlanlariKontrolEdinizUyarisiniDogrular() {
        ReusableMethods.waitFor(2);
        Driver.getDriver().switchTo().defaultContent();
        Assert.assertTrue(trendyolPage.uyari.isDisplayed());
    }

    @And("siparis ozeti alaninin ekran resmini alir")
    public void siparisOzetiAlanininEkranResminiAlir() throws IOException {
        ReusableMethods.waitFor(1);
        ReusableMethods.getScreenshotWebElement("siparis ozet",trendyolPage.siparisOzeti);
    }
    @Then("cerezleri kabul eder")
    public void cerezleriKabulEder() {
        ReusableMethods.waitFor(1);
        trendyolPage.cerez.click();
    }

    @And("sayfalari kapatir")
    public void sayfalariKapatir() {
        ReusableMethods.waitFor(1);
        Driver.quitDriver();
    }
}
