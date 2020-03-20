package org.hopto.pcrhome.discordpl.Commands.discord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.hopto.pcrhome.discordpl.DiscordPl;

import javax.annotation.Nonnull;

public class InfoCmd extends ListenerAdapter {

    DiscordPl plugin = DiscordPl.getInstance();

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(event.getChannel().getId().equals(DiscordPl.getCommandChannel())){
            if(args[0].equalsIgnoreCase(DiscordPl.getPrefix() + "info")){
                EmbedBuilder info = new EmbedBuilder();
                info.setTitle("Minecraft Server");
                info.setDescription("Des information sur le serveur Minecraft");

                info.addBlankField(false);
                info.addField("MOTD", Bukkit.getServer().getMotd(), false);

                info.addField("⏱ Uptime", plugin.getUptime(), false);

                String onlinePlayers = "";
                int i = 0;

                if(Bukkit.getServer().getOnlinePlayers().size() < 1){
                    onlinePlayers = "Aucun joueur en ligne... 😢";
                } else {
                    for(Player p : Bukkit.getServer().getOnlinePlayers()){
                        i++;
                        onlinePlayers += p.getName();
                        if(i != Bukkit.getServer().getOnlinePlayers().size()){
                            onlinePlayers += ", ";
                        }
                    }
                }

                info.addField(String.valueOf(Bukkit.getOnlinePlayers().size()) + " joueurs en ligne !", onlinePlayers, false);
                info.setColor(0x1efc0a);

                event.getChannel().sendTyping().queue();
                event.getChannel().sendMessage(info.build()).queue();
                info.clear();
            }
        }
    }
}
