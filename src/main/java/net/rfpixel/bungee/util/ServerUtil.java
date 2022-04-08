/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.plugin.Plugin
 */
package net.rfpixel.bungee.util;

import net.md_5.bungee.api.plugin.Plugin;

public class ServerUtil {
    public static void sendExceptionMessage(Plugin plugin, Exception e) {
        if (plugin != null && e != null) {
            StringBuilder sb = new StringBuilder("&c");
            int i = 1;
            while (i <= 100) {
                sb.append("=");
                ++i;
            }
            String bar = sb.toString();
            MessageUtil.sendToConsole(bar);
            MessageUtil.sendToConsole("&a" + PluginUtil.getName(plugin) + " &ehas an exception.");
            MessageUtil.sendToConsole("");
            MessageUtil.sendToConsole("&bType: &r" + e.getClass().getName());
            MessageUtil.sendToConsole("&bMessage: &r" + e.getMessage());
            MessageUtil.sendToConsole("&bDetail: &r");
            StackTraceElement[] stackTraceElementArray = e.getStackTrace();
            int n = stackTraceElementArray.length;
            int n2 = 0;
            while (n2 < n) {
                StackTraceElement s = stackTraceElementArray[n2];
                MessageUtil.sendToConsole("\t&e" + s);
                ++n2;
            }
            MessageUtil.sendToConsole(bar);
        }
    }
}

