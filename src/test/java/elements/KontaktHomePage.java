package elements;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KontaktHomePage {

    public KontaktHomePage(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='minisearch-input-kontakt-top-search']")
    WebElement searchField;

    @FindBy(xpath = "//button[@class='single_add_to_cart_button button alt']")
    WebElement addButton;

    @FindBy(css = ".woocommerce-cart-form__cart-item:nth-child(1) .plus")
    WebElement increaseButton;

    @FindBy(xpath = "//div[@id='popup-onesignal']//div//div[@class='action secondary']")
    WebElement adsPopupCloseButton;

    @FindBy(xpath = "(//div[@class='catalog__bottom products-grid container__elem--15 poisk']//div[@class='container']//div[@class='container__elem container__elem--15']//div[@class='contentos']//div)[1]")
    WebElement firstSearchResult;

    @FindBy(xpath = "(//div[@class='catalog__bottom products-grid container__elem--15 poisk']//div[@class='container']//div[@class='container__elem container__elem--15']//div[@class='contentos']//div)[1]//div[@class='prodItem__title']")
    WebElement firstSearchResultTitle;

    @FindBy(xpath = "//button[@id='product-addtocart-button']")
    WebElement addToCartButton;
    @FindBy(xpath = "//div[@class='cartHistory__item minicart-wrapper order-2 order-lg-3']")
    WebElement goToCartButton;

    @FindBy(xpath = "//div[@class='cartContent__bottom']//form//div[@class='cartContentItem']//div[@class='cartContentItem__wr']//div[@class='cartContentItem__title']//a")
    WebElement cartContent;


    public void searchInField(WebDriver driver, String s) {
        String data = s.trim().replaceAll("[^\\x00-\\x7f]", "");
        ;
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].removeAttribute('value')", searchField);
        jse.executeScript("arguments[0].setAttribute('value', '" + data + "')", searchField);
        searchField.sendKeys(Keys.ENTER);

    }

    public void closeAdsPopup() {
        adsPopupCloseButton.click();
    }

    public void openProductDetailsPage() {
        firstSearchResult.click();
    }

    public void addToCart(WebDriver driver) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Actions builder = new Actions(driver);
        builder.moveToElement(addToCartButton).perform();

        addToCartButton.click();
    }

    public void goToCart() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        goToCartButton.click();
    }

    public String getProductName() {
        return firstSearchResultTitle.getText();
    }

    public String checkCartContent() {
        return cartContent.getText();
    }

}
