package com.probe.service;

import com.probe.model.Command;
import com.probe.model.Probe;
import com.probe.model.commands.MoveCommand;

public class CommandFactory
{
	private final Probe probe;

	public CommandFactory(Probe probe) {
		this.probe = probe;
	}

	public Command getCommand(char cmd) {
		return switch (cmd) {
			case 'F' -> new MoveCommand(probe, 1);
			case 'B' -> new MoveCommand(probe, -1);
			case 'L' -> new TurnCommand(probe, false);
			case 'R' -> new TurnCommand(probe, true);
			default -> throw new IllegalArgumentException("Invalid command: " + cmd);
		};
	}
}
