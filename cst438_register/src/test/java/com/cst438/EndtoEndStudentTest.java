package com.cst438;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cst438.domain.StudentRepository;
@SpringBootTest
public class EndtoEndStudentTest
{
   @Autowired
   StudentRepository studentRepository;
   
   public static final String CHROME_DRIVER_FILE_LOCATION = "C:/Users/Mehar/chromedriver.exe";
   public static final String URL = "http://localhost:3000";
   public static final String TEST_USER_NAME = "Test";
   public static final String TEST_USER_EMAIL = "test@gmail.com";
   public static final int SLEEP_DURATION = 1000;
   
   @Test
   public void addStudentTest() throws Exception{
      System.setProperty("webdriver.chrome.driver",CHROME_DRIVER_FILE_LOCATION);
      WebDriver driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      
      try {
         driver.get(URL);
         Thread.sleep(SLEEP_DURATION);
         driver.findElement(By.xpath("//a[@id='addStudent']")).click(); 
         Thread.sleep(SLEEP_DURATION);
         
         driver.findElement(By.xpath("//input[@id='studentName']")).sendKeys(TEST_USER_NAME);
         Thread.sleep(SLEEP_DURATION);
         
         driver.findElement(By.xpath("//input[@id='studentEmail']")).sendKeys(TEST_USER_EMAIL);
         Thread.sleep(SLEEP_DURATION);
         
         driver.findElement(By.xpath("//button[@id=SubmitNewStudent']")).click();
         
      } catch (Exception ex) {
         ex.printStackTrace();
         throw ex;
      } finally {
         driver.quit();
      }
   }
}
