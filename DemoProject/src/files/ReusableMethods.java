package files;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {
	public static XmlPath rawToXml(Response res) {
		String resp =res.asString();
		
					
			   XmlPath x=new XmlPath(resp);
			   return x;
		
		
	}
	public static JsonPath rawToJson(Response res) {
		String resp =res.asString();
		  JsonPath x= new JsonPath(resp);
		   return x;
	}
	public static String getSessionKEY() {
String bodydata = "{ \"username\": \"dmbassan\", \"password\": \"Davidb$29\" }";
		
		//RestAssured.baseURI=prop.getProperty("JIRAHOST");
		RestAssured.baseURI="http://localhost:8082";
		Response res=given().header("Content-Type", "application/json").
			body(bodydata)
  .when().
		post("/rest/auth/1/issue").then().statusCode(200).extract().response();
		
		   JsonPath js= ReusableMethods.rawToJson(res);
		   		   String sessionid=js.get("session value");
		   		   System.out.println(sessionid);
		   		   return sessionid;
	}

}
