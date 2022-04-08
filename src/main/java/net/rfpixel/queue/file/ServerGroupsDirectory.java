/*
 * Decompiled with CFR 0.151.
 */
package net.rfpixel.queue.file;

import java.io.File;
import net.rfpixel.queue.main.Main;

public class ServerGroupsDirectory {
    private File directory = new File(Main.getInstance().getDataFolder(), "server-groups");

    public ServerGroupsDirectory() {
        this.directory.mkdirs();
    }

    public File getDirectory() {
        return this.directory;
    }
}

