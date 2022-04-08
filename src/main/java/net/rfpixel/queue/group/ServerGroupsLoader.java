/*
 * Decompiled with CFR 0.151.
 */
package net.rfpixel.queue.group;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.rfpixel.queue.file.FilesManager;
import net.rfpixel.queue.file.ServerGroupFile;
import net.rfpixel.queue.file.YamlConfig;
import net.rfpixel.queue.rule.QueueRuleType;

public class ServerGroupsLoader {
    private static final Map<String, ServerGroup> serverGroups = new HashMap<String, ServerGroup>();
    public static Map<String, String> serverNameGroups = new HashMap<String, String>();

    public static void init() {
        File[] files = FilesManager.getServerGroupsDirectory().getDirectory().listFiles();
        if (files != null && files.length != 0) {
            return;
        }
        new YamlConfig("server-groups", "example-group.yml"){

            @Override
            public void initDefaults(Map<String, Object> defaults) {
                defaults.put("Group Name", "EXAMPLE_GROUP");
                defaults.put("Queue Rule", QueueRuleType.LESS_ONLINE.name());
                defaults.put("Servers List.Server_1.Proxy Name", "Server_1");
                defaults.put("Servers List.Server_1.Display Name", "DisplayName_1");
                defaults.put("Servers List.Server_1.Timeout", 20);
                defaults.put("Servers List.Server_2.Proxy Name", "Server_2");
                defaults.put("Servers List.Server_2.Display Name", "DisplayName_2");
                defaults.put("Servers List.Server_2.Timeout", 20);
                defaults.put("Servers List.Server_3.Proxy Name", "Server_3");
                defaults.put("Servers List.Server_3.Display Name", "DisplayName_3");
                defaults.put("Servers List.Server_3.Timeout", 20);
                defaults.put("Servers List.Server_4.Proxy Name", "Server_4");
                defaults.put("Servers List.Server_4.Display Name", "DisplayName_4");
                defaults.put("Servers List.Server_4.Timeout", 20);
            }

            @Override
            public void initSets(Map<String, Object> sets) {
            }
        }.load();
    }

    public static void loadAll() {
        ServerGroupsLoader.unregisterAll();
        File[] files = FilesManager.getServerGroupsDirectory().getDirectory().listFiles();
        if (files == null || files.length == 0) {
            return;
        }
        File[] fileArray = files;
        int n = files.length;
        int n2 = 0;
        while (n2 < n) {
            File file = fileArray[n2];
            try {
                ServerGroupsLoader.register(new ServerGroupFile(file.getName()).getServerGroup());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            ++n2;
        }
    }

    public static ServerGroup get(String name) {
        return serverGroups.get(name.toUpperCase());
    }

    public static Collection<ServerGroup> getServerGroups() {
        return new ArrayList<ServerGroup>(serverGroups.values());
    }

    public static void register(ServerGroup serverGroup) {
        serverGroups.put(serverGroup.getName(), serverGroup);
        
        for(String server : serverGroup.getServerNames()) {
        	serverNameGroups.put(server, serverGroup.getName());
        }
    }

    public static void unregister(ServerGroup serverGroup) {
        serverGroups.remove(serverGroup.getName());
    }

    public static void unregister(String name) {
        serverGroups.remove(name.toUpperCase());
    }

    public static void unregisterAll() {
        serverGroups.clear();
    }

    public static boolean isRegistered(ServerGroup serverGroup) {
        return serverGroups.containsKey(serverGroup.getName());
    }

    public static boolean isRegistered(String name) {
        return serverGroups.containsKey(name.toUpperCase());
    }
}

