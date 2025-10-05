package net.rudahee.metallics_arts.modules.logic.server.server_events;

/**
 * Handles events related to a player's death.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class OnLivingDeathEvent {

    /**
     * Resets a player's Allomantic and Feruchemical metal states upon death.
     * This method is triggered when a LivingDeathEvent occurs. It sets the player's
     * Allomantic burning and Feruchemical tapping/storing states for all metals to false.
     * It also sets the player's metal mind equipped state to false for all metal groups.
     * Finally, it syncs the player's capability data with the client.
     *
     * @param event The LivingDeathEvent that triggered this method.
     */
}
