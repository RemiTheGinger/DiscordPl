package org.hopto.pcrhome.discordpl;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import org.bukkit.plugin.java.JavaPlugin;
import org.hopto.pcrhome.discordpl.Commands.minecraft.DiscordCmd;


public class DiscordPl extends JavaPlugin {

    private static DiscordPl instance;

    public static DiscordBot discordBot;
    public static JDA jda;

    public static String botToken;
    public static String chatID;
    public static String prefix;
    public static String commandChannel;
    public static String modoRole;

    public static long serverStart;

    @Override
    public void onEnable() {
        instance = this;

        //Get time when server load
        serverStart = System.currentTimeMillis();

        botToken = this.getConfig().getString("Bot.Token");
        chatID = this.getConfig().getString("Bot.chatID");
        commandChannel = getConfig().getString("Bot.commandChannel");
        modoRole = getConfig().getString("Bot.modoRole");

        prefix = getConfig().getString("Bot.Prefix");

        //Websocket Connection
        discordBot = new DiscordBot(this, botToken, chatID);
        jda = discordBot.getJda();

        getCommand("discord").setExecutor(new DiscordCmd());
    }

    @Override
    public void onDisable() {
        if(discordBot != null){
            discordBot.stopBot();
        }

    }

    public static boolean isModo(Member member){
        for(Role role : member.getRoles()){
            if(role.getId().equals(modoRole)){
                return true;
            }
        }
        return false;
    }

    /*
    GET SERVER UPTIME
     */
    public static String getUptime() {
        long now = System.currentTimeMillis();
        long diff = now - serverStart;

        String upTime = (int)(diff / 86400000L) + " Jours " + (int)(diff / 3600000L % 24L) + " Heures " + (int)(diff / 60000L % 60L) + " Minutes "
                + (int)(diff / 1000L % 60L) + " Secondes";
        return upTime;
    }

    /*
    GETTER FUNCTION
     */

    public static DiscordPl getInstance(){
        return instance;
    }

    public static DiscordBot getDiscordBot() {
        return discordBot;
    }

    public static String getChatID() {
        return chatID;
    }

    public static String getBotToken() {
        return botToken;
    }

    public static JDA getJda() {
        return jda;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static String getCommandChannel() {
        return commandChannel;
    }
}
