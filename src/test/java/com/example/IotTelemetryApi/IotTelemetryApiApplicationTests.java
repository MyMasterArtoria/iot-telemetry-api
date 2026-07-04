package com.example.IotTelemetryApi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class IotTelemetryApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {

	}

	@Test
	void createDevice_returnsCreatedDevice() throws Exception {
		String json = "{\"name\":\"sensor-1\",\"type\":\"temperature\",\"status\":\"online\"}";
		mockMvc.perform(post("/devices")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.name").value("sensor-1"));
	}

	@Test
	void getMissingDevice_returns404() throws Exception {
		mockMvc.perform(get("/devices/999999"))
				.andExpect(status().isNotFound());
	}

	@Test
	void createDeviceWithBlankName_returns400() throws Exception {
		String json = "{\"type\":\"temperature\"}"; // no name
		mockMvc.perform(post("/devices")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
				.andExpect(status().isBadRequest());
	}
}
