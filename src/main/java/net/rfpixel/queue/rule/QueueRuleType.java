/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.connection.ProxiedPlayer
 */
package net.rfpixel.queue.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.rfpixel.queue.Server;
import net.rfpixel.queue.ping.ServerData;
import net.rfpixel.queue.rule.MotdQueueRule;
import net.rfpixel.queue.rule.QueueRule;

public enum QueueRuleType {
    MORE_ONLINE(new MoreOnline()),
    LESS_ONLINE(new LessOnline()),
    MOTD(new Motd()),
    MOTD_AND_MORE_ONLINE(new MotdAndMoreOnline()),
    MOTD_AND_LESS_ONLINE(new MotdAndLessOnline()),
    RANDOM(new Random());

    private static final java.util.Random RAMDOM;
    private final QueueRule queueRule;

    static {
        RAMDOM = new java.util.Random();
    }

    private QueueRuleType(QueueRule queueRule) {
        this.queueRule = queueRule;
    }

    public QueueRule getQueueRule() {
        return this.queueRule;
    }

    private static class LessOnline
    implements QueueRule {
        private LessOnline() {
        }

        @Override
        public Server queue(ProxiedPlayer player, List<Server> servers) {
            int minOnline = Integer.MAX_VALUE;
            HashMap sameOnlineServers = new HashMap();
            for (Server server : servers) {
                int max;
                ServerData.Players players;
                int online;
                if (player.getServer().getInfo() == server.getServerInfo() || !server.tryPing() || (online = (players = server.getServerData().getPlayers()).getOnline()) >= (max = players.getMax())) continue;
                if (!sameOnlineServers.containsKey(online)) {
                    sameOnlineServers.put(online, new ArrayList());
                }
                ((List)sameOnlineServers.get(online)).add(server);
                if (online >= minOnline) continue;
                minOnline = online;
            }
            if (sameOnlineServers.isEmpty()) {
                return null;
            }
            List minOnlineServers = (List)sameOnlineServers.get(minOnline);
            return (Server)minOnlineServers.get(RAMDOM.nextInt(minOnlineServers.size()));
        }

        /* synthetic */ LessOnline(LessOnline lessOnline, LessOnline lessOnline2) {
            this();
        }
    }

    private static class MoreOnline
    implements QueueRule {
        private MoreOnline() {
        }

        @Override
        public Server queue(ProxiedPlayer player, List<Server> servers) {
            int maxOnline = 0;
            HashMap sameOnlineServers = new HashMap();
            for (Server server : servers) {
                int max;
                ServerData.Players players;
                int online;
                if (player.getServer().getInfo() == server.getServerInfo() || !server.tryPing() || (online = (players = server.getServerData().getPlayers()).getOnline()) >= (max = players.getMax())) continue;
                if (!sameOnlineServers.containsKey(online)) {
                    sameOnlineServers.put(online, new ArrayList());
                }
                ((List)sameOnlineServers.get(online)).add(server);
                if (online <= maxOnline) continue;
                maxOnline = online;
            }
            if (sameOnlineServers.isEmpty()) {
                return null;
            }
            List maxOnlineServers = (List)sameOnlineServers.get(maxOnline);
            return (Server)maxOnlineServers.get(RAMDOM.nextInt(maxOnlineServers.size()));
        }

        /* synthetic */ MoreOnline(MoreOnline moreOnline, MoreOnline moreOnline2) {
            this();
        }
    }

    private static class Motd
    implements MotdQueueRule {
        private Motd() {
        }

        @Override
        public Server queue(ProxiedPlayer player, String motd, List<Server> servers) {
            ArrayList<Server> availableServers = new ArrayList<Server>();
            for (Server server : servers) {
                int max;
                ServerData.Players players;
                int online;
                if (player.getServer().getInfo() == server.getServerInfo() || !server.tryPing() || (online = (players = server.getServerData().getPlayers()).getOnline()) >= (max = players.getMax()) || !server.getServerData().getMotd().equals(motd)) continue;
                availableServers.add(server);
            }
            if (availableServers.isEmpty()) {
                return null;
            }
            return (Server)availableServers.get(RAMDOM.nextInt(availableServers.size()));
        }

        @Override
        public Server queue(ProxiedPlayer player, List<Server> servers) {
            throw new UnsupportedOperationException();
        }
    }

    private static class MotdAndLessOnline
    extends LessOnline
    implements MotdQueueRule {
        private MotdAndLessOnline() {
            super(null, null);
        }

        @Override
        public Server queue(ProxiedPlayer player, String motd, List<Server> servers) {
            ArrayList<Server> availableServers = new ArrayList<Server>();
            for (Server server : servers) {
                int max;
                ServerData.Players players;
                int online;
                if (player.getServer().getInfo() == server.getServerInfo() || !server.tryPing() || (online = (players = server.getServerData().getPlayers()).getOnline()) >= (max = players.getMax()) || !server.getServerData().getMotd().equals(motd)) continue;
                availableServers.add(server);
            }
            if (availableServers.isEmpty()) {
                return null;
            }
            return this.queue(player, availableServers);
        }
    }

    private static class MotdAndMoreOnline
    extends MoreOnline
    implements MotdQueueRule {
        private MotdAndMoreOnline() {
            super(null, null);
        }

        @Override
        public Server queue(ProxiedPlayer player, String motd, List<Server> servers) {
            ArrayList<Server> availableServers = new ArrayList<Server>();
            for (Server server : servers) {
                int max;
                ServerData.Players players;
                int online;
                if (player.getServer().getInfo() == server.getServerInfo() || !server.tryPing() || (online = (players = server.getServerData().getPlayers()).getOnline()) >= (max = players.getMax()) || !server.getServerData().getMotd().equals(motd)) continue;
                availableServers.add(server);
            }
            if (availableServers.isEmpty()) {
                return null;
            }
            return this.queue(player, availableServers);
        }
    }

    private static class Random
    implements QueueRule {
        private Random() {
        }

        @Override
        public Server queue(ProxiedPlayer player, List<Server> servers) {
            ArrayList<Server> availableServers = new ArrayList<Server>();
            for (Server server : servers) {
                int max;
                ServerData.Players players;
                int online;
                if (player.getServer().getInfo() == server.getServerInfo() || !server.tryPing() || (online = (players = server.getServerData().getPlayers()).getOnline()) >= (max = players.getMax())) continue;
                availableServers.add(server);
            }
            if (availableServers.isEmpty()) {
                return null;
            }
            return (Server)availableServers.get(RAMDOM.nextInt(availableServers.size()));
        }
    }
}

