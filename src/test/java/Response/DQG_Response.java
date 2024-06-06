package Response;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.mail.EmailException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DQG_Response {

	public int login_status;
	public int ConnectionList_status;
	public String login_responsebody;
	public String ConnectionList_responsebody;
	
	 // public static String pincode = "";
	    // static WebDriver driver;
	@Test
	public void Login_and_ConnectionList() throws EmailException, IOException, InterruptedException
	{
		HttpResponse<String> Login_response = Unirest.post("https://kairostech.ai/DQG-AuthNZ/api/Login/Userlogin")
				  .header("Content-Type", "application/json")
				  .body("{\r\n"
				  		+ "    \"EncryptedData\": \"py_AauTREySmbudklrv2sbLRl4e_2xHui1TbvoLsB2VJbCmIirkhK6sAIy0VhcnO0dZUbhVgapcgRlnmbvoAbJbl7AUzxK9opfLT6fDPz3k=\"\r\n"
				  		+ "}")
				  .asString();
				
				int login_status=Login_response.getStatus();
				//Headers responsebody=response.getHeaders();
				String login_responsebody=Login_response.getBody();
				
				System.out.println("Status = "+login_status);
				System.out.println("Body = "+login_responsebody);
				
				 HttpResponse<String> Connection_List_response = Unirest.post("https://kairostech.ai/DQG-Server/api/data-source/list")
			                .header("Content-Type", "application/json")
			                .body("{\"key\": \"\"}")
			                .asString();
						int ConnectionList_status=Connection_List_response.getStatus();
						System.out.println("Connections List Status = "+Connection_List_response.getStatus());
						if(login_status != 200 && ConnectionList_status != 200 )
						{
						 HtmlEmail email = new HtmlEmail();
			                email.setHostName("smtp.gmail.com");
			                email.setSmtpPort(587);
			                email.setAuthenticator(new DefaultAuthenticator("kmbb4578@gmail.com", "ntto lwsv rfwa plhk"));
			                email.setSSLOnConnect(true);
		
			                email.setFrom("kmbb4578@gmail.com");
			                email.setSubject("DQG Server is not working");
			                email.setContent("Hi Team.<br>DQG Working Status:<br>" +"Login status : "+login_status + "<br>Connections List Status: " +ConnectionList_status , "text/html");
			                email.addCc("lakshminarayana.g@kairostech.com");
			                email.addCc("bhasha.k@kairostech.com");
			     	      	email.addTo("ravikumar.p@kairostech.com");			                
			                email.send();
	}
		
		
		
	}
	

		
		// WebDriverManager.edgedriver().setup();
  //       driver = new EdgeDriver();
  //       driver.manage().window().maximize();

  //       FileInputStream excelFile = new FileInputStream(".//src//main//Resources//Book1.xlsx");
  //       Workbook workbook = new XSSFWorkbook(excelFile);
  //       Sheet sheet = workbook.getSheetAt(0);

  //       for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
  //           Row row = sheet.getRow(rowNum);
  //           String country = row.getCell(1).getStringCellValue();
  //           String state = row.getCell(2).getStringCellValue();
  //           String city = row.getCell(3).getStringCellValue();

  //           driver.get("https://www.bing.com/");
  //           Thread.sleep(3000);
  //           driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys(country + "," + state + "," + city + " ,pincode", Keys.ENTER);

  //           try {
  //               WebElement pincodeElement = driver.findElement(By.xpath("//div[@class='b_focusTextLarge' or @class='b_focusTextMedium']"));
  //               pincode = pincodeElement.getText();//div[@class='b_focusTextLarge' or @class='b_focusTextMedium']
  //             System.out.println(pincode);
  //           } catch (NoSuchElementException e) {
  //           System.out.println("First element not found at Row Number: " + rowNum + ", trying next element.");
  //               try {
  //                   WebElement element1 = driver.findElement(By.xpath("//p[@class='b_lineclamp3 b_algoSlug']//strong[1]"));
  //                   if (element1.isDisplayed()) {
  //                       pincode = element1.getText();
  //                       System.out.println(pincode);
  //                   }
  //               } catch (NoSuchElementException e1) {
  //                   System.out.println("Second element not found at Row Number: " + rowNum + ", trying next element.");
  //                   try {
  //                       WebElement element2 = driver.findElement(By.xpath("//*[@id=\"b_results\"]/li[1]/h2/a"));
  //                       WebElement element3 = driver.findElement(By.xpath("//span[@style='font-size:250%; font-weight:bold;']"));
  //                       if (element2.isDisplayed()) {
  //                           JavascriptExecutor js = (JavascriptExecutor) driver;
  //                           js.executeScript("arguments[0].removeAttribute('target');", element2);
  //                           element2.click();
  //                           Thread.sleep(2000);
  //                           pincode=element3.getText();
  //                           System.out.println(pincode);
  //                           driver.navigate().back();
  //                       }
  //                   } catch (NoSuchElementException e2) {
  //                       System.out.println("All elements not found at Row Number: " + rowNum + ", setting pincode as 'Not Found'.");
  //                       pincode = "Not Found";
  //                   }
  //               }
  //           }

  //           Cell pincodeCell = row.createCell(7, CellType.STRING);  // Column index 7 corresponds to the 8th column
  //           pincodeCell.setCellValue(pincode);
  //       }

  //       FileOutputStream outputStream = new FileOutputStream(".//src//main//java//Resources//Book1.xlsx");
  //       workbook.write(outputStream);
  //       workbook.close();
  //       outputStream.close();
  //       excelFile.close();
  //       driver.quit();
  //   }

  //   public static void scrolltoEle(WebElement ele) {
  //       ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
  //   }

}


