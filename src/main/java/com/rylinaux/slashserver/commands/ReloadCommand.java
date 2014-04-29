package com.rylinaux.slashserver.commands;

import com.rylinaux.slashserver.SlashServer;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

/**
 * Command used to reload the servers the plugin know of.
 *
 * @author rylinaux
 */
public class ReloadCommand extends Command {

    /**
     * The instance of the plugin.
     */
    private final SlashServer plugin;

    public ReloadCommand(SlashServer plugin, String name, String permission, String... aliases) {
        super(name, permission, aliases);
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(new TextComponent(SlashServer.PREFIX + ChatColor.RED + "You don't have permission to do this."));
            return;
        }

        plugin.unloadServers();
        plugin.loadServers();

        sender.sendMessage(new TextComponent(SlashServer.PREFIX + ChatColor.GREEN + "SlashServer has been reloaded."));

    }

}
