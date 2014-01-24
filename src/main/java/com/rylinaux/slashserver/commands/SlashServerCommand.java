package com.rylinaux.slashserver.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class SlashServerCommand extends Command {

    public SlashServerCommand(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        String server = this.getName();

        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(new ComponentBuilder("You don't have permission to do this.").color(ChatColor.RED).create());
            return;
        }

        if (!(sender instanceof ProxiedPlayer)) {
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (player.getServer().getInfo().getName().equalsIgnoreCase(server)) {
            player.sendMessage(new ComponentBuilder("You are already on that server.").color(ChatColor.RED).create());
            return;
        }

        ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(server);

        if (serverInfo == null) {
            player.sendMessage(new ComponentBuilder("Server not found.").color(ChatColor.RED).create());
            return;
        }

        if (!serverInfo.canAccess(player)) {
            player.sendMessage(new ComponentBuilder("You aren't allowed to access this server.").color(ChatColor.RED).create());
            return;
        }

        player.connect(serverInfo);

    }

}
