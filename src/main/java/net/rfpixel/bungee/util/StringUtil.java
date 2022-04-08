/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.ChatColor
 */
package net.rfpixel.bungee.util;

import net.md_5.bungee.api.ChatColor;

public class StringUtil {
    public static String translateColorCodes(String str) {
        return ChatColor.translateAlternateColorCodes((char)'&', (String)str);
    }
}

