package com.probe.model;

import java.util.List;


public class InitRequest
{
	public int gridSize;
	public int startX;
	public int startY;
	public String direction;
	public List<Position> obstacles;

	protected int getGridSize()
	{
		return gridSize;
	}

	public void setGridSize(final int gridSize)
	{
		this.gridSize = gridSize;
	}

	protected int getStartX()
	{
		return startX;
	}

	public void setStartX(final int startX)
	{
		this.startX = startX;
	}

	protected int getStartY()
	{
		return startY;
	}

	public void setStartY(final int startY)
	{
		this.startY = startY;
	}

	protected String getDirection()
	{
		return direction;
	}

	public void setDirection(final String direction)
	{
		this.direction = direction;
	}

	protected List<Position> getObstacles()
	{
		return obstacles;
	}

	public void setObstacles(final List<Position> obstacles)
	{
		this.obstacles = obstacles;
	}
}
