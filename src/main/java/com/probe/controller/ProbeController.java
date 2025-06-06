package com.probe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.probe.model.InitRequest;
import com.probe.model.Position;
import com.probe.service.ProbeService;


@RestController
@RequestMapping("/api/probe")
public class ProbeController
{
	@Autowired
	private ProbeService probeService;

	@PostMapping("/init")
	public String initialize(@RequestBody InitRequest request) {
		probeService.initialize(request.gridSize, request.startX, request.startY, request.direction, request.obstacles);
		return "Initialized grid of size " + request.gridSize + " with start at (" + request.startX + "," + request.startY + ") facing " + request.direction;
	}

	@PostMapping("/command")
	public String moveProbe(@RequestBody String commands) {
		return probeService.executeCommands(commands.toUpperCase().replaceAll("[^FBLR]", ""));
	}

	@GetMapping("/visited")
	public List<Position> getVisitedCoordinates() {
		return probeService.getVisitedPositions();
	}
}
