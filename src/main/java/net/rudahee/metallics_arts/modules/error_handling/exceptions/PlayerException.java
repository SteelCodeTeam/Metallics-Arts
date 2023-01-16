package net.rudahee.metallics_arts.modules.error_handling.exceptions;

import net.rudahee.metallics_arts.modules.error_handling.messages.ErrorTypes;
import net.rudahee.metallics_arts.modules.error_handling.messages.SeverityType;
import net.rudahee.metallics_arts.modules.error_handling.utils.LoggerUtils;

public class PlayerException extends Exception {
    private final ErrorTypes errorType;

    public PlayerException(ErrorTypes errorType) {
        super();
        this.errorType = errorType;
    }

    public ErrorTypes getErrorType() {
        return errorType;
    }

    public String getMessage() {
        return  this.errorType.getMessage();
    }

    public String getCode() {
        return this.errorType.getCode();
    }

    public SeverityType getSeverityType() {
        return this.errorType.getSeverityType();
    }

    public void printResumeLog() {
        LoggerUtils.printLog(this.getCode(), this.getMessage(), null, this.getSeverityType());
    }

    public void printCompleteLog() {
        LoggerUtils.printLog(this.getCode(), this.getMessage(), this.getStackTrace(), this.getSeverityType());
    }
}
