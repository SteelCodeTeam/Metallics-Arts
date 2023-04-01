package net.rudahee.metallics_arts.modules.error_handling.exceptions;

import net.rudahee.metallics_arts.modules.error_handling.messages.ErrorTypes;
import net.rudahee.metallics_arts.modules.error_handling.messages.SeverityType;
import net.rudahee.metallics_arts.modules.error_handling.utils.LoggerUtils;

/**
 * PlayerException is a custom exception class that extends Exception.
 * It provides additional information about the error, such as a custom error code,
 * message, severity type, and methods to log the error.
 * <p>
 * PlayerException contains an ErrorTypes enum field, which holds the error information.
 * It also provides methods for retrieving the error type, message, code, and severity type.
 * The class includes two methods for printing logs: printResumeLog() and printCompleteLog().
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class PlayerException extends Exception {
    private final ErrorTypes errorType;

    /**
     * Constructor that takes an ErrorTypes parameter and initializes the errorType field.
     *
     * @param errorType the error type of the exception
     */
    public PlayerException(ErrorTypes errorType) {
        super();
        this.errorType = errorType;
    }

    /**
     * Getter method for errorType.
     *
     * @return the error type of the exception
     */
    public ErrorTypes getErrorType() {
        return errorType;
    }

    /**
     * Getter method for the error message.
     *
     * @return the message associated with the errorType
     */
    public String getMessage() {
        return  this.errorType.getMessage();
    }

    /**
     * Getter method for the error code.
     *
     * @return the code associated with the errorType
     */
    public String getCode() {
        return this.errorType.getCode();
    }

    /**
     * Getter method for the severity type.
     *
     * @return the severity type associated with the errorType
     */
    public SeverityType getSeverityType() {
        return this.errorType.getSeverityType();
    }

    /**
     * Prints a summarized log of the exception, including the error code, message, and severity type.
     */
    public void printResumeLog() {
        LoggerUtils.printLog(this.getCode(), this.getMessage(), null, this.getSeverityType());
    }

    /**
     * Prints a complete log of the exception, including the error code, message, stack trace, and severity type.
     */
    public void printCompleteLog() {
        LoggerUtils.printLog(this.getCode(), this.getMessage(), this.getStackTrace(), this.getSeverityType());
    }
}
