/*
 * Copyright © 2023 Demeng and Demeng Development
 *
 * All rights reserved.
 *
 * This software is a "remix" of the original and is solely intended as a means to reduce boilerplate code.
 * It is not intended to be a substitute for the original software.
 *
 * The copyright owners, Demeng and Demeng Development, retain all rights to this software.
 *
 * This is a copyrighted work, and any unauthorized reproduction, distribution, or modification of this software is prohibited.
 */

package games.negative.sentinel.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/**
 * A utility class for logging messages with different log levels.
 */
public enum Logger {
    /**
     * Represents an urgent log level.
     */
    URGENT("urgent", "&c[Negative Games Licences]: "),

    /**
     * Represents a warning log level.
     */
    WARNING("warning", "&e[Negative Games Licences]: "),

    /**
     * Represents a normal log level.
     */
    NORMAL("normal", "&a[Negative Games Licences]: "),

    /**
     * Represents a startup log level.
     */
    STARTUP("startup", "&6[Negative Games Licences]: ");

    private final String id;
    private final String display;

    Logger(String id, String display) {
        this.id = id;
        this.display = display;
    }

    /**
     * Gets the unique identifier of this log level.
     *
     * @return The log level's identifier.
     */
    public String getID() {
        return id;
    }

    /**
     * Sends a log message to the console with the specified message.
     *
     * @param message The message to log.
     */
    public void sendLog(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', display + message));
    }
}
