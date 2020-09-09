package CloudThing.Assignment;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

public class ReadDataFromJson {
	
	
	@Test
	public static void testJson() throws IOException, ParseException
	{
		System.out.println("test");
		FileReader file= new FileReader(".\\testDataJson\\testData.json");
		JSONParser json = new JSONParser();
		Object obj=json.parse(file);
		
		JSONObject jobj=(JSONObject)obj;
		
		System.out.println(jobj.get("FirstName"));
		System.out.println(jobj.get("Address"));
		
		JSONArray add=(JSONArray) jobj.get("Address");
		for(int i=0;i<add.size();i++)
		{
			JSONObject json2=(JSONObject) add.get(i);
			System.out.println(json2.get("State"));
		}	
		
	}

}
