package org.hopto.pcrhome.discordpl;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.event.Listener;
import org.hopto.pcrhome.discordpl.Commands.discord.InfoCmd;
import org.hopto.pcrhome.discordpl.Commands.discord.PlayerCmd;
import org.hopto.pcrhome.discordpl.Commands.discord.WhitelistCmd;
import org.hopto.pcrhome.discordpl.chatEvents.DiscordChat;
import org.hopto.pcrhome.discordpl.chatEvents.MinecraftChat;
import org.hopto.pcrhome.discordpl.chatEvents.PlayerServerInteract;

import javax.security.auth.login.LoginException;

public class DiscordBot extends ListenerAdapter implements Listener {
    public DiscordPl plugin;
    public JDA jda;

    public String token;
    public String chatID;

    public DiscordBot(DiscordPl pl, String token, String chatID){
        this.plugin = pl;

        this.token = token;
        this.chatID = chatID;

        startBot();

        /*
        CHAT INTERACTION EVENTS
         */
        plugin.getServer().getPluginManager().registerEvents(new MinecraftChat(), plugin);
        jda.addEventListener(new DiscordChat());
        plugin.getServer().getPluginManager().registerEvents(new PlayerServerInteract(), plugin);

        /*
        DISCORD COMMANDS
         */
        jda.addEventListener(new InfoCmd());
        jda.addEventListener(new PlayerCmd());
        jda.addEventListener(new WhitelistCmd());

        jda.getPresence().setActivity(Activity.playing("on pcrhome.hopto.org"));

    }

    public void startBot() {
        try{
            jda = new JDABuilder(AccountType.BOT).setToken(token).build();
        } catch (LoginException e){
            e.printStackTrace();
        }


    }

    public void stopBot() {
        jda.shutdown();
    }

    public JDA getJda() {
        return jda;
    }


}
