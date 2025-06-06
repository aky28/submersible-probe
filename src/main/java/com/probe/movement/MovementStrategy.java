package com.probe.movement;

import com.probe.model.Direction;
import com.probe.model.Position;

public interface MovementStrategy {
	Position move(Position current, Direction direction, int step);
}
