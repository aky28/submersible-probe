package com.probe.model;

public record Position(int x,int y) {
	public Position {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException("Coordinates must be non-negative.");
		}
	}
}
