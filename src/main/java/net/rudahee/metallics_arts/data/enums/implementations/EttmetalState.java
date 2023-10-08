package net.rudahee.metallics_arts.data.enums.implementations;


/**
 * The EttmetalState enum represents one of three possible states that can be used to control a process or operation.
 *
 * @author SteelCode Team
 * @since 1.5.1
 * */
public enum EttmetalState {
    DELETE_ITEMS("delete"),   // indicates that items should be deleted.
    KEEP_ITEMS("keep"),     // indicates that items should be kept.
    NOTHING("none");         // indicates that nothing should be done.

    final String name;

    EttmetalState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
