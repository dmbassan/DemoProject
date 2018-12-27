package TestFramework;

import static io.restassured.RestAssured.given;
import files.resources;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import files.ReusableMethods;

public class basics4 {
	
	Properties prop=new Properties();
	@BeforeTest
	  public void getData() throws IOException
	  {
		
		FileInputStream fis=new FileInputStream("C:\\Users\\dmbas\\eclipse-workspace\\DemoProject\\src\\files\\env.properties");
		prop.load(fis);
		
	  }
	  
  @Test
  public void AddandDeletePlace() throws IOException {
	 
	  String postData=GenerateStringFromResource("C:\\Users\\dmbas\\Documents\\postdata.xml");
	  //Task 1 - Grab the response
	  RestAssured.baseURI=prop.getProperty("HOST");
	  Response res=given().
	  queryParam("key", prop.getProperty("KEY"))
	  .body(postData).
	  when().post(resources.placePostData()).
	  then().assertThat().statusCode(200).and().contentType(ContentType.XML).and().
	  extract().response();
	  
	  XmlPath x=ReusableMethods.rawToXml(res);
	  String resp =res.asString();
	  
	 
  }
  public static String GenerateStringFromResource(String path) throws IOException {
	  return new String(Files.readAllBytes(Paths.get(path)));
  }
}
