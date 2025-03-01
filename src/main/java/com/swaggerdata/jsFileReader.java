package com.swaggerdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.json.JSONObject;
import org.json.JSONTokener;

public class jsFileReader {
	public static String readJson(String path, String payloadType) {
		File f = new File(System.getProperty("user.dir") + path);
		// Open a file input stream to read the content of the file
		FileInputStream fileInputStream = null;
		String payload=null;
		try {
			fileInputStream = new FileInputStream(f);
			JSONTokener jsonTokener = new JSONTokener(fileInputStream);
			JSONObject requestData= new JSONObject(jsonTokener);
			if(requestData.has("payloads") && requestData.getJSONObject("payloads").has(payloadType)) {
				payload= requestData.getJSONObject("payloads").getJSONArray(payloadType).getJSONObject(0).toString();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// Create a JSONTokener object that parses the JSON content from the file input
		// stream

		// Create a JSONObject from the JSONTokener, which reads the JSON content into a
		// structured format
		return payload;
	}
	public static void main(String[] args) {
		readJson("/src/test/resources/requestPayload/CreateUserWithValidPayloadAuthors.json", "validPayload");
	}

}
