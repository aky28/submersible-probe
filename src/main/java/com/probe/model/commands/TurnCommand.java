package com.probe.model.commands;

import com.probe.model.Command;
import com.probe.model.Probe;


public class TurnCommand implements Command
{
	private final Probe probe;
	private final boolean isRight;

	public TurnCommand(Probe probe, boolean isRight) {
		this.probe = probe;
		this.isRight = isRight;
	}

	@Override
	public void execute() {
		if (isRight) probe.turnRight();
		else probe.turnLeft();
	}
}
