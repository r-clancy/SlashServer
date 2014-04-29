package com.rylinaux.slashserver.commands;

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
