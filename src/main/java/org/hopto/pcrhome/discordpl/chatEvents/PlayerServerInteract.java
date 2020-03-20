package org.hopto.pcrhome.discordpl.chatEvents;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.hopto.pcrhome.discordpl.DiscordPl;

public class PlayerServerInteract implements Listener {

    String chatID = DiscordPl.getChatID();
    EmbedBuilder serverInteractInfo = new EmbedBuilder();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        TextChannel serverChat = DiscordPl.getJda().getTextChannelById(chatID);

        serverInteractInfo.setTitle("✅ " + event.getPlayer().getName() + " >> Joined !");
        serverInteractInfo.setColor(0x1efc0a);

        serverChat.sendMessage(serverInteractInfo.build()).queue();
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        TextChannel serverChat = DiscordPl.getJda().getTextChannelById(chatID);

        serverInteractInfo.setTitle("⛔ " + event.getPlayer().getName() + " >> Left !");
        serverInteractInfo.setColor(0xe00804);

        serverChat.sendMessage(serverInteractInfo.build()).queue();
    }
}
