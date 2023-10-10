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
import dev.demeng.sentinel.wrapper.exception.*;
import dev.demeng.sentinel.wrapper.exception.unchecked.UnexpectedResponseException;
import games.negative.sentinel.util.Logger;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

/**
 * Provides methods for authenticating with the Sentinel API.
 */
public class Sentinel {

    /**
     * Authenticates with the Sentinel API using the provided parameters.
     *
     * @param url           The API URL.
     * @param auth          The API key.
     * @param key           The license key (nullable).
     * @param product       The product identifier.
     * @param platform      The purchase platform.
     * @param platformValue The purchase platform user identity.
     * @return True if authentication is successful, false otherwise.
     */
    public boolean authenticate(String url, String auth, @Nullable String key, String product,
                                String platform, String platformValue) {

        // Create a SentinelClient instance with the provided API URL and key.
        final SentinelClient sentinel = new SentinelClient(url, auth);

        try {
            // Attempt license authentication using the provided parameters.
            sentinel.getLicenseController().auth(key, product, platform, platformValue,
                    SentinelClient.getCurrentHwid(), SentinelClient.getCurrentIp());

            // Authentication succeeded; log a success message.
            Logger.WARNING.sendLog("&aThis license is &a&lvalid&d.");
            return true;

        } catch (InvalidLicenseException e) {
            handleLicenseError("invalid");
        } catch (ExpiredLicenseException e) {
            handleLicenseError("expired");
        } catch (BlacklistedLicenseException e) {
            handleLicenseError("blacklisted");
        } catch (ExcessiveServersException e) {
            handleLicenseError("used by too many servers. (Max: " + e.getMaxServers() + ")");
        } catch (ExcessiveIpsException e) {
            handleLicenseError("used by too many IPs. (Max: " + e.getMaxIps() + ")");
        } catch (InvalidProductException e) {
            handleLicenseError("linked to a different product");
        } catch (InvalidPlatformException e) {
            handleLicenseError("not linked to your download id");
        } catch (ConnectionMismatchException e) {
            handleLicenseError("not linked to your connection");
        } catch (UnexpectedResponseException | IOException e) {
            handleLicenseError("dodgy. We don't know what happened, join https://discord.gg/uWQRGB662c!");
        }

        // Authentication failed.
        return false;
    }

    /**
     * Handles license-related errors and logs an appropriate message.
     *
     * @param errorMessage The error message describing the license issue.
     */
    private void handleLicenseError(String errorMessage) {
        Logger.WARNING.sendLog("&cThis license is " + errorMessage + ".");
    }
}
