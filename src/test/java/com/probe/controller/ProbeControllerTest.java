package com.probe.controller;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.probe.model.Direction;
import com.probe.model.Position;
import com.probe.model.InitRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@AutoConfigureMockMvc
public class ProbeControllerTest
{
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private InitRequest initRequest;

	@BeforeEach
	public void setup() {
		initRequest = new InitRequest();
		initRequest.setGridSize(5);
		initRequest.setStartX(0);
		initRequest.setStartY(0);
		initRequest.setDirection(String.valueOf(Direction.NORTH));
		initRequest.setObstacles(List.of(
				new Position(0, 1),
				new Position(1, 0)
		));
	}

	@Test
	public void testProbeAvoidsObstaclesAndStaysInBounds() throws Exception {
		// Step 1: Initialize
		mockMvc.perform(post("/api/probe/init")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(initRequest)))
				.andExpect(status().isOk());

		// Step 2: Send commands FRF
		mockMvc.perform(post("/api/probe/command")
						.contentType(MediaType.APPLICATION_JSON)
						.content("\"FRF\"")) // <-- raw JSON string
				.andExpect(status().isOk())
				.andExpect(content().string("Final Position: (0,0) Facing: EAST\nVisited: [(0,0)]"));

	}
}
