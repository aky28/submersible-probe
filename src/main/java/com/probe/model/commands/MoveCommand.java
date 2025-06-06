package com.probe.model.commands;

import org.springframework.stereotype.Component;

import com.probe.model.Command;
import com.probe.model.Probe;

public class MoveCommand implements Command
{

	private final Probe probe;
	private final int step;

	public MoveCommand(Probe probe, int step) {
		this.probe = probe;
		this.step = step;
	}

	@Override
	public void execute() {
		probe.move(step);
	}
}
