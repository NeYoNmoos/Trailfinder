package tests.selenium;// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class CreateRouteTestTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void createRouteTest() {
    driver.get("http://localhost:8080/hike_war_exploded/route-create");
    driver.manage().window().setSize(new Dimension(2016, 1296));
    driver.findElement(By.id("name")).click();
    driver.findElement(By.id("name")).sendKeys("FHV to Karren");
    driver.findElement(By.id("length")).sendKeys("5.6");
    driver.findElement(By.id("altitude")).sendKeys("360");
    driver.findElement(By.id("location")).sendKeys("Dornbirn, Austria");
    driver.findElement(By.id("duration")).sendKeys("2.2");
    driver.findElement(By.id("description")).click();
    driver.findElement(By.id("description")).click();
    driver.findElement(By.id("description")).sendKeys("Beautiful hike to the Karren Platform. ");
    {
      WebElement dropdown = driver.findElement(By.id("months"));
      dropdown.findElement(By.xpath("//option[. = 'February']")).click();
    }
    {
      WebElement dropdown = driver.findElement(By.id("months"));
      dropdown.findElement(By.xpath("//option[. = 'March']")).click();
    }
    {
      WebElement dropdown = driver.findElement(By.id("months"));
      dropdown.findElement(By.xpath("//option[. = 'April']")).click();
    }
    {
      WebElement dropdown = driver.findElement(By.id("months"));
      dropdown.findElement(By.xpath("//option[. = 'May']")).click();
    }
    {
      WebElement dropdown = driver.findElement(By.id("months"));
      dropdown.findElement(By.xpath("//option[. = 'June']")).click();
    }
    {
      WebElement dropdown = driver.findElement(By.id("months"));
      dropdown.findElement(By.xpath("//option[. = 'July']")).click();
    }
    {
      WebElement dropdown = driver.findElement(By.id("months"));
      dropdown.findElement(By.xpath("//option[. = 'August']")).click();
    }
    {
      WebElement dropdown = driver.findElement(By.id("months"));
      dropdown.findElement(By.xpath("//option[. = 'September']")).click();
    }
    {
      WebElement dropdown = driver.findElement(By.id("months"));
      dropdown.findElement(By.xpath("//option[. = 'December']")).click();
    }
    driver.findElement(By.id("power")).click();
    {
      WebElement dropdown = driver.findElement(By.id("power"));
      dropdown.findElement(By.xpath("//option[. = '3']")).click();
    }
    driver.findElement(By.id("experience")).click();
    {
      WebElement dropdown = driver.findElement(By.id("experience"));
      dropdown.findElement(By.xpath("//option[. = '4']")).click();
    }
    driver.findElement(By.id("createform")).click();
    driver.findElement(By.id("condition")).click();
    {
      WebElement dropdown = driver.findElement(By.id("condition"));
      dropdown.findElement(By.xpath("//option[. = '5']")).click();
    }
    driver.findElement(By.id("routeMap")).click();
    {
      WebElement element = driver.findElement(By.id("routeMap"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.id("routeMap"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.id("routeMap"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.id("routeMap")).click();
    driver.findElement(By.id("routeMap")).click();
    {
      WebElement element = driver.findElement(By.id("routeMap"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.id("routeMap"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.id("routeMap"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.id("routeMap")).click();
    driver.findElement(By.id("routeMap")).click();
    {
      WebElement element = driver.findElement(By.cssSelector("li:nth-child(3)"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.cssSelector("li:nth-child(3)"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.cssSelector("li:nth-child(3)"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.id("createform")).click();
    driver.findElement(By.id("routeMap")).click();
    {
      WebElement element = driver.findElement(By.id("routeMap"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.id("routeMap"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.id("routeMap"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.id("routeMap")).click();
    driver.findElement(By.id("routeMap")).click();
    {
      WebElement element = driver.findElement(By.id("routeMap"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.id("routeMap"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.id("routeMap"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.id("routeMap")).click();
    driver.findElement(By.cssSelector(".btn-primary:nth-child(30)")).click();
    driver.findElement(By.linkText("Back to list of routes")).click();
    driver.findElement(By.cssSelector(".block:nth-child(1) .mb-2:nth-child(2)")).click();
    driver.findElement(By.cssSelector(".max-w-7xl")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector(".text-3xl"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.cssSelector(".py-5:nth-child(2)")).click();
    driver.findElement(By.cssSelector("p:nth-child(1)")).click();
    driver.findElement(By.cssSelector(".py-5:nth-child(2)")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector("p:nth-child(1)"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.cssSelector(".py-5:nth-child(2)")).click();
    driver.findElement(By.cssSelector(".px-4:nth-child(2) > .grid")).click();
    driver.findElement(By.cssSelector(".px-4:nth-child(2) .sm\\3A col-span-1:nth-child(1) > .mt-1")).click();
    driver.findElement(By.cssSelector(".px-4:nth-child(2) .sm\\3A col-span-1:nth-child(1) > .mt-1")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector(".px-4:nth-child(2) .sm\\3A col-span-1:nth-child(1) > .mt-1"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.cssSelector(".px-4:nth-child(2) > .grid")).click();
    driver.findElement(By.cssSelector(".px-4:nth-child(2) .sm\\3A col-span-1:nth-child(2) > .mt-1")).click();
    driver.findElement(By.cssSelector(".px-4:nth-child(2) > .grid")).click();
    driver.findElement(By.cssSelector(".px-4:nth-child(2) .sm\\3A col-span-1:nth-child(2) > .mt-1")).click();
    driver.findElement(By.cssSelector(".px-4:nth-child(2) .sm\\3A col-span-1:nth-child(2) > .mt-1")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector(".px-4:nth-child(2) .sm\\3A col-span-1:nth-child(2) > .mt-1"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.cssSelector(".py-5:nth-child(2)")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector(".px-4:nth-child(2) .sm\\3A col-span-1:nth-child(3) > .mt-1"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.cssSelector(".px-4:nth-child(2) .sm\\3A col-span-1:nth-child(4) > .mt-1")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector(".px-4:nth-child(2) .sm\\3A col-span-1:nth-child(4) > .mt-1"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.cssSelector(".py-10")).click();
    driver.findElement(By.cssSelector(".bg-white:nth-child(3) > .px-4")).click();
    driver.findElement(By.cssSelector(".px-4:nth-child(1) .sm\\3A col-span-1:nth-child(1) > .mt-1")).click();
    driver.findElement(By.cssSelector(".px-4:nth-child(1) > .grid")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector(".px-4:nth-child(1) .sm\\3A col-span-1:nth-child(2) > .mt-1"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.cssSelector(".bg-white:nth-child(3) > .px-4")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector(".px-4:nth-child(1) .sm\\3A col-span-1:nth-child(3) > .mt-1"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.cssSelector(".px-4:nth-child(1) > .grid")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector(".px-4:nth-child(1) .sm\\3A col-span-1:nth-child(4) > .mt-1"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.cssSelector(".px-4 > .mt-1")).click();
    driver.findElement(By.cssSelector(".px-4 > .mt-1")).click();
    driver.findElement(By.cssSelector(".px-4 > .mt-1")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector(".px-4 > .mt-1"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.cssSelector(".flex-col")).click();
  }
}