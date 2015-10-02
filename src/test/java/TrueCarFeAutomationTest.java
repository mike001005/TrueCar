import com.google.code.tempusfugit.concurrency.ConcurrentTestRunner;
import interfaces.TrueCar;
import org.junit.*;
import org.junit.runner.RunWith;
import pages.*;

import java.util.ArrayList;

/**
 * Created by JRNorris on 9/30/15.
 */

@RunWith(ConcurrentTestRunner.class)
public class TrueCarFeAutomationTest implements TrueCar{

    private String siteUrl;
    private String make;
    private String zip;
    private String driver;
    private String model;
    private String year;
    private String style;

    public void siteURL(String siteUrl) {
        this.siteUrl = siteUrl;
    }


    public void make(String make) {
        this.make = make;
    }
    public void model(String model) {
        this.model = model;
    }
    public void zip(String zip) {this.zip = zip;}
    public void driver(String driver) {this.driver = driver;}
    public void year(String year){this.year = year;}
    public void style(String style){this.style = style;}

    @Before
    public void setUpBeforeClass(){

        siteURL(System.getProperty("siteUrl"));
        make(System.getProperty("make"));
        zip(System.getProperty("zip"));
        driver(System.getProperty("driver"));
        model(System.getProperty("model"));
        year(System.getProperty("year"));
        style(System.getProperty("style"));
    }

    @Test
    public void TestConfigurator() throws Exception{

        ArrayList<String> fails;
        fails = Configurator.Configurator(make, model, year, style, zip, driver);
        Assert.assertEquals(new ArrayList<String>(0), fails);
    }

    @Test
    public void TestAutoBlogHomePage() throws Exception{

        ArrayList<String> fails;
        fails = AutoBlogHomePage.CarandZip(siteUrl, make, model, year, zip, driver);
        Assert.assertEquals(new ArrayList<String>(0), fails);
    }


}
