/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.BungeeCord
 *  net.md_5.bungee.api.CommandSender
 *  net.md_5.bungee.api.connection.ProxiedPlayer
 *  net.md_5.bungee.api.plugin.Command
 *  net.md_5.bungee.api.plugin.Plugin
 *  net.md_5.bungee.config.Configuration
 */
package net.rfpixel.queue.cmd;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.rfpixel.bungee.util.MessageUtil;
import net.rfpixel.queue.Server;
import net.rfpixel.queue.file.FilesManager;
import net.rfpixel.queue.group.ServerGroup;
import net.rfpixel.queue.group.ServerGroupsLoader;
import net.rfpixel.queue.main.Main;

public class QueueCmd
extends Command {
    public QueueCmd() {
        super("queue");
    }

    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer)sender;
        Configuration config = FilesManager.getMessageFile().getConfiguration();
        if (args.length == 1) {
            ServerGroup serverGroup = ServerGroupsLoader.get(args[0]);
            if (serverGroup == null) {
                MessageUtil.send((CommandSender)player, config.getString("Unknown Server Group").replace("%group%", args[0].toUpperCase()));
            } else {
                ProxyServer.getInstance().getScheduler().runAsync((Plugin)Main.getInstance(), () -> {
                    Server server = serverGroup.queue(player);
                    if (server == null) {
                        MessageUtil.send((CommandSender)player, config.getString("No Available Server"));
                    } else {
                        MessageUtil.send((CommandSender)player, config.getString("Connecting").replace("%server%", server.getDisplayName()));
                        server.tryConnect(player);
                    }
                });
            }
        } else {
            MessageUtil.send((CommandSender)player, config.getString("Usage"));
        }
    }
}

