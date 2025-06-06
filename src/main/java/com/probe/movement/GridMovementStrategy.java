package com.probe.movement;

import com.probe.model.Direction;
import com.probe.model.Position;


public class GridMovementStrategy implements MovementStrategy
{

	/**
	 * @param current
	 * @param direction
	 * @param step
	 * @return
	 */
	@Override
	public Position move(final Position current, final Direction direction, final int step)
	{
//		int x = current.x();
//		int y = current.y();
//		return switch (direction) {
//			case NORTH -> new Position(x, y + step);
//			case SOUTH -> new Position(x, y - step);
//			case EAST -> new Position(x + step, y);
//			case WEST -> new Position(x - step, y);
//		};

		int x = current.x();
		int y = current.y();
		int newX = switch (direction) {
			case EAST -> x + step;
			case WEST -> x - step;
			default -> x;
		};
		int newY = switch (direction) {
			case NORTH -> y + step;
			case SOUTH -> y - step;
			default -> y;
		};

		if (newX < 0 || newY < 0) {
			// invalid move, return current position or throw
			return current; // or throw IllegalArgumentException here if you want
		}

		return new Position(newX, newY);
	}
}
