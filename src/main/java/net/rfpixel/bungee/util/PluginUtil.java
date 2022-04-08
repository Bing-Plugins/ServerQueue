/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.plugin.Plugin
 */
package net.rfpixel.bungee.util;

import net.md_5.bungee.api.plugin.Plugin;
import org.apache.commons.lang.Validate;

public class PluginUtil {
    public static String getName(Plugin plugin) {
        Validate.notNull(plugin, "Plugin cannot be null");
        return plugin.getDescription().getName();
    }

    public static String getVersion(Plugin plugin) {
        Validate.notNull(plugin, "Plugin cannot be null");
        return plugin.getDescription().getVersion();
    }

    public static String getMainPath(Plugin plugin) {
        Validate.notNull(plugin, "Plugin cannot be null");
        return plugin.getDescription().getMain();
    }
}

