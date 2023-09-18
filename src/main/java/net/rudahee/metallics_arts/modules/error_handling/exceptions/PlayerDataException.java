package net.rudahee.metallics_arts.modules.error_handling.exceptions;

import net.minecraftforge.common.util.NonNullSupplier;
import net.rudahee.metallics_arts.modules.error_handling.messages.ErrorTypes;
import net.rudahee.metallics_arts.modules.error_handling.messages.SeverityType;
import net.rudahee.metallics_arts.modules.error_handling.utils.LoggerUtils;
import org.jetbrains.annotations.NotNull;

public class PlayerDataException extends Exception implements NonNullSupplier<PlayerDataException> {
    private final ErrorTypes errorType;

    /**
     * Constructor that takes an ErrorTypes parameter and initializes the errorType field.
     *
     * @param errorType the error type of the exception
     */
    public PlayerDataException(ErrorTypes errorType) {
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

    @NotNull
    @Override
    public PlayerDataException get() {
        return this;
    }
}
