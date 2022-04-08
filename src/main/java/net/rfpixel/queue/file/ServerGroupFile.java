/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.config.Configuration
 */
package net.rfpixel.queue.file;

import java.util.LinkedHashMap;
import java.util.Map;
import net.md_5.bungee.config.Configuration;
import net.rfpixel.queue.Server;
import net.rfpixel.queue.file.YamlConfig;
import net.rfpixel.queue.group.ServerGroup;
import net.rfpixel.queue.rule.QueueRuleType;

public class ServerGroupFile
extends YamlConfig {
    private final ServerGroup serverGroup;

    public ServerGroupFile(String fileName) {
        super("server-groups", fileName);
        QueueRuleType queueRule;
        this.load();
        Configuration config = this.getConfiguration();
        try {
            queueRule = QueueRuleType.valueOf(config.getString("Queue Rule").toUpperCase());
        }
        catch (Exception e) {
            e.printStackTrace();
            this.serverGroup = null;
            return;
        }
        String queueMotd = null;
        if (queueRule.name().startsWith("MOTD")) {
            queueMotd = config.getString("Queue Motd");
            if (queueMotd.equals("")) {
                queueMotd = null;
            }
        } else {
            queueMotd = "";
        }
        LinkedHashMap<String, Server> servers = new LinkedHashMap<String, Server>();
        for (String key : config.getSection("Servers List").getKeys()) {
            int timeout;
            String proxyName = config.getString("Servers List." + key + ".Proxy Name");
            if (proxyName == null) continue;
            String displayName = config.getString("Servers List." + key + ".Display Name");
            if (displayName == null) {
                displayName = proxyName;
            }
            if ((timeout = config.getInt("Servers List." + key + ".Timeout")) <= 0) {
                timeout = 1;
            }
            Server server = new Server(proxyName, displayName, timeout);
            servers.put(proxyName, server);
        }
        this.serverGroup = new ServerGroup(config.getString("Group Name").toUpperCase(), queueRule, queueMotd, servers);
    }

    @Override
    public void initDefaults(Map<String, Object> defaults) {
    }

    @Override
    public void initSets(Map<String, Object> sets) {
    }

    public ServerGroup getServerGroup() {
        return this.serverGroup;
    }

    public static class ServerGroupPath {
        public static final String GROUP_NAME = "Group Name";
        public static final String QUEUE_RULE = "Queue Rule";
        public static final String QUEUE_MOTD = "Queue Motd";
        public static final String SERVERS_LIST = "Servers List";
    }
}

