/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  com.google.gson.internal.LinkedTreeMap
 *  net.md_5.bungee.api.ChatColor
 */
package net.rfpixel.queue.ping;

import com.google.gson.internal.LinkedTreeMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.md_5.bungee.api.ChatColor;

public class ServerData {
    private boolean isInit;
    private Object description;
    private String motd = "";
    private Players players;
    private Version version;
    private String favicon;
    private Object modinfo;
    private long ping = -1L;

    public String getMotd() {
        if (!this.isInit) {
            this.isInit = true;
            if (this.description != null) {
                if (this.description instanceof LinkedTreeMap) {
                    Map map = (Map)this.description;
                    ArrayList<String> l = new ArrayList<String>();
                    for (Object key : map.keySet()) {
                        ChatColor chatColor = ChatColor.RESET;
                        StringBuilder text = new StringBuilder();
                        Object value = map.get(key);
                        if (value instanceof List) {
                            List list = (List)value;
                            for (Object v : list) {
                                if (!(v instanceof Map)) continue;
                                Map m = (Map)v;
                                for (Object s : m.keySet()) {
                                    Object o = m.get(s);
                                    if (s.equals("color")) {
                                        chatColor = ChatColor.valueOf((String)((String)o).toUpperCase());
                                    }
                                    if (!s.equals("text")) continue;
                                    text.append(String.valueOf(chatColor.toString()) + o);
                                }
                            }
                        } else if (value instanceof String) {
                            text.append(value);
                        }
                        l.add(String.valueOf(chatColor.toString()) + text.toString());
                    }
                    String string = ((Object)l).toString();
                    if (string.endsWith(", \u00a7r]")) {
                        this.motd = string.substring(3, string.length() - 5);
                    } else if (string.endsWith("]")) {
                        this.motd = string.substring(3, string.length() - 1);
                    }
                } else if (this.description instanceof String) {
                    this.motd = (String)this.description;
                }
            }
        }
        return this.motd;
    }

    public Players getPlayers() {
        return this.players;
    }

    public Version getVersion() {
        return this.version;
    }

    public String getFavicon() {
        return this.favicon;
    }

    public Object getModinfo() {
        return this.modinfo;
    }

    public long getPing() {
        return this.ping;
    }

    protected void setPing(long ping) {
        this.ping = ping;
    }

    public String toString() {
        return "ServerInfo [description=" + this.getMotd() + ", players=" + this.players + ", version=" + this.version + ", favicon=" + this.favicon + ", modinfo=" + this.modinfo + ", ping=" + this.ping + "]";
    }

    public class Player {
        private String name;
        private String id;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUUID() {
            return this.id;
        }

        public void setUUID(String id) {
            this.id = id;
        }

        public String toString() {
            return "Player [name=" + this.name + ", id=" + this.id + "]";
        }
    }

    public class Players {
        private int max;
        private int online;
        private List<Player> sample;

        public int getMax() {
            return this.max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getOnline() {
            return this.online;
        }

        public void setOnline(int online) {
            this.online = online;
        }

        public List<Player> getSample() {
            return this.sample;
        }

        public void setSample(List<Player> sample) {
            this.sample = sample;
        }

        public String toString() {
            return "Players [max=" + this.max + ", online=" + this.online + ", sample=" + this.sample + "]";
        }
    }

    public class Version {
        private String name;
        private String protocol;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProtocol() {
            return this.protocol;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }

        public String toString() {
            return "Version [name=" + this.name + ", protocol=" + this.protocol + "]";
        }
    }
}

