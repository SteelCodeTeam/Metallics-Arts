package net.rudahee.metallics_arts.modules.error_handling.utils;

import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.modules.error_handling.messages.SeverityType;

import javax.annotation.Nullable;


public class LoggerUtils {

    public static void printLog(String code, String message, @Nullable StackTraceElement[] stackTrace, SeverityType severity) {
        if (severity == SeverityType.INFO) {
            printLogInfo(code, message, stackTrace);
        } else if (severity == SeverityType.DEBUG) {
            printLogDebug(code, message, stackTrace);
        } else if (severity == SeverityType.WARN) {
            printLogWarn(code, message, stackTrace);
        } else if (severity == SeverityType.ERROR) {
            printLogError(code, message, stackTrace);
        } else if (severity == SeverityType.FATAL) {
            printLogFatal(code, message, stackTrace);
        } else {
            printLogWarn(code, message, stackTrace);
        }
    }

    public static void printLogInfo(String code, String message, @Nullable StackTraceElement[] stackTrace) {
        String messageBuilt = buildMessage(code, message, stackTrace);
        MetallicsArts.LOGGER.info(messageBuilt);
    }

    public static void printLogInfo(String message) {
        MetallicsArts.LOGGER.info(message);
    }

    public static void printLogDebug(String code, String message, @Nullable StackTraceElement[] stackTrace) {
        String messageBuilt = buildMessage(code, message, stackTrace);
        MetallicsArts.LOGGER.debug(messageBuilt);
    }

    public static void printLogDebug(String message) {
        MetallicsArts.LOGGER.debug(message);
    }

    public static void printLogWarn(String code, String message, @Nullable StackTraceElement[] stackTrace) {
        String messageBuilt = buildMessage(code, message, stackTrace);
        MetallicsArts.LOGGER.warn(messageBuilt);
    }

    public static void printLogWarn(String message) {
        MetallicsArts.LOGGER.warn(message);
    }

    public static void printLogError(String code, String message, @Nullable StackTraceElement[] stackTrace) {
        String messageBuilt = buildMessage(code, message, stackTrace);
        MetallicsArts.LOGGER.error(messageBuilt);
    }

    public static void printLogError(String message) {
        MetallicsArts.LOGGER.error(message);
    }

    public static void printLogFatal(String code, String message, @Nullable StackTraceElement[] stackTrace) {
        String messageBuilt = buildMessage(code, message, stackTrace);
        MetallicsArts.LOGGER.fatal(messageBuilt);
    }

    public static void printLogFatal(String message) {
        MetallicsArts.LOGGER.fatal(message);
    }
    private static String buildMessage(String code, String message, @Nullable StackTraceElement[] stackTrace) {
        StringBuilder messageBuilder = new StringBuilder(code + ": " + message);

        if (stackTrace != null && stackTrace.length != 0) {
            messageBuilder.append(" Stacktrace: \n");
            for (StackTraceElement trace: stackTrace) {
                messageBuilder.append(trace.toString()).append("\n");
            }

        }

        return messageBuilder.toString();
    }
}
