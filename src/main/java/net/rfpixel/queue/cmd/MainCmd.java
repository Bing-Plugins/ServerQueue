/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.CommandSender
 *  net.md_5.bungee.api.plugin.Command
 */
package net.rfpixel.queue.cmd;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.rfpixel.bungee.util.MessageUtil;
import net.rfpixel.queue.main.Main;

public class MainCmd
extends Command {
    public MainCmd() {
        //super("advancedserverqueue", "", new String[]{"serverqueue"});
    	super("serverqueue");
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length == 1 && (args[0]).equalsIgnoreCase("reload") && sender.hasPermission("serverqueue.reload")) {
            MessageUtil.send(sender, "&2Reloading...");
            try {
                Main.getInstance().onDisable();
                Main.getInstance().onEnable();
                MessageUtil.send(sender, "&aReload complete.");
            }
            catch (Exception e) {
                MessageUtil.send(sender, "&cReload failed.");
            }
        }
    }
}

