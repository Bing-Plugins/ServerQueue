/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.config.Configuration
 *  net.md_5.bungee.config.ConfigurationProvider
 *  net.md_5.bungee.config.YamlConfiguration
 */
package net.rfpixel.queue.file;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.rfpixel.queue.main.Main;

public abstract class YamlConfig {
    private File folder;
    private File file;
    private Configuration configuration;

    public YamlConfig(String fileName) {
        this(null, fileName);
    }

    public YamlConfig(String folders, String fileName) {
        try {
            if (folders != null) {
                this.folder = new File(Main.getInstance().getDataFolder(), folders);
                this.file = new File(this.folder, fileName);
            } else {
                this.file = new File(Main.getInstance().getDataFolder(), fileName);
            }
            if (this.file.exists()) {
                this.configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(this.file);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void initDefaults(Map<String, Object> var1);

    public abstract void initSets(Map<String, Object> var1);

    public void load() {
        try {
            if (!Main.getInstance().getDataFolder().exists()) {
                Main.getInstance().getDataFolder().mkdirs();
            }
            if (this.folder != null && !this.folder.exists()) {
                this.folder.mkdirs();
            }
            if (!this.file.exists()) {
                this.file.createNewFile();
            }
            this.configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(this.file);
            LinkedHashMap<String, Object> defaults = new LinkedHashMap<String, Object>();
            this.initDefaults(defaults);
            for (String path : defaults.keySet()) {
                Object data = defaults.get(path);
                if (this.configuration.contains(path)) continue;
                this.configuration.set(path, data);
            }
            LinkedHashMap<String, Object> sets = new LinkedHashMap<String, Object>();
            this.initSets(sets);
            for (String path : sets.keySet()) {
                Object data = sets.get(path);
                this.configuration.set(path, data);
            }
            this.save();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(this.configuration, this.file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        try {
            if (!this.file.exists()) {
                this.load();
            }
            this.configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(this.file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return this.file;
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }
}

