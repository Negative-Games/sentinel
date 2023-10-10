/*
 * Copyright © 2023 Demeng and Demeng Development
 *
 * All rights reserved.
 *
 * This software is a "remix" of the original and is solely intended as a means to reduce boilerplate code. It is not intended to be a substitute for the original software.
 *
 * The copyright owners, Demeng and Demeng Development, retain all rights to this software.
 *
 * This is a copyrighted work, and any unauthorized reproduction, distribution, or modification of this software is prohibited.
 */

package games.negative.sentinel.util;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public enum Logger {
    // Make this go by plugin.yml name
    URGENT("urgent", "&c[Negative Games Licences]: "),
    WARNING("warning", "&e[Negative Games Licences]: "),
    NORMAL("normal", "&a[Negative Games Licences]: "),
    STARTUP("startup", "&6[Negative Games Licences]: ");

    private final String id;
    @Getter
    private final String display;

    Logger(String id, String display) {
        this.id = id;
        this.display = display;
    }

    public String getID() {
        return id;
    }

    public void sendLog(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', display + message));
    }
}