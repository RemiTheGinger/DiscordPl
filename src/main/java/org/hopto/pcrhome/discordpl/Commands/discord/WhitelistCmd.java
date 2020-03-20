package org.hopto.pcrhome.discordpl.Commands.discord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.hopto.pcrhome.discordpl.DiscordPl;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

public class WhitelistCmd extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(event.getChannel().getId().equals(DiscordPl.getCommandChannel())){
            if(args[0].equalsIgnoreCase(DiscordPl.getPrefix() + "whitelist")){
                EmbedBuilder returnMsg = new EmbedBuilder();

                if(args.length == 1){
                    returnMsg.setTitle("⛔ Il me faut le nom du joueur");
                    returnMsg.setColor(0xe00804);
                } else {
                    if(DiscordPl.isModo(event.getMember())){
                        String pname = args[1];
                        String apiUrl = "https://api.mojang.com/users/profiles/inecraft/"+pname;

                        try {
                            InputStream inputStream = new URL(apiUrl).openStream();
                            String PlayerJson = IOUtils.toString(inputStream);

                            if(PlayerJson.isEmpty()){
                                returnMsg.setTitle("⛔ Ce joueur n'as pas été trouvé");
                                returnMsg.setColor(0xe00804);
                            }else{
                                JSONObject PlayerObject = (JSONObject) JSONValue.parseWithException(PlayerJson);
                                UUID pUuid = UUID.fromString(PlayerObject.get("id").toString());

                                OfflinePlayer player = Bukkit.getOfflinePlayer(pUuid);
                                if(player.isWhitelisted()){
                                    returnMsg.setTitle(PlayerObject.get("name").toString() + " est déjà whitelist");
                                    returnMsg.setColor(0xe00804);
                                } else {
                                    player.setWhitelisted(true);

                                    returnMsg.setTitle("✅ " + PlayerObject.get("name").toString() + " a été ajouté a la whitelist");
                                    returnMsg.setColor(0x1efc0a);
                                }
                            }
                        } catch (IOException | ParseException e) {
                            returnMsg.setTitle("Une erreur c'est produit lors de cette commande");
                            returnMsg.setColor(0xe00804);
                            e.printStackTrace();
                        }

                    } else {
                        returnMsg.setTitle("⛔ Tu n'es pas modo");
                        returnMsg.setColor(0xe00804);
                    }
                }
                event.getChannel().sendTyping().queue();
                event.getChannel().sendMessage(returnMsg.build()).queue();
                returnMsg.clear();
            }
        }
    }
}
