/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.CommandSender
 *  net.md_5.bungee.api.ProxyServer
 *  net.md_5.bungee.api.config.ServerInfo
 *  net.md_5.bungee.api.connection.ProxiedPlayer
 */
package net.rfpixel.queue;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.rfpixel.bungee.util.MessageUtil;
import net.rfpixel.queue.ping.Pinger;
import net.rfpixel.queue.ping.ServerData;
import org.apache.commons.lang.Validate;

public class Server {
    private String proxyName;
    private String displayName;
    private String host;
    private int port;
    private int timeout;
    private Pinger pinger;
    private ServerData serverData;
    private boolean isOnline;

    public Server(String proxyName, String displayName, int timeout) {
        Validate.notNull(proxyName, "ProxyName cannot be null");
        this.proxyName = proxyName;
        if (displayName == null) {
            displayName = proxyName;
        }
        this.displayName = displayName;
        ServerInfo info = this.getServerInfo();
        if (info != null) {
            this.host = info.getAddress().getHostName();
            this.port = info.getAddress().getPort();
        } else {
            this.host = "localhost";
            this.port = 1;
        }
        if (timeout <= 0) {
            timeout = 1;
        }
        this.timeout = timeout;
        this.pinger = new Pinger(this.host, this.port, timeout);
    }

    public ServerInfo getServerInfo() {
        return ProxyServer.getInstance().getServerInfo(this.proxyName);
    }

    public boolean tryPing() {
        this.isOnline = this.pinger.ping();
        this.serverData = this.pinger.getServerData();
        return this.isOnline;
    }

    public boolean isOnline() {
        return this.isOnline;
    }

    public String getProxyName() {
        return this.proxyName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public ServerData getServerData() {
        return this.serverData;
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void tryConnect(ProxiedPlayer player) {
        ServerInfo info = this.getServerInfo();
        if (info != null) {
            player.connect(info);
        } else {
            MessageUtil.send((CommandSender)player, "&c\u670d\u52a1\u5668 &a" + this.proxyName + "&c \u4e0d\u5b58\u5728, \u8bf7\u8054\u7cfb\u7ba1\u7406\u5458\u8fdb\u884c\u4fee\u590d\uff01");
        }
    }

    public String toString() {
        return "Server [proxyName=" + this.proxyName + ", displayName=" + this.displayName + ", host=" + this.host + ", port=" + this.port + ", timeout=" + this.timeout + ", pinger=" + this.pinger + ", serverInfo=" + this.serverData + ", isOnline=" + this.isOnline + "]";
    }
}

