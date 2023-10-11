package games.negative.sentinel.builder;

import games.negative.sentinel.Sentinel;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a builder for Sentinel.
 */
public class SentinelBuilder {

    private final JavaPlugin plugin;
    private String url;
    private String auth;
    private String key;
    private String product;
    private String platform;
    private String platformValue;

    /**
     * Constructs a new SentinelBuilder instance.
     * @param plugin The plugin instance.
     */
    public SentinelBuilder(@NotNull JavaPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Sets the API URL.
     * @param url The API URL.
     * @return The SentinelBuilder instance.
     */
    public SentinelBuilder url(@NotNull String url) {
        this.url = url;
        return this;
    }

    /**
     * Sets the API authentication key.
     * @param auth The API authentication key.
     * @return The SentinelBuilder instance.
     */
    public SentinelBuilder auth(@NotNull String auth) {
        this.auth = auth;
        return this;
    }

    /**
     * Sets the license key.
     * @param key The license key.
     * @return The SentinelBuilder instance.
     */
    public SentinelBuilder key(@NotNull String key) {
        this.key = key;
        return this;
    }

    /**
     * Sets the product name.
     * @param product The product name.
     * @return The SentinelBuilder instance.
     */
    public SentinelBuilder product(@NotNull String product) {
        this.product = product;
        return this;
    }

    /**
     * Sets the platform name.
     * @param platform The platform name.
     * @return The SentinelBuilder instance.
     */
    public SentinelBuilder platform(@NotNull String platform) {
        this.platform = platform;
        return this;
    }

    /**
     * Sets the platform value.
     * @param platformValue The platform value.
     * @return The SentinelBuilder instance.
     */
    public SentinelBuilder platformValue(@NotNull String platformValue) {
        this.platformValue = platformValue;
        return this;
    }

    /**
     * Builds the Sentinel instance.
     * @return The Sentinel instance.
     */
    public Sentinel build() {
        return new Sentinel(plugin, url, auth, key, product, platform, platformValue);
    }
}
