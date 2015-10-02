package pages;

import drivers.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

/**
 * Created by JRNorris on 9/30/15.
 */

public class Configurator {

    private static ArrayList<String> fails = new ArrayList<String>();
    private static String carQ = null;

   public static ArrayList<String> Configurator(String make,String model,String year,String style,String zip,String driver){

       findCar(make, model, year, zip, driver);
       options(carQ, style, zip, driver);

       return fails;
   }

    private static void findCar(String make,String model,String year,String zip,String driver){

        WebDriver d =  Driver.getDriver(driver);
        d.get("https://autoblog.truecar.com/nc/configurator");

        //Make drop down//
        Driver.click(Driver.findElement(d, By.id("s2id_select_make_nav")).findElement(By.className("select2-choice")));

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

        Driver.click(Driver.findElement(d, By.id("s2id_select_model_nav")).findElement(By.className("select2-choice")));

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

        carQ = d.getCurrentUrl();

        String car = Driver.findElement(d, By.id("config-header")).getText();

        if(!car.equals(year + " " + make + " " + model)) fails.add("Car make/module/ on Find car module did not work" + car);


        editZip(zip, d);

        d.quit();

    }

    private static void options(String url, String style, String zip, String driver){

        WebDriver d =  Driver.getDriver(driver);
        d.get(url);
        editZip(zip, d);
        changeStyle(style, d);
        changeOptions(d, "edit_color", "group_primary_paint");
        changeOptions(d, "edit_opts", "group_additional_equipment");

        Driver.clickWait(Driver.findElement(d, By.xpath(".//*[@id='price-summary']/div[2]/a")),2000);
        Registration.register(d, "XXXXXXX", "ZZZZZZZ", "1234 test way", "3102324747", "test30@yahoo.com");
        Dealer.selection(d);
        Certificate.print(d);
        d.quit();

    }

    private static void editZip(String zip, WebDriver d){

        String newZip = "90401";
        Driver.click(Driver.findElement(d, By.className("zip-view")).findElement(By.className("button")));
        Driver.sendkeys(Driver.findElement(d, By.xpath(".//*[@id='update_zip']/div[2]/input")), newZip);
        Driver.clickWait(Driver.findElement(d, By.xpath(".//*[@id='update_zip']/div[2]/button")),2000);
        String pageZip = Driver.findElement(d, By.className("zip-view")).getText();

        if(!pageZip.contains(newZip)) fails.add("zip was not edited" + pageZip);

    }


    private static void changeStyle(String style, WebDriver d){

        String carPrice = carPrice(d);

        Driver.click(Driver.findElement(d, By.id("s2id_select_style")).findElement(By.className("select2-choice")));
        List<WebElement> styles = Driver.findElement(d, By.id("select2-drop")).findElement(By.className("select2-results"))
                .findElements(By.className("select2-results-dept-0"));

        for(WebElement pageStyle: styles){
            if(pageStyle.getText().equals(style)){
                Driver.click(pageStyle);
                break;
            }

        }

        String newCarPrice = carPrice(d);

        if(carPrice.equals(newCarPrice)) fails.add("car price did not change when adjusting the style of the car");

    }

    private static void changeOptions(WebDriver d,String buttonName, String optionName){


        Random random = new Random();
        ArrayList<String> paints = new ArrayList<>();
        String carPrice = carPrice(d);

        Driver.click(Driver.findElement(d, By.id(buttonName)));
        d.switchTo().activeElement();
        List<WebElement> paintOptions = Driver.findElement(d, By.id(optionName)).findElements(By.tagName("tr"));

        for(int i=0;i< paintOptions.size();i++){

            WebElement paintOption = paintOptions.get(i);

            List<WebElement> tds = paintOption.findElements(By.tagName("td"));

            for(WebElement td : tds){

                if(td.getAttribute("class").equals("oo-col3")){
                    String price = td.findElement(By.tagName("b")).getText();
                    String finalPrice = price.replace("$","").replace(",", "");
                    int invoice = Integer.parseInt(finalPrice);
                    if(invoice > 0){

                        paints.add(paintOption.findElement(By.className("oo-col2")).getText());

                    }
                }
            }

        }

            int randomPaintNumber = random.nextInt(paints.size() - 1);
            String paintChoice = paints.get(randomPaintNumber);

            String finalPaint = null;
            for (int z = 0; z < paintOptions.size(); z++) {

                if (finalPaint != null) {
                    break;
                }

                WebElement paintOption = paintOptions.get(z);

                List<WebElement> tds = paintOption.findElements(By.tagName("td"));

                for (WebElement td : tds) {

                    if (td.getAttribute("class").equals("oo-col2")) {

                        String paint = paintOption.findElement(By.className("oo-col2")).getText();

                        if (paint.equals(paintChoice)) {

                            finalPaint = paintChoice;
                            Driver.click(paintOption.findElement(By.className("oo-col1")).findElement(By.tagName("span")));

                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            break;

                        }
                    }

                }


            }



        Driver.click(Driver.findElement(d, By.xpath(".//*[@id='oo-container']/div[3]/span[2]")));

        String newCarPrice = carPrice(d);

        if(carPrice.equals(newCarPrice)) fails.add("car price did not change when adjusting the options of the car");

    }

    private static String carPrice(WebDriver d){

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       return Driver.findElement(d, By.id("price_overview")).findElement(By.className("price")).getText();

    }




}
