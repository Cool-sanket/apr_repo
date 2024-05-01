package Steps;



import cucumber.runtime.junit.Assertions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.time.Duration;

public class Websteps {
    WebDriver driver;
    WebElement total,total1;
    String amount,amount1;

    @Given("Launch the browser and navigate to url")
    public void launch_the_browser_and_navigate_to_url() {
        System.out.println("Open browser");
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://bookcart.azurewebsites.net/");
        driver.manage().window().maximize();
    }
    @When("we clicked on login link")
    public void we_clicked_on_login_link() {
       WebElement loginlink= driver.findElement(By.xpath("//*[@class='mdc-button__label' and text()=' Login ']"));
       loginlink.click();
    }
    @When("we entered username {string}")
    public void we_entered_username(String uname) {
        driver.findElement(By.id("mat-input-0")).sendKeys(uname);
    }
    @When("we entered password {string}")
    public void we_entered_password(String pass) {
        driver.findElement(By.id("mat-input-1")).sendKeys(pass);
    }
    @Then("clicked on login button {string}")
    public void clicked_on_login_button(String uname) throws InterruptedException
    {
       WebElement loginbtn= driver.findElement(By.xpath("//*[@class='mdc-button__label' and text()='Login']"));
       loginbtn.click();
       Thread.sleep(3000);
       WebElement user =driver.findElement(By.xpath("//div[@class='d-flex align-items-center']/a[1]/span[2]/span"));
       String user1= user.getText();
       //System.out.println(user1);
       if(user1.equals(uname))
       {
           System.out.println("Username "+ user1 + " is Registered");
           System.out.println("Login Successfully and we are at Homepage");
       }
       else{
           System.out.println("Username "+ uname + " is not Registered");
           System.out.println("Login not Successfully \nNeed to Register first");
       }

    }

        @When("searching for {string}")
        public void searchingFor(String name) {
            WebElement serch = driver.findElement(By.xpath("//input[@type='search']"));
            serch.sendKeys(name);
            serch.submit();

             }


     @And("click items to add in cart")
    public void clickItemsToAddInCart() throws InterruptedException {
         System.out.println("Clicked items to add in cart");
         driver.findElement(By.xpath("//body[1]/app-root[1]/div[1]/app-home[1]/div[1]/div[2]/div[1]/div[1]/app-book-card[1]/mat-card[1]/mat-card-content[1]/app-addtocart[1]/button[1]/span[2]")).click();
         driver.findElement(By.xpath("//body[1]/app-root[1]/div[1]/app-home[1]/div[1]/div[2]/div[1]/div[2]/app-book-card[1]/mat-card[1]/mat-card-content[1]/app-addtocart[1]/button[1]/span[2]")).click();
         WebElement book3=driver.findElement(By.xpath("//body[1]/app-root[1]/div[1]/app-home[1]/div[1]/div[2]/div[1]/div[3]/app-book-card[1]/mat-card[1]/mat-card-content[1]/app-addtocart[1]/button[1]/span[2]"));
         book3.click();
         //scrollIntoView(driver,book3);
         //Thread.sleep(5000);
         WebElement book4= driver.findElement(By.xpath("//body[1]/app-root[1]/div[1]/app-home[1]/div[1]/div[2]/div[1]/div[4]/app-book-card[1]/mat-card[1]/mat-card-content[1]/app-addtocart[1]/button[1]/span[2]"));
         //scrollIntoView(driver,book4);
         book4.click();
         JavascriptExecutor js = (JavascriptExecutor) driver;
         js.executeScript("window.scrollBy(0, 150)");
         Thread.sleep(2500);
         driver.findElement(By.xpath("//body[1]/app-root[1]/div[1]/app-home[1]/div[1]/div[2]/div[1]/div[5]/app-book-card[1]/mat-card[1]/mat-card-content[1]/app-addtocart[1]/button[1]/span[2]")).click();
         Thread.sleep(3000);


    }

    @And("Goto cart and get total amount {string}")
    public String gotoCartAndGetTotalAmount(String expamount) throws InterruptedException {
        driver.findElement(By.xpath("//button[@class='mdc-icon-button mat-mdc-icon-button mat-unthemed mat-mdc-button-base']//span[@class='mat-mdc-button-touch-target']")).click();
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 300)");
        total = driver.findElement(By.xpath("(//td[@class='mat-mdc-cell mdc-data-table__cell cdk-cell cdk-column-action mat-column-action'])[5]"));
        amount = total.getText();
        //System.out.println(amount);
        System.out.println("Single item added to cart having Amount:= "+total.getText()+" Rupees");
        //Assert.assertEquals(amount,expamount);
        Assert.assertEquals(amount, expamount, "Cart amount not matched and test failed:-");
//        if (amount.equals(expamount))
//        {
//            System.out.println("After adding books total price of cart is matched and updated to "+expamount);
//        }
//        else
//        {
//            System.out.println("After adding books total price of cart not matching and needs to resolve");
//        }
        return amount;
    }

    @And("Add multiple items to cart and get total amt {string}")
    public String addMultipleItemsToCartAndGetTotalAmt(String expamount1) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 70)");
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//button[@color='warn'])[2]")).click();
        driver.findElement(By.xpath("(//button[@color='warn'])[4]")).click();
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("window.scrollBy(0, 300)");
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//button[@color='warn'])[6]")).click();
        driver.findElement(By.xpath("(//button[@color='warn'])[8]")).click();
        driver.findElement(By.xpath("(//button[@color='warn'])[10]")).click();
        Thread.sleep(3000);
        total1 = driver.findElement(By.xpath("(//td[@class='mat-mdc-cell mdc-data-table__cell cdk-cell cdk-column-action mat-column-action'])[5]"));
        amount1 = total1.getText();
        System.out.println("Multiple items added to cart having total Amount:= "+total1.getText()+" Rupees");
        Assert.assertEquals(amount, expamount1, "After adding multiple items Cart amount not matched and test failed:-");
       // Assert.assertEquals(amount,expamount1);
//        if (amount1.equals(expamount1))
//        {
//            System.out.println("After adding quantity total price of cart is matched and updated to "+expamount1);
//        }
//        else
//        {
//            System.out.println("After adding quantity total price of cart not matching and needs to resolve");
//        }

        return amount1;
    }

    @Then("Verifying both total amount {string} {string}")
    public void verifyingBothTotalAmount(String expamount,String expamount1) throws InterruptedException {
        Thread.sleep(3000);
        // implicit wait in Selenium WebDriver
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement total2 = driver.findElement(By.xpath("(//td[@class='mat-mdc-cell mdc-data-table__cell cdk-cell cdk-column-action mat-column-action'])[5]"));
        //Explicit Wait in Selenium WebDriver
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(total2));

//        System.out.println(total.getText());
//        System.out.println(total1.getText());
//        String t= total.getText();
//        String t1=total1.getText();
//        Thread.sleep(3000);


//        if (amount.equals(amount1))
//        {
//            System.out.println("After adding multiple quantity total price of cart is same and needs to resolve");
//        }
//        else
//        {
//            System.out.println("After adding multiple quantity total price of cart is increase and gets updated");
//        }
    }
}
