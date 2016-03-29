package org.unirest.example;

import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class UnirestExample {

	@Test
	public void main() {		
		String url = "http://httpbin.org/post";

		try {
			HttpResponse<JsonNode> jsonResponse = Unirest.post(url)
					.header("accept", "application/json")
					.queryString("apikey", "123")
					.field("parameter", "value")
					.field("foo", "bar")
					.asJson();
			if (HttpStatus.SC_OK == jsonResponse.getStatus()) {
				System.out.println(jsonResponse.getBody());
			}
		} catch (UnirestException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void main2() {
		String url = "http://localhost:8081/employee";
		try {
			HttpResponse<JsonNode> jsonResponse = Unirest.get(url)
					.header("accept", "application/json")
					.asJson();
			if (HttpStatus.SC_OK == jsonResponse.getStatus()) {
				JsonNode jsonNode = jsonResponse.getBody();
				System.out.println("value: " + jsonNode.getObject().get("value"));
			}
		} catch (UnirestException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
}
