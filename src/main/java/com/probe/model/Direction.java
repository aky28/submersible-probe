package com.probe.model;

public enum Direction
{
	NORTH(0, 1), EAST(1, 0), SOUTH(0, -1), WEST(-1, 0);

	private final int dx, dy;
	Direction(int dx, int dy) { this.dx = dx; this.dy = dy; }

	public int dx() { return dx; }
	public int dy() { return dy; }

	public Direction left() {
		return values()[(this.ordinal() + 3) % 4];
	}

	public Direction right() {
		return values()[(this.ordinal() + 1) % 4];
	}
}
