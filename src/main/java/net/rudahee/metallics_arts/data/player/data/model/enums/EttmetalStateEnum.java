package net.rudahee.metallics_arts.data.player.data.model.enums;


/**
 * The EttmetalState enum represents one of three possible states that can be used to control a process or operation.
 *
 * @author SteelCode Team
 * @since 1.5.1
 * */
public enum EttmetalStateEnum {
    DELETE_ITEMS("delete"),   // indicates that items should be deleted.
    KEEP_ITEMS("keep"),     // indicates that items should be kept.
    NOTHING("none");         // indicates that nothing should be done.

    final String name;

    EttmetalStateEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static EttmetalStateEnum getEttmetaStateByName(String name) {
        if (name.equals("delete")) {
            return DELETE_ITEMS;
        } else if (name.equals("keep")) {
            return KEEP_ITEMS;
        } else {
            return NOTHING;
        }
    }
}
