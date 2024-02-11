package steps;

import elements.KontaktHomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import utils.Driver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;


public class KontaktHomeSteps {
    static final Logger logger = LogManager.getLogger(KontaktHomeSteps.class.getName());
    protected WebDriver wd = Driver.getDriver();
    KontaktHomePage hp = new KontaktHomePage(wd);

    String productName = "";


    @Given("Go to site")
    public void the_user_visits_the_El_Tiempo_website() {
        wd.get("https://kontakt.az/");
        DOMConfigurator.configure("log4j.xml");
        logger.info("# # # # # # # # # # # # # # # # # # # # # # # # # # # ");
        logger.info("TEST Has Started");

    }

    @When("Check mainpage is open")
    public void check_mainpage_is_open() {
        if (wd.getTitle() != null && wd.getTitle().contains("Kontakt Home")) {
            logger.info("Web page is opened");
        } else {
            System.exit(0);
        }
    }

    @Then("Close ads popup")
    public void close_ads_popup() {
        logger.info("Close ads popup");
        hp.closeAdsPopup();
    }

    @Then("Insert Airpods to search field")
    public void search_airpods() {
        logger.info("Airpods searched!");
        hp.searchInField(wd, "Airpods");
    }

    @And("Select first result")
    public void select_random_result() {
        productName = hp.getProductName();
        hp.openProductDetailsPage();
        logger.info("First result (" + productName +  ") is selected");
    }

    @And("Add to cart")
    public void add_to_basket() {
        hp.addToCart(wd);
        logger.info("The airpods added to cart");
    }

    @And("Go to cart")
    public void go_to_cart() {
        hp.goToCart();
        logger.info("Go to cart");
    }

    @And("Verify")
    public void verify() {
        assertEquals(productName, hp.checkCartContent());
        logger.info("Verify (Product has been added to the cart)");
    }



    @After
    public void afterScenario() {
        wd.close();
    }

}
