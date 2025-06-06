package com.probe.model;

import java.util.LinkedHashSet;
import java.util.Set;

import com.probe.movement.GridMovementStrategy;
import com.probe.movement.MovementStrategy;
import com.probe.service.CommandFactory;


public class Probe
{
	private Position position;
	private Direction direction;
	private final Set<Position> visited;
	private final Set<Position> obstacles;
	private final int gridWidth;
	private final int gridHeight;
	private final MovementStrategy movementStrategy;

	public Probe(int x, int y, Direction direction, int gridWidth, int gridHeight, Set<Position> obstacles) {
		this.position = new Position(x, y);
		this.direction = direction;
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
		this.obstacles = obstacles;
		this.visited = new LinkedHashSet<>();
		this.visited.add(this.position);
		this.movementStrategy = new GridMovementStrategy();
	}

	public void processCommands(String commands) {
		CommandFactory factory = new CommandFactory(this);
		for (char cmd : commands.toCharArray()) {
			factory.getCommand(cmd).execute();
		}
	}

	public void move(int step) {
		Position newPos = movementStrategy.move(position, direction, step);
		if (isWithinBounds(newPos) && !obstacles.contains(newPos)) {
			position = newPos;
			visited.add(position);
		}
	}

	public void turnLeft() {
		direction = direction.left();
	}

	public void turnRight() {
		direction = direction.right();
	}

	private boolean isWithinBounds(Position pos) {
		return pos.x() >= 0 && pos.y() >= 0 && pos.x() < gridWidth && pos.y() < gridHeight;
	}

	public Position getPosition() {
		return position;
	}

	public Direction getDirection() {
		return direction;
	}

	public Set<Position> getVisited() {
		return visited;
	}

}
