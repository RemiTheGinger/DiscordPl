package org.hopto.pcrhome.discordpl.chatEvents;

import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.hopto.pcrhome.discordpl.DiscordBot;
import org.hopto.pcrhome.discordpl.DiscordPl;


public class MinecraftChat implements Listener {

    String chatID = DiscordPl.getChatID();

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e){
        String message = e.getMessage();
        TextChannel textChannel = DiscordPl.getJda().getTextChannelById(chatID);
        textChannel.sendMessage("[MINECRAFT] " + e.getPlayer().getName() + " >> " + message).queue();
    }
}
