package com.probe.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonCreator;


public class Position(int x, int y) {

	private int x, y;

	public Position(int x, int y) {
		if (x < 0 || y < 0) throw new IllegalArgumentException("Coordinates must be non-negative.");
		this.x = x;
		this.y = y;
	}

	public Position move(Direction dir, boolean forward) {
		int dx = forward ? dir.dx() : -dir.dx();
		int dy = forward ? dir.dy() : -dir.dy();
		return new Position(x + dx, y + dy);
	}

	public int getX() { return x; }
	public int getY() { return y; }

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Position other)) return false;
		return x == other.x && y == other.y;
	}

	@Override
	public int hashCode() { return 31 * x + y; }

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}
