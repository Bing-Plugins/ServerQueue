/*
 * Decompiled with CFR 0.151.
 */
package net.rfpixel.queue.file;

public class FilesManager {
    private static ServerGroupsDirectory serverGroupsDirectory;
    private static MessageFile messageFile;

    public static void loadAll() {
        serverGroupsDirectory = new ServerGroupsDirectory();
        messageFile = new MessageFile();
    }

    public static ServerGroupsDirectory getServerGroupsDirectory() {
        return serverGroupsDirectory;
    }

    public static MessageFile getMessageFile() {
        return messageFile;
    }
}

