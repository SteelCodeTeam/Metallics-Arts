package net.rudahee.metallics_arts.data.enums.interfaces;


/**
 * An interface representing a metal with methods to get metal names in different cases.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public interface IMetal {

     /**
      * Returns the lowercase metal name.
      *
      * @return the metal name in lowercase
      */
     String getMetalNameLower();

     /**
      * Returns the uppercase metal name.
      *
      * @return the metal name in uppercase
      */
     String getMetalNameUpper();
}
