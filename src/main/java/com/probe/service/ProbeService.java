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
	private Position currentPos = new Position(0, 0);
	private Direction direction = Direction.NORTH;

	public void initialize(int size, int x, int y, String dir, List<Position> obs) {
		this.gridSize = size;
		this.currentPos = new Position(x, y);
		this.direction = Direction.valueOf(dir.toUpperCase());
		this.obstacles = new HashSet<>(obs);
		this.visited = new ArrayList<>();
		visited.add(currentPos);
	}

	public String executeCommands(String commands) {
		for (char cmd : commands.toCharArray()) {
			switch (cmd) {
				case 'F' -> tryMove(true);
				case 'B' -> tryMove(false);
				case 'L' -> direction = direction.left();
				case 'R' -> direction = direction.right();
				default -> throw new IllegalArgumentException("Invalid command: " + cmd);
			}
		}

		return "Final Position: " + currentPos + " Facing: " + direction + "\nVisited: " + visited;
	}

	private void tryMove(boolean forward) {
		Position next = currentPos.move(direction, forward);
		if (isValid(next)) {
			currentPos = next;
			visited.add(currentPos);
		}
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
