package pages;

import drivers.Driver;
import org.openqa.selenium.*;

/**
 * Created by JRNorris on 10/1/15.
 */
public class Certificate {

    public static void print(WebDriver d){




       Driver.clickWait(Driver.findElement(d, By.className("header-container")).findElement(By.className("button")),4000);

        WebElement html = d.findElement(By.tagName("html"));
        html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
        html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
        html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
        html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
        html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
        html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

        String price = Driver.findElement(d, By.xpath(".//*[@id='pageContainer2']/xhtml:div[2]/xhtml:div[14]")).getText();

        String DealerShipName = Driver.findElement(d, By.xpath(".//*[@id='pageContainer1']/xhtml:div[2]/xhtml:div[43]")).getText();
        String DealerShipStreet = Driver.findElement(d,By.xpath(".//*[@id='pageContainer1']/xhtml:div[2]/xhtml:div[44]")).getText();
        String DealerShipCityZip = Driver.findElement(d, By.xpath(".//*[@id='pageContainer1']/xhtml:div[2]/xhtml:div[45]")).getText();

        String extColorTitle = Driver.findElement(d, By.xpath(".//*[@id='pageContainer2']/xhtml:div[2]/xhtml:div[16]")).getText();
        String extColor = Driver.findElement(d, By.xpath(".//*[@id='pageContainer2']/xhtml:div[2]/xhtml:div[17]")).getText();
        String intColorTitle = Driver.findElement(d, By.xpath(".//*[@id='pageContainer2']/xhtml:div[2]/xhtml:div[18]")).getText();
        String intColor = Driver.findElement(d, By.xpath(".//*[@id='pageContainer2']/xhtml:div[2]/xhtml:div[19]")).getText();
        String optionsTitle = Driver.findElement(d, By.xpath(".//*[@id='pageContainer2']/xhtml:div[2]/xhtml:div[20]")).getText();
        String options = Driver.findElement(d, By.xpath(".//*[@id='pageContainer2']/xhtml:div[2]/xhtml:div[21]")).getText() +
               "\n" + Driver.findElement(d, By.xpath(".//*[@id='pageContainer2']/xhtml:div[2]/xhtml:div[22]")).getText();


        html.sendKeys(Keys.chord(Keys.CONTROL, "0"));


        System.out.println("*************** Dealer ***************" + "\n");

            System.out.println(DealerShipName);
            System.out.println(DealerShipStreet);
            System.out.println(DealerShipCityZip + "\n");

        System.out.println("*************** Dealer ***************" + "\n");


        System.out.println("*************** Options ***************" + "\n");

         System.out.println(extColorTitle + " " + extColor);
         System.out.println(intColorTitle +" "+intColor);
         System.out.println(optionsTitle +" "+ options + "\n");

        System.out.println("*************** Options ***************" + "\n");


        System.out.println("*************** Price ***************" + "\n");

        System.out.println(price + "\n");

        System.out.println("*************** Price ***************" + "\n");

    }

}
