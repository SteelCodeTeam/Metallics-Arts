package net.rudahee.metallics_arts.modules.error_handling.messages;

import java.io.Serializable;

/**
 * SeverityType is an enumeration that represents the different severity levels of errors in the application.
 * It implements Serializable, which allows it to be converted into a byte stream for storage and transmission purposes.
 *<p>
 * The severity levels are:
 * - INFO: Informational messages that highlight the progress of the application at a coarse-grained level.
 * - DEBUG: Fine-grained informational events that are most useful for debugging an application.
 * - WARN: Potentially harmful situations that might not cause any immediate issues.
 * - ERROR: Error events that might still allow the application to continue running.
 * - FATAL: Severe error events that will most likely lead to the application's termination.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public enum SeverityType implements Serializable {
    INFO, DEBUG, WARN, ERROR, FATAL;
}
