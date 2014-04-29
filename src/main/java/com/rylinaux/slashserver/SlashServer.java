package com.rylinaux.slashserver;

/*
 * #%L
 * slashserver
 * %%
 * Copyright (C) 2014 slashserver
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

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