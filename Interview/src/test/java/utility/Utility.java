package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Properties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utility {
	static RequestSpecification req;
	JsonPath js;
	
	public static RequestSpecification requestSpecification() throws IOException
	{
		if(req==null)
		{
		PrintStream log= new PrintStream(new FileOutputStream("logging.txt"));
	     req = new RequestSpecBuilder().setBaseUri(getProperty("url"))
	    		 .setContentType(ContentType.JSON)
	    		 .addFilter(RequestLoggingFilter.logRequestTo(log))
	    		 .addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
	     return req;
		}
		return req;
	}
	
	
	
	public String getJsonPath(Response response,String key)
	{
		String resp=response.asString();
		js=new JsonPath(resp);
		return js.get(key).toString();
	}
	
	public JsonPath getJsonPath(Response response)
	{
		String resp=response.asString();
		return js=new JsonPath(resp);
	}
	
	public static String getProperty(String key) throws IOException
	{
		Properties prop=new Properties();
		String path=System.getProperty("user.dir")+"/configuration/global.properties";
		FileInputStream fis=new FileInputStream(path);
		prop.load(fis);
		return prop.getProperty(key);
	}
	

}
