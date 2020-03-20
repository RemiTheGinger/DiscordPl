package org.hopto.pcrhome.discordpl.chatEvents;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.hopto.pcrhome.discordpl.DiscordBot;
import org.hopto.pcrhome.discordpl.DiscordPl;

import javax.annotation.Nonnull;

public class DiscordChat extends ListenerAdapter {

    String chatID = DiscordPl.getChatID();

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        TextChannel textChannel = event.getChannel();
        User user = event.getAuthor();

        if(user.isBot() || user.isFake() ||event.isWebhookMessage()){
            return;
        } else {
            if(textChannel.getId().equals(chatID)){
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "[DISCORD] " + ChatColor.GOLD + user.getName() + ChatColor.LIGHT_PURPLE + " >> " + ChatColor.WHITE + message);
            }
        }
    }
}
