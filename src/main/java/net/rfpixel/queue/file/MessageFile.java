/*
 * Decompiled with CFR 0.151.
 */
package net.rfpixel.queue.file;

import java.util.Map;
import net.rfpixel.queue.file.YamlConfig;

public class MessageFile
extends YamlConfig {
    public MessageFile() {
        super("message.yml");
        this.load();
    }

    @Override
    public void initDefaults(Map<String, Object> defaults) {
        defaults.put("Usage", "&eUsage: &f/queue <ServerGroupName>");
        defaults.put("No Available Server", "&cNo server is available now, please try again later.");
        defaults.put("Connecting", "&aConnecting to server %server%...");
        defaults.put("Unknown Server Group", "&cUnknown server group &e%group%&c.");
        defaults.put("Reloading", "&2Reloading...");
        defaults.put("Reload Complete", "&aReload complete.");
        defaults.put("Reload Failed", "&c2Reload failed.");
    }

    @Override
    public void initSets(Map<String, Object> sets) {
    }

    public static class MessagePath {
        public static final String USAGE = "Usage";
        public static final String NO_AVAILABLE_SERVER = "No Available Server";
        public static final String CONNECTING = "Connecting";
        public static final String UNKNOWN_SERVER_GROUP = "Unknown Server Group";
        public static final String RELOADING = "Reloading";
        public static final String RELOAD_COMPLETE = "Reload Complete";
        public static final String RELOAD_FAILED = "Reload Failed";
    }
}

