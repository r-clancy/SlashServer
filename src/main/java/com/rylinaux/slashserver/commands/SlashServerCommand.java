package com.rylinaux.slashserver.commands;

import com.rylinaux.slashserver.SlashServer;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
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
            sender.sendMessage(new TextComponent(SlashServer.PREFIX + ChatColor.RED + "You don't have permission to do this."));
            return;
        }

        if (!(sender instanceof ProxiedPlayer)) {
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
