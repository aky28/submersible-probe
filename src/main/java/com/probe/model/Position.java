/**
 * Represents a position in a 2D grid with non-negative coordinates.
 * Provides methods to move in specified directions and check equality.
 */
package com.probe.model;

import java.util.Objects;
import com.probe.model.Direction;
import com.probe.model.Position;

public class Position {

	private int x;
	private int y;

	public Position() {}

	public Position(int x, int y) {
		if (x < 0 || y < 0)
			throw new IllegalArgumentException("Coordinates must be non-negative.");
		this.x = x;
		this.y = y;
	}

	public Position move(Direction direction, boolean forward) {
		int dx = 0, dy = 0;
		int step = forward ? 1 : -1;
		switch (direction) {
			case NORTH -> dy = step;
			case SOUTH -> dy = -step;
			case EAST -> dx = step;
			case WEST -> dx = -step;
		}
		return new Position(this.x + dx, this.y + dy);
	}

	public int getX() { return x; }
	public int getY() { return y; }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Position position = (Position) o;
		return x == position.x && y == position.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}
