package com.probe.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.probe.model.Direction;
import com.probe.model.Position;
import com.probe.model.Probe;


@RestController
@RequestMapping("/api/probe")
public class ProbeController
{
	private Probe probe;

	@PostMapping("/init")
	public String init(@RequestParam int x, @RequestParam int y,
			@RequestParam Direction direction,
			@RequestParam int width, @RequestParam int height,
			@RequestBody(required = false) List<Position> obstacles) {
		Set<Position> obs = obstacles != null ? new HashSet<>(obstacles) : Set.of();
		this.probe = new Probe(x, y, direction, width, height, obs);
		return "Probe initialized at (" + x + "," + y + ") facing " + direction;
	}

	@PostMapping("/command")
	public ResponseEntity<String> command(@RequestParam String commands) {
		if (probe == null) {
			return ResponseEntity.badRequest().body("Probe is not initialized.");
		}
		probe.processCommands(commands);
		Position pos = probe.getPosition();
		Direction dir = probe.getDirection();
		String response = String.format("Moved to (%d,%d) facing %s", pos.x(), pos.y(), dir);
		return ResponseEntity.ok(response);
	}


	@GetMapping("/visited")
	public Set<Position> visited() {
		return probe.getVisited();
	}

	@GetMapping("/")
	public String hello() {
		return "Submersible Probe API is running!";
	}
}
