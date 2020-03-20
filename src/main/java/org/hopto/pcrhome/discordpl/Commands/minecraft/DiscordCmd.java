package org.hopto.pcrhome.discordpl.Commands.minecraft;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class DiscordCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        sender.sendMessage(ChatColor.LIGHT_PURPLE + "Tu veux te joindre à nous, voici notre discord => " + ChatColor.GOLD + "https://discord.gg/ZqMM23X");

        return true;
    }
}
