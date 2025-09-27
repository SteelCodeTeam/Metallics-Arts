package net.rudahee.metallics_arts.data.enums.implementations.languages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RedstoneItemTranslation {

    ALLOMANTIC_LEVER("block.metallics_arts.allomantic_lever", "Allomantic Lever", "Palanca alomantica","Palanca alomántica"),
    ALLOMANTIC_BUTTON_PUSH("block.metallics_arts.allomantic_push_button", "Allomantic Button (Push)", "Boton alomantico (Empujar)","Botón alomántico (empuhâh)"),
    ALLOMANTIC_BUTTON_PULL("block.metallics_arts.allomantic_pull_button", "Allomantic Button (Pull)", "Boton alomantico (Tirar)","Botón alomántico (tirâh)");

    private final String key;
    private final String english;
    private final String spanish;
    private final String andaluz;
}
