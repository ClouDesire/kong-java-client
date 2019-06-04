package com.github.vaibhavsinha.kong;

import com.github.vaibhavsinha.kong.impl.KongClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fanhua on 2017-07-28.
 */
public abstract class BaseTest {

	private static final String KONG_ADMIN_URL = "http://localhost:8001";
	private static final String KONG_API_URL = "https://localhost:8443";

	protected static KongClient kongClient;

	private Gson gson;

	@Before
	public void before() {

		kongClient = new KongClient(KONG_ADMIN_URL, KONG_API_URL, true);

		gson = new GsonBuilder()
				.enableComplexMapKeySerialization()
				.serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")
				.setPrettyPrinting()
				.setVersion(1.0)
				.create();
	}


	protected void printJson(Object object) {
		System.out.println(gson.toJson(object));
	}

	protected void printString(String str) {
		System.out.println(str);
	}

	protected static String getCurrentDateTimeString() {
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}
}
