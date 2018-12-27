package TestFramework;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class basics6 {
	Properties prop=new Properties();
	@BeforeTest
	public void getData() throws IOException
	{
		
		FileInputStream fis=new FileInputStream("C:\\Users\\dmbas\\eclipse-workspace\\DemoProject\\src\\files\\env.properties");
		prop.load(fis);
		
		//prop.get("HOST");
	}
	@Test
	public void JiraAPICreateIssue()
	{
		//Creating Issue/Defect
		
		
//		String bodydata= "credential=dmbassan&password=Davidb$29";
		String bodydata = "{ \"username\": \"dmbassan\", \"password\": \"Davidb$29\" }";
		
		//RestAssured.baseURI=prop.getProperty("JIRAHOST");
		RestAssured.baseURI="http://localhost:8082";
		Response res=given().header("Content-Type", "application/json").
				header("Cookie","JSESSIONID="+ReusableMethods.getSessionKEY()).
			body("{"+
				"\"fields\": {"+
					"\"project\":{"+
				    "\"key\": \"DTP\""+
					"},"+
				    "\"summary\": \"Credit Card Defect Again\","+
					"\"description\": \"Creating my second bug\","+
				    "\"issuetype\": {"+
					"\"name\": \"Bug\""+
				    "}"+
				
  "}}").when().
		post("/rest/api/2/issue").then().statusCode(201).extract().response();
		
		   JsonPath js= ReusableMethods.rawToJson(res);
		   		   String id=js.get("id");
		   		   System.out.println(id);
		
				
		
		
		
		}
}
