package org.okhttp.example;

import java.io.IOException;

import org.junit.Test;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpExample {

	@Test
	public void test() {
		OkHttpClient client = new OkHttpClient();
		String url = "http://localhost:8081/employee";
		Request request = new Request.Builder().url(url).build();

		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				System.out.println(response.body().string());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
