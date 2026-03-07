package TestNG;
import pages.LoginPage;
import pages.CoursePage;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Them_CapNhatKhoaHoc {
    WebDriver driver;
    LoginPage loginPage;
    CoursePage coursePage;

    @BeforeTest
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "D:\\Project_AT\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://elearning.plt.pro.vn/trang-chu");
        driver.manage().window().maximize();
        
        loginPage = new LoginPage(driver);
        coursePage = new CoursePage(driver);
    }

    @Test(priority = 1)
    public void TC2() throws Exception {
        loginPage.login("test.pltsolutions@gmail.com", "plt@intern_051224");
        Thread.sleep(3000);
        coursePage.goToCourseMenu();
        Thread.sleep(3000);
        coursePage.clickAddNew();
        Thread.sleep(3000);
        coursePage.uploadImage("C:\\Users\\hh\\git\\repository\\Project_AT\\Json_img\\images.jpg");
        Thread.sleep(3000);

        JSONParser jsonparser = new JSONParser();
        JSONArray listChuong = (JSONArray) jsonparser.parse(new FileReader("C:\\Users\\hh\\git\\repository\\Project_AT\\Json_img\\taokhoahoc.json"));
        for (int i = 0; i < listChuong.size(); i++) {
            JSONObject data = (JSONObject) listChuong.get(i);
            coursePage.fillNewCourse((String) data.get("ten_khoahoc"), (String) data.get("mota_khoahoc"));
            Thread.sleep(2000);
            coursePage.clickConfirm();
            Thread.sleep(5000);
        }
    }

    @Test(priority = 2)
    public void TC1() throws Exception {
        coursePage.selectDatabaseCourse();
        Thread.sleep(3000);
        coursePage.openNoiDungTab();
        Thread.sleep(3000);
        coursePage.clickSuaNoiDung();
        Thread.sleep(3000);

        JSONParser jsonparser = new JSONParser();
        JSONArray listChuong = (JSONArray) jsonparser.parse(new FileReader("C:\\Users\\hh\\git\\repository\\Project_AT\\Json_img\\thembaihoc.json"));
        for (int i = 0; i < listChuong.size(); i++) {
            JSONObject data = (JSONObject) listChuong.get(i);
            coursePage.updateContent(
                (String) data.get("tieude_chuong"),
                (String) data.get("mota_chuong"),
                (String) data.get("tieude_bai"),
                (String) data.get("mota_bai")
            );
            Thread.sleep(1000);
        }
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}