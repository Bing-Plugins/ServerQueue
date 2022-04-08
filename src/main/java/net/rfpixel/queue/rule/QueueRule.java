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

public interface QueueRule {
    public Server queue(ProxiedPlayer var1, List<Server> var2);
}

