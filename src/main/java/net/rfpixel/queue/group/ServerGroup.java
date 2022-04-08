/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.connection.ProxiedPlayer
 */
package net.rfpixel.queue.group;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.rfpixel.queue.Server;
import net.rfpixel.queue.rule.MotdQueueRule;
import net.rfpixel.queue.rule.QueueRule;
import net.rfpixel.queue.rule.QueueRuleType;

public class ServerGroup {
    private final String name;
    private QueueRuleType queueRuleType;
    private String queueMotd;
    private Map<String, Server> servers = new LinkedHashMap<>();

    public ServerGroup(String name, QueueRuleType queueRuleType, String queueMotd, Map<String, Server> servers) {
        this(name, queueRuleType, servers);
        if (queueMotd == null) {
            throw new NullPointerException("QueueMotd not set");
        }
        this.queueMotd = queueMotd;
    }

    public ServerGroup(String name, QueueRuleType queueRuleType, Map<String, Server> servers) {
        this.name = name.toUpperCase();
        this.queueRuleType = queueRuleType;
        this.servers = servers;
    }

    public String getName() {
        return this.name;
    }

    public QueueRuleType getQueueRuleType() {
        return this.queueRuleType;
    }

    public void setQueueRuleType(QueueRuleType queueRuleType) {
        this.queueRuleType = queueRuleType;
    }

    public List<Server> getServers() {
        return new ArrayList<Server>(this.servers.values());
    }
    
    public ArrayList<String> getServerNames() {
    	return new ArrayList<String>(this.servers.keySet());
    }
    
    public Server getServer(String serverName) {
        return this.servers.get(serverName);
    }

    public Server queue(ProxiedPlayer player) {
        Server server;
        if (this.queueRuleType.name().startsWith("MOTD")) {
            MotdQueueRule queueRule = (MotdQueueRule)this.queueRuleType.getQueueRule();
            server = queueRule.queue(player, this.queueMotd, new ArrayList<Server>(this.servers.values()));
        } else {
            QueueRule queueRule = this.queueRuleType.getQueueRule();
            server = queueRule.queue(player, new ArrayList<Server>(this.servers.values()));
        }
        return server;
    }

    public Server queueAndConnect(ProxiedPlayer player) {
        Server server = this.queue(player);
        if (server == null) {
            return null;
        }
        server.tryConnect(player);
        return server;
    }

    public String toString() {
        return "ServerGroup [name=" + this.name + ", queueRule=" + (Object)((Object)this.queueRuleType) + ", servers=" + this.servers + "]";
    }
}

