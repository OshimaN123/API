package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ReadOneProduct {
	String baseURI;
	public ReadOneProduct() {
		baseURI="https://techfios.com/api-prod/api/product";
	}

	@Test
	public void readOneProduct() {
		
	
		
		Response response=
		given() 
		        .baseUri(baseURI)
				.header("Content-Type", "application/json")
				.header("Authorization","Bearer fjlsdjlfksdgs08409235")
				.queryParam("id", "8000").
				//.log().all().
		when()
		        .get("/read_one.php").
		        
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
Assert.assertEquals(responseHeaderContentType , "application/json");


String responseBody=response.getBody().asString();
System.out.println("ResponseBody:"+responseBody);


JsonPath jp=new JsonPath(responseBody);
String productName=jp.get("name");
System.out.println("Product Name"+productName);
Assert.assertEquals(productName , "{{name}}");

String productDescription=jp.get("description");
System.out.println("Product description: "+productDescription);
Assert.assertEquals(productDescription , "CharGPT");

String productPrice=jp.get("price");
System.out.println("Product price: "+productPrice);
Assert.assertEquals(productPrice , "1999");

}
}