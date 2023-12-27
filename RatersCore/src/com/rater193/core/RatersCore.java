package com.rater193.core;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

public class RatersCore extends JavaPlugin {

	@Override
	public void onEnable() {
		
		//These commands are finished
		this.getCommand("tpa").setExecutor(new CommandTpa());
		this.getCommand("tpaccept").setExecutor(new CommandTpa());
		this.getCommand("tpdeny").setExecutor(new CommandTpa());
		
		//These need to be programmed
		this.getCommand("spawn").setExecutor(new CommandTpa());
		this.getCommand("home").setExecutor(new CommandTpa());
		this.getCommand("warp").setExecutor(new CommandTpa());
	}

	@Override
	public void onDisable() {
		
	}
	
}
