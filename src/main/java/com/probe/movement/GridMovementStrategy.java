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
		int x = current.x();
		int y = current.y();
		return switch (direction) {
			case NORTH -> new Position(x, y + step);
			case SOUTH -> new Position(x, y - step);
			case EAST -> new Position(x + step, y);
			case WEST -> new Position(x - step, y);
		};
	}
}
