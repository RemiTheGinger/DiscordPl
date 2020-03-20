package org.hopto.pcrhome.discordpl.Commands.discord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.hopto.pcrhome.discordpl.DiscordPl;

import javax.annotation.Nonnull;

public class PlayerCmd extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(event.getChannel().getId().equals(DiscordPl.getCommandChannel())){
            if(args[0].equalsIgnoreCase(DiscordPl.getPrefix() + "player")){

                EmbedBuilder embed = new EmbedBuilder();

                if(args.length == 1){
                    embed.setTitle("⛔ Il me manque le nom du joueur");
                    embed.setDescription(DiscordPl.getPrefix() + "player (nom du joueur)");
                    embed.setColor(0xe00804);
                } else{
                    String pName = args[1];

                    if(Bukkit.getPlayer(pName) == null){
                        embed.setTitle("⛔ Ce Joueur est introuvable !");
                        embed.setColor(0xe00804);
                    } else {
                        Player p = Bukkit.getPlayer(pName);
                        embed.setTitle(pName);
                        embed.setThumbnail("https://crafatar.com/avatars/" + p.getUniqueId().toString());
                        embed.setDescription(p.getUniqueId().toString());

                        embed.addField("NameMC", "https://mine.ly/" + p.getUniqueId().toString(), false);

                        embed.setColor(0x1efc0a);
                    }
                }

                event.getChannel().sendTyping().queue();
                event.getChannel().sendMessage(embed.build()).queue();
                embed.clear();
            }
        }
    }
}
