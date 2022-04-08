/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.CommandSender
 *  net.md_5.bungee.api.ProxyServer
 *  net.md_5.bungee.api.chat.BaseComponent
 *  net.md_5.bungee.api.chat.TextComponent
 */
package net.rfpixel.bungee.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.apache.commons.lang.Validate;

public class MessageUtil {
    public static void send(CommandSender sender, Object object) {
        Validate.notNull(sender, "Player cannot be null");
        Validate.notNull(object, "Object cannot be null");
        if (object instanceof Collection) {
            Collection collection = (Collection)object;
            collection.forEach(o -> sender.sendMessage((BaseComponent)new TextComponent(StringUtil.translateColorCodes(o.toString()))));
        } else {
            sender.sendMessage((BaseComponent)new TextComponent(StringUtil.translateColorCodes(object.toString())));
        }
    }

    public static void sendToConsole(Object object) {
        Validate.notNull(object, "Object cannot be null");
        if (object instanceof Iterable) {
            Iterator iterator = ((List)object).iterator();
            ProxyServer.getInstance().getConsole().sendMessage((BaseComponent)new TextComponent(StringUtil.translateColorCodes(iterator.next().toString())));
        } else {
            ProxyServer.getInstance().getConsole().sendMessage((BaseComponent)new TextComponent(StringUtil.translateColorCodes(object.toString())));
        }
    }
}

