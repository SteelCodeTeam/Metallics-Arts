package net.rudahee.metallics_arts.modules.error_handling.messages;

import java.io.Serializable;

public enum ErrorTypes implements Serializable {


    INDETERMINATE_ERROR("9999", "Unknown error, please, contact us in https://github.com/SteelCodeTeam/Metallics-arts/issues", SeverityType.ERROR),

    PLAYER_ERROR("1000", "Player is null or not found.", SeverityType.WARN),
    PLAYER_CAPABILITY_ERROR("1001", "Capability not found for a Player or ServerPlayer.", SeverityType.ERROR),
    PLAYER_DEAD_ERROR("1002", "Player is dead error.", SeverityType.WARN);

    private final String code;
    private final String message;
    private final SeverityType severity;


    ErrorTypes(String code, String message, SeverityType severity) {
        this.code = code;
        this.message = message;
        this.severity = severity;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public SeverityType getSeverityType() {
        return this.severity;
    }
}
