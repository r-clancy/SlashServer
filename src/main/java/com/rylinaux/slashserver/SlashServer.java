package com.rylinaux.slashserver;

import com.google.common.collect.Lists;

import com.rylinaux.slashserver.commands.ReloadCommand;
import com.rylinaux.slashserver.commands.SlashServerCommand;

import java.util.ArrayList;
import java.util.List;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * Plugin used to quickly switch between servers by just using their name.
 *
 * @author rylinaux
 */
public class SlashServer extends Plugin {

    /**
     * The prefix of the plugin.
     */
    public static final String PREFIX = ChatColor.GRAY + "[" + ChatColor.GREEN + "SlashServer" + ChatColor.GRAY + "] ";

    /**
     * The list of commands.
     */
    private final List<Command> serverCommands = new ArrayList<>();

    @Override
    public void onEnable() {
        loadServers();
        this.getProxy().getPluginManager().registerCommand(this, new ReloadCommand(this, "ssreload", "ss.reload"));
    }

    @Override
    public void onDisable() {
        serverCommands.clear();
        this.getProxy().getPluginManager().unregisterCommands(this);
    }

    /**
     * Loads all the servers connected to the Bungee instance.
     */
    public void loadServers() {
        for (String server : this.getProxy().getServers().keySet()) {
            Command command = new SlashServerCommand(server, "ss." + server);
            this.getProxy().getPluginManager().registerCommand(this, command);
            serverCommands.add(command);
        }
    }

    /**
     * Removes all the servers from the list and unregisters their commands.
     */
    public void unloadServers() {
        for (Command command : serverCommands)
            this.getProxy().getPluginManager().unregisterCommand(command);
        serverCommands.clear();
    }

}