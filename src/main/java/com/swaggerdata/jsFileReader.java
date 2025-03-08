package com.swaggerdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.json.JSONObject;
import org.json.JSONTokener;

public class jsFileReader {
	public String readJson(String path, String payloadType) {
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
		return payload;
	}
}

//package com.swaggerdata;
//
//import java.io.InputStream;
//import java.nio.charset.StandardCharsets;
//import java.util.Scanner;
//import org.json.JSONObject;
//import org.json.JSONTokener;
//
//public class jsFileReader {
//    public String readJson(String path, String payloadType) {
//        ClassLoader classLoader = getClass().getClassLoader();
//        InputStream inputStream = classLoader.getResourceAsStream(path);
//        
//        if (inputStream == null) {
//            throw new RuntimeException("File not found: " + path);
//        }
//
//        try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
//            String jsonContent = scanner.useDelimiter("\\A").next();
//            JSONObject requestData = new JSONObject(new JSONTokener(jsonContent));
//
//            if (requestData.has("payloads") && requestData.getJSONObject("payloads").has(payloadType)) {
//                return requestData.getJSONObject("payloads").getJSONArray(payloadType).getJSONObject(0).toString();
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Error reading JSON file: " + path, e);
//        }
//
//        return null;
//    }
//}

