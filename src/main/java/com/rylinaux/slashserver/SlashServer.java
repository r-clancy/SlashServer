package com.rylinaux.slashserver;

import com.rylinaux.slashserver.commands.SlashServerCommand;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;

public class SlashServer extends Plugin {

    public static final String PREFIX = ChatColor.GRAY + "[" + ChatColor.GREEN + "SlashServer" + ChatColor.GRAY + "] ";

    @Override
    public void onEnable() {
        for (String server : this.getProxy().getServers().keySet()) {
            this.getProxy().getPluginManager().registerCommand(this, new SlashServerCommand(server, "ss." + server));
        }
    }

    @Override
    public void onDisable() {
        this.getProxy().getPluginManager().unregisterCommands(this);
    }

}