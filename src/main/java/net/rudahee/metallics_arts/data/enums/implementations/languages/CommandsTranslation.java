package net.rudahee.metallics_arts.data.enums.implementations.languages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommandsTranslation {

    ONE_POWER_ADD_ALLOMANTIC("commands.metallics_arts.allomantic_one_power_add", "Succesfully added %s allomantic power to ", "El %s alomántico se añadido con éxito a "),
    ONE_POWER_ADD_FERUCHEMIC("commands.metallics_arts.feruchemic_one_power_add", "Succesfully added %s feruchemic power to ", "El %s feruquimico se añadido con éxito a "),
    ALL_POWER_ADD_ALLOMANTIC("commands.metallics_arts.allomantic_all_power_add", "Succesfully added all allomantic powers to ", "Todos los poderes alománticos se han añadido con éxito a "),
    ALL_POWER_ADD_FERUCHEMIC("commands.metallics_arts.feruchemic_all_power_add", "Succesfully added all feruchemic powers to ", "Todos los poderes feruquimicos se han añadido con éxito a "),
    ALL_POWER_ADD("commands.metallics_arts.all_power_add", "Succesfully added all powers to ", "Todos los poderes se han añadido con éxito a "),

    ALL_POWER_FILL("commands.metallics_arts.all_power_fill", "Succesfully filled all powers to ", "Todos los poderes se han recargado con éxito a "),
    ONE_POWER_FILL("commands.metallics_arts.one_power_fill", "Succesfully filled %s power to ", "El %s se ha recargado con éxito a "),

    ONE_POWER_REMOVE_ALLOMANTIC("commands.metallics_arts.allomantic_one_power_remove", "Succesfully removed %s allomantic power to ", "El %s alomántico se eliminado con éxito a "),
    ONE_POWER_REMOVE_FERUCHEMIC("commands.metallics_arts.feruchemic_one_power_remove", "Succesfully removed %s feruchemic power to ", "El %s feruquimico se eliminado con éxito a "),
    ALL_POWER_REMOVE_ALLOMANTIC("commands.metallics_arts.allomantic_all_power_remove", "Succesfully removed all allomantic powers to ", "Todos los poderes alománticos se han eliminado con éxito a "),
    ALL_POWER_REMOVE_FERUCHEMIC("commands.metallics_arts.feruchemic_all_power_remove", "Succesfully removed all feruchemic powers to ", "Todos los poderes feruquimicos se han eliminado con éxito a "),
    ALL_POWER_REMOVE("commands.metallics_arts.all_power_remove", "Succesfully removed all powers to ", "Todos los poderes se han eliminado con éxito a "),

    ONE_POWER_GET("commands.metallics_arts.one_power_get", " -> ", " -> "),
    ALL_POWER_GET("commands.metallics_arts.all_power_get", "Succesfully removed all powers to ", "Todos los poderes se han eliminado con éxito a "),

    ALLOMANTIC("commands.metallics_arts.allomantic", "Allomantic Powers", "Poderes Alomanticos"),
    FERUCHEMIC("commands.metallics_arts.feruchemic", "Feruchemic Powers", "Poderes Feruquimico");

    private final String key;
    private final String english;
    private final String spanish;
}
