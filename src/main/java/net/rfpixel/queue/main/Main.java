/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.plugin.Command
 *  net.md_5.bungee.api.plugin.Plugin
 */
package net.rfpixel.queue.main;

import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;
import net.rfpixel.bungee.util.MessageUtil;
import net.rfpixel.queue.cmd.MainCmd;
import net.rfpixel.queue.cmd.QueueCmd;
import net.rfpixel.queue.file.FilesManager;
import net.rfpixel.queue.group.ServerGroupsLoader;

public class Main
extends Plugin {
    private static Main instance;

    public void onEnable() {
        instance = this;
        this.getProxy().registerChannel("ServerQueue");
        FilesManager.loadAll();
        try {
            ServerGroupsLoader.init();
            ServerGroupsLoader.loadAll();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.getProxy().getPluginManager().registerCommand((Plugin)this, (Command)new MainCmd());
        this.getProxy().getPluginManager().registerCommand((Plugin)this, (Command)new QueueCmd());
        MessageUtil.sendToConsole("[ServerQueue] Enabled successfully.");
    }

    public void onDisable() {
        this.getProxy().getPluginManager().unregisterCommands((Plugin)this);
        this.getProxy().unregisterChannel("ServerQueue");
        ServerGroupsLoader.unregisterAll();
        MessageUtil.sendToConsole("[ServerQueue] Disabled successfully.");
    }

    public static Main getInstance() {
        return instance;
    }
}

