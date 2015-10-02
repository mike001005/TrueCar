package pages;

import drivers.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by JRNorris on 9/30/15.
 */
public class AutoBlogHomePage {

public static ArrayList<String> CarandZip(String url,String make,String model,String year,String zip,String driver){

    ArrayList<String> fails = new ArrayList<String>();

   WebDriver d =  Driver.getDriver(driver);

    //Make drop down//
    d.get(url);
    Driver.click(Driver.findElement(d, By.id("s2id_home_select_make")).findElement(By.className("select2-choice")));

    List<WebElement> pageMakes = Driver.findElement(d, By.id("select2-drop")).findElement(By.className("select2-results"))
            .findElements(By.tagName("li"));

    for(WebElement pageMake: pageMakes){

        if(pageMake.getText().equals(make)){
            Driver.click(pageMake);
            break;
        }

    }

    String carModel = null;
    //Model drop down//
    Driver.click(Driver.findElement(d, By.id("s2id_home_select_model")).findElement(By.className("select2-choice")));

    List<WebElement> years = Driver.findElement(d, By.id("select2-drop")).findElement(By.className("select2-results"))
            .findElements(By.className("select2-results-dept-0"));

    for(WebElement pageYear: years){

        if(carModel != null){break;}

        if(pageYear.findElement(By.className("select2-result-label")).getText().equals(year)){

           List<WebElement> pageModels = pageYear.findElement(By.className("select2-result-sub")).findElements(By.tagName("li"));

            for(WebElement pageModel: pageModels){

                if(pageModel.getText().equals(model)){
                    carModel = pageModel.getText();
                    Driver.click(pageModel);
                    break;
                }

            }
        }

    }

    //zip code//
    Driver.sendkeys(Driver.findElement(d, By.id("home_zip")), zip);

    //Button//
    Driver.click(Driver.findElement(d, By.id("home_cta")));

    String car = Driver.findElement(d, By.id("config-header")).getText();

    if(!car.equals(year + " " + make + " " + model)) fails.add("Car make/module/year was not passed" + car);

    String pageZip = Driver.findElement(d,By.className("zip-view")).getText();

    if(!pageZip.contains(zip)){
        fails.add("Zip was not passed " + pageZip);
    }

    d.quit();

    return fails;

}


}
