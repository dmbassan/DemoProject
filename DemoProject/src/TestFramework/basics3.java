package TestFramework;

import static io.restassured.RestAssured.given;
import files.resources;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class basics3 {
	
	Properties prop=new Properties();
	@BeforeTest
	  public void getData() throws IOException
	  {
		
		FileInputStream fis=new FileInputStream("C:\\Users\\dmbas\\eclipse-workspace\\DemoProject\\src\\files\\env.properties");
		prop.load(fis);
		
	  }
	  
  @Test
  public void AddandDeletePlace() {
	  String b= "{"+
			    "\"location\": {"+
		        "\"lat\": -38.383494,"+
		        "\"lng\": 33.427362"+
		    "},"+
		    "\"accuracy\": 50,"+
		    "\"name\": \"Frontline House\","+
		    "\"phone number\": \"(+91)9838933937\""+
		"}";
	  //Task 1 - Grab the response
	  RestAssured.baseURI=prop.getProperty("HOST");
	  Response res=given().
	  queryParam("key", prop.getProperty("KEY"))
	  .body(b).
	  when().post(resources.placePostData()).
	  then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
	  body("status", equalTo("OK")).and().
	  extract().response();
	  
		 //Grab the place ID from the response - need to convert to JSON format
	 String  responseString = res.asString();
	 System.out.println(responseString);
	 JsonPath js = new JsonPath(responseString);
	 String placeid = js.get("place_id");
	 System.out.println(placeid );
	 
	 //Place this placeID in the Delete request
	 given().
	  queryParam("key", "qaclick123").
	  body("{"+
	  		"\"place_id\": \"" +placeid+"\""+
	  		"}").
	  when().post("/maps/api/place/delete/json").
	  then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
	  body("status", equalTo("OK"));
	 
  }
}
