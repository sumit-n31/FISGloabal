package com.apitest;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;
import org.testng.Assert;
import io.restassured.response.Response;

public class Validategetapi {

	@Test
	public static void validateGetcall() {
		Response res = given().get("https://api.coindesk.com/v1/bpi/currentprice.json");
		

	}

}
