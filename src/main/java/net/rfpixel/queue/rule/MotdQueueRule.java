/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.connection.ProxiedPlayer
 */
package net.rfpixel.queue.rule;

import java.util.List;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.rfpixel.queue.Server;
import net.rfpixel.queue.rule.QueueRule;

public interface MotdQueueRule
extends QueueRule {
    public Server queue(ProxiedPlayer var1, String var2, List<Server> var3);
}

