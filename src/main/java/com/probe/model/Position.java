package com.probe.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonCreator;


public record Position(int x, int y) {
	@JsonCreator
	public Position {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException("Coordinates must be non-negative.");
		}
	}
}
