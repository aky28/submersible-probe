package com.probe.service;

import com.probe.model.Direction;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.probe.model.Position;

@Service
public class ProbeService
{
	private int gridSize = 10;
	private Set<Position> obstacles = new HashSet<>();
	private List<Position> visited = new ArrayList<>();
	private Position currentPosition = new Position(0, 0);
	private Direction currentDirection = Direction.NORTH;

	public void initialize(int size, int x, int y, String dir, List<Position> obs) {
		this.gridSize = size;
		this.currentPosition = new Position(x, y);
		this.currentDirection = Direction.valueOf(dir.toUpperCase());
		this.obstacles = new HashSet<>(obs);
		this.visited = new ArrayList<>();
		visited.add(currentPosition);
	}

	public String executeCommands(String commands) {
		for (char cmd : commands.toCharArray()) {
			switch (cmd) {
				case 'F' -> tryMove(true);
				case 'B' -> tryMove(false);
				case 'L' -> currentDirection = currentDirection.left();
				case 'R' -> currentDirection = currentDirection.right();
				default -> throw new IllegalArgumentException("Invalid command: " + cmd);
			}
		}

		return "Final Position: " + currentPosition + " Facing: " + currentDirection + "\nVisited: " + visited;
	}

	private void tryMove(boolean forward) {
		int newX = currentPosition.getX();
		int newY = currentPosition.getY();

		// Simulate move based on direction
		switch (currentDirection) {
			case NORTH -> newY += forward ? 1 : -1;
			case SOUTH -> newY += forward ? -1 : 1;
			case EAST  -> newX += forward ? 1 : -1;
			case WEST  -> newX += forward ? -1 : 1;
		}

		// Check if in bounds
		if (newX < 0 || newY < 0 || newX >= gridSize || newY >= gridSize) {
			return; // Skip invalid move
		}

		Position newPosition = new Position(newX, newY);

		if (!obstacles.contains(newPosition)) {
			currentPosition = newPosition;
			visited.add(currentPosition);
		}
	}

	private boolean isWithinGrid(Position pos) {
		return pos.getX() >= 0 && pos.getY() >= 0 &&
				pos.getX() < gridSize && pos.getY() < gridSize;
	}

	private boolean isValid(Position pos) {
		return pos.getX() >= 0 && pos.getY() >= 0 &&
				pos.getX() < gridSize && pos.getY() < gridSize &&
				!obstacles.contains(pos);
	}

	public List<Position> getVisitedPositions() {
		return new ArrayList<>(visited);
	}
}
