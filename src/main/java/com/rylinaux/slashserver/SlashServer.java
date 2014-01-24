package com.rylinaux.slashserver;

import com.rylinaux.slashserver.commands.SlashServerCommand;

import lombok.Getter;

import net.md_5.bungee.api.plugin.Plugin;

public class SlashServer extends Plugin {

    @Getter
    private static SlashServer instance;

    @Override
    public void onEnable() {
        instance = this;
        loadCommands();
    }

    @Override
    public void onDisable() {
        unloadCommands();
        instance = null;
    }

    public void loadCommands() {
        for (String server : this.getProxy().getServers().keySet()) {
            this.getProxy().getPluginManager().registerCommand(this, new SlashServerCommand(server, String.format("ss.%s", server)));
        }
    }

    public void unloadCommands() {
        this.getProxy().getPluginManager().unregisterCommands(this);
    }

}