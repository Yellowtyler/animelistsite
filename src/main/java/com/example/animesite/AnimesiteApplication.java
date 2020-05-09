package com.example.animesite;

import org.json.JSONArray;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

@SpringBootApplication
public class AnimesiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimesiteApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//         JSONObject jsonObject = JsonReader.readJsonFromUrl("https://kitsu.io/api/edge/anime?filter[text]=cowboy%20bebop");
//         JSONArray jsonArray=jsonObject.getJSONArray("data");
//		JSONObject jsonObject1 = jsonArray.getJSONObject(0).getJSONObject("attributes");
//		System.out.println(jsonObject1.get("canonicalTitle").toString());
//
//	}
}


class JsonReader {

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}
}