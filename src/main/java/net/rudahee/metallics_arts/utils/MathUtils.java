package net.rudahee.metallics_arts.utils;


/**
 * Utility class to avoid rewriting the most common mathematical operations.
 * So it's a class full of useful little methods, in charge of returning something after receiving a series of numbers.
 * <p>
 * By default, the methods do not have to be related to each other, but if they are, they are usually grouped together.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public class MathUtils {

    /**
     * Basic method to know if a number divided by 3 gives a remainder of 0.
     *
     * @param number to divide by 3.
     *
     * @return boolean if it divisible.
     */
    public static boolean isDivisibleBy3(int number) {
        return (number % 3) == 0;
    }

    /**
     * Basic method to know if a number divided by 10 gives a remainder of 0.
     *
     * @param number to divide by 10.
     *
     * @return boolean if it divisible.
     */
    public static boolean isDivisibleBy10(int number) { return (number % 10) == 0; }

    public static boolean isDivisibleBy2(int number) { return (number % 2) == 0; }
}
