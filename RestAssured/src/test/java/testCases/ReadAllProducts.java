package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ReadAllProducts {
	@Test
	public void readAllProducts() {
		/*
		 * given: all input 
		 * details(baseURI,Headers,Authorization,Payload/Body,QueryParameters) when:
		 * submit api requests(Http method,Endpoint/Resource) then: validate
		 * response(status code, Headers, responseTime, Payload/Body) Http method=GET
		 * EndPointUrl= https://techfios.com/api-prod/api/product /read.php Header:
		 * Content-Type:application/json; charset=UTF-8 AythorizationType:Basic Auth
		 * username=demo@techfios.com password=abc123 StatusCode=200
		 */
		Response response=
		given() 
		        .baseUri("https://techfios.com/api-prod/api/product")
				.header("Content-Type", "application/json; charset=UTF-8")
				.auth().preemptive().basic("demo@techfios.com", "abc123").
				//.log().all().
		when()
		//.log().all()	
		        .get("/read.php").
		        
		then()
		
.extract().response();	
int statusCode=response.getStatusCode();	
System.out.println("StatusCode:"+statusCode);
Assert.assertEquals( statusCode, 200);
long responseTime=response.getTime();
System.out.println("Response Time:"+ responseTime);
if (responseTime<=2000) {
	System.out.println("Response Time is within range.");
}
else {
	System.out.println("Response Time is out of  range!");

}
String responseHeaderContentType=response.getHeader("Content-Type");
System.out.println("Response Header Content-Type:"+responseHeaderContentType);
Assert.assertEquals(responseHeaderContentType , "application/json; charset=UTF-8");
String responseBody=response.getBody().asString();
System.out.println("ResponseBody:"+responseBody);
JsonPath jp=new JsonPath(responseBody);
String firstProductId=jp.get("records[0].id");
System.out.println("First product Id:"+firstProductId);
if(firstProductId!=null) {
	System.out.println("First Product Id is not null");
}
else {
	System.out.println("First Product Id is not null");
}
	}

}
