package pages;

import drivers.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by JRNORRIS on 10/1/15.
 */
public class Registration {

    static public void register(WebDriver d,String fname, String lname, String address, String phone, String email){


        Driver.sendkeys(Driver.findElement(d,By.id("given_name")),fname);
        Driver.sendkeys(Driver.findElement(d,By.id("family_name")), lname);
        Driver.sendkeys(Driver.findElement(d,By.id("street_address")),address);
        Driver.sendkeys(Driver.findElement(d,By.id("phone_number")),phone);
        Driver.sendkeys(Driver.findElement(d,By.id("email_address")),email);

        Driver.clickWait(Driver.findElement(d, By.xpath(".//*[@id='contact_fields']/button")),4000);
    }

}
