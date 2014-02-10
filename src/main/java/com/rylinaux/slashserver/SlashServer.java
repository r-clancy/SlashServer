package com.rylinaux.slashserver;

import com.rylinaux.slashserver.commands.SlashServerCommand;

import net.md_5.bungee.api.plugin.Plugin;

public class SlashServer extends Plugin {

    @Override
    public void onEnable() {
        for (String server : this.getProxy().getServers().keySet()) {
            this.getProxy().getPluginManager().registerCommand(this, new SlashServerCommand(server, String.format("ss.%s", server)));
        }
    }

    @Override
    public void onDisable() {
        this.getProxy().getPluginManager().unregisterCommands(this);
    }

}