package com.rater193.core;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandTpa implements CommandExecutor {
	
	public class TPAData {
		public Player playerFrom;
		public Player playerTo;
		
		public TPAData(Player _playerFrom, Player _playerTo) {
			playerFrom = _playerFrom;
			playerTo = _playerTo;
		}
	}
	
	public static ArrayList<TPAData> requests = new ArrayList<TPAData>();
	
	public boolean isNameRequested(Player player) {
		for(TPAData data : requests) {
			if(data.playerTo.getName().equals(player.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public TPAData getRequestData(Player target) {
		for(TPAData data : requests) {
			if(data.playerTo.getName().equals(target.getName()) || data.playerFrom.getName().equals(target.getName())) {
				return data;
			}
		}
		return null;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		if(sender instanceof Player) {
			//Here we execute code if the player was the one whom
			//excecuted the command
			
			Player player = (Player) sender;
			
			switch(label) {
			case "tpa":
				if(args.length<1) {
					player.sendMessage("You have to specify a player.");
					return false;
				}else {
					Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
					if(targetPlayer!=null) {
						if(targetPlayer.isOnline()) {
							player.sendMessage("Sending a TPA request to " + args[0]);
							targetPlayer.sendMessage(player.getDisplayName() + " wants to tpa to you. type /tpaccept or /tpdeny to handle the request.");
							requests.add(new TPAData(player, targetPlayer));
						}else {
							player.sendMessage("The player is not online");
							return false;
						}
					}else {
						player.sendMessage("The player does not exist");
						return false;
					}
				}
				break;
				
			case "tpaccept":
				if(isNameRequested(player)) {
					TPAData data = getRequestData(player);
					data.playerFrom.teleport(data.playerTo);
					data.playerFrom.sendMessage("Teleporting to " + data.playerTo.getDisplayName());
					data.playerTo.sendMessage("Teleporting " + data.playerFrom.getDisplayName());
					requests.remove(data);
				}else {
					player.sendMessage("You do not have any pending requests");
				}
				break;
				
			case "tpdeny":
				if(isNameRequested(player)) {
					TPAData data = getRequestData(player);
					player.sendMessage("You denied the request for " + data.playerFrom.getDisplayName());
					requests.remove(data);
				}else {
					player.sendMessage("You do not have any pending requests");
				}
				break;
			}

			//ItemStack stackDiamond = new ItemStack(Material.DIAMOND);
			//ItemStack stackBrick = new ItemStack(Material.BRICK, 3);
			
			//player.getInventory().addItem(stackDiamond, stackBrick);
			return true;
		}else {
			System.out.println("The command, " + label + ", command can only be executed by a player");
		}
		return false;
	}

}
