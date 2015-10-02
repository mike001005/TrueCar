package pages;

import drivers.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by JRNorris on 10/1/15.
 */
public class Dealer {

    public static void selection(WebDriver d){

        ArrayList<Integer> dealerPrice = new ArrayList<>();
        List<WebElement> dealers = Driver.findElements(d, By.className("dealer"));
        List<WebElement> trueCarDealers = new ArrayList<>();

        Driver.click(Driver.findElement(d, By.xpath(".//*[@id='dealers-0']")));

        for(int i=0;i< dealers.size();i++){

            WebElement dealer = dealers.get(i);

            List<WebElement> list = dealer.findElements(By.tagName("li"));

            for(WebElement li : list){

                if(li.getAttribute("class").equals("price")){
                    String price = li.findElement(By.className("actual-price")).getText();

                        trueCarDealers.add(dealer);

                }
            }

        }


        for(WebElement trueCarDealer: trueCarDealers) {

            dealerPrice.add(Integer.parseInt(trueCarDealer.findElement(By.className("actual-price")).getText().replace(",", "").replace("$", "")));

        }

        int price = Collections.min(dealerPrice);

        for(WebElement trueCarDealer: trueCarDealers) {

            int trueCarDealerPrice = Integer.parseInt(trueCarDealer.findElement(By.className("actual-price")).getText().replace(",", "").replace("$", ""));

           if(price == trueCarDealerPrice){

               Driver.click(trueCarDealer.findElement(By.className("form-field")).findElement(By.tagName("input")));
               break;

           }

        }

        Driver.clickWait(Driver.findElement(d, By.xpath(".//*[@id='dsel-header']/div[2]/button")),4000);


    }

}
