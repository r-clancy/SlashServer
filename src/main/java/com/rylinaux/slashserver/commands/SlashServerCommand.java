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
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Command for quickly switching between servers.
 *
 * @author rylinaux
 */
public class SlashServerCommand extends Command {

    /**
     * Construct the object.
     *
     * @param name       the name of the command
     * @param permission the permission of the command
     * @param aliases    the aliases of the command
     */
    public SlashServerCommand(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        String server = this.getName();

        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(new TextComponent(SlashServer.PREFIX + ChatColor.RED + "You don't have permission to do this."));
            return;
        }

        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent(SlashServer.PREFIX + ChatColor.RED + "You must be a player do to this."));
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (player.getServer().getInfo().getName().equalsIgnoreCase(server)) {
            player.sendMessage(new TextComponent(SlashServer.PREFIX + ChatColor.RED + "You are already on that server."));
            return;
        }

        ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(server);

        if (serverInfo == null) {
            player.sendMessage(new TextComponent(SlashServer.PREFIX + ChatColor.RED + "Server not found."));
            return;
        }

        if (!serverInfo.canAccess(player)) {
            player.sendMessage(new TextComponent(SlashServer.PREFIX + ChatColor.RED + "You aren't allowed to access this server."));
            return;
        }

        player.connect(serverInfo);

    }

}
