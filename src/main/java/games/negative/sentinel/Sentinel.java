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

package games.negative.sentinel;

import dev.demeng.sentinel.wrapper.SentinelClient;
import dev.demeng.sentinel.wrapper.controller.LicenseController;
import dev.demeng.sentinel.wrapper.exception.*;
import dev.demeng.sentinel.wrapper.exception.unchecked.UnexpectedResponseException;
import games.negative.sentinel.model.AuthenticationResult;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Provides methods for authenticating with the Sentinel API.
 */
public class Sentinel {

    private final AuthenticationResult result;
    private final Logger logger;

    /**
     * Constructs a new Sentinel instance.
     * @param plugin The plugin instance.
     * @param url The API URL.
     * @param auth The API authentication key.
     * @param key The license key.
     * @param product The product name.
     * @param platform The platform name.
     * @param platformValue The platform value.
     */
    public Sentinel(@NotNull JavaPlugin plugin, @NotNull String url, @NotNull String auth, @Nullable String key, @NotNull String product, @NotNull String platform, @NotNull String platformValue) {
        this.logger = plugin.getLogger();

        AuthenticationResult output;

        // Create a SentinelClient instance with the provided API URL and key.
        final SentinelClient sentinel = new SentinelClient(url, auth);

        try {
            // Attempt license authentication using the provided parameters.
            LicenseController controller = sentinel.getLicenseController();
            controller.auth(key, product, platform, platformValue,
                    SentinelClient.getCurrentHwid(), SentinelClient.getCurrentIp());

            // Authentication successful!
            output = AuthenticationResult.SUCCESS;
        } catch (InvalidLicenseException exception) {
            // The license is invalid.
            output = AuthenticationResult.INVALID;
        } catch (ExpiredLicenseException exception) {
            // The license has expired.
            output = AuthenticationResult.EXPIRED;
        } catch (BlacklistedLicenseException exception) {
            // The license has been blacklisted.
            output = AuthenticationResult.BLACKLISTED;
        } catch (ExcessiveServersException exception) {
            // The license has been used by too many servers.
            output = AuthenticationResult.EXCESSIVE_SERVERS;
        } catch (ExcessiveIpsException exception) {
            // The license has been used by too many IPs.
            output = AuthenticationResult.EXCESSIVE_IPS;
        } catch (InvalidProductException exception) {
            // The license is linked to a different product.
            output = AuthenticationResult.INVALID_PRODUCT;
        } catch (InvalidPlatformException exception) {
            // The download ID is not the same as the current download ID
            output = AuthenticationResult.UNKNOWN_DOWNLOAD_ID;
        } catch (ConnectionMismatchException exception) {
            // The connection of the license is not the same as the current connection.
            output = AuthenticationResult.UNKNOWN_CONNECTION;
        } catch (UnexpectedResponseException | IOException exception) {
            // An unexpected error occurred.
            output = AuthenticationResult.UNEXPECTED;
        }

        this.result = output;
    }

    /**
     * Authenticates with the Sentinel API.
     * @return Whether the authentication succeeded.
     */
    public boolean authenticate() {
        handleLicenseResult(result);
        return result == AuthenticationResult.SUCCESS;
    }

    /**
     * Returns the result of the license authentication.
     * @return The result of the license authentication.
     */
    @NotNull
    public AuthenticationResult result() {
        return result;
    }

    /**
     * Handles the result of the license authentication.
     * @param result The result of the license authentication.
     */
    private void handleLicenseResult(@NotNull AuthenticationResult result) {
        logger.severe(result.message());
    }
}
