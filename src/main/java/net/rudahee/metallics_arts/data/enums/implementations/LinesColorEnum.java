package net.rudahee.metallics_arts.data.enums.implementations;


/**
 *  The LinesColorEnum class represents the different colors available for lines used in a graphical interface.
 *
 * @author SteelCode Team
 * @since 1.5.1
 */
public enum LinesColorEnum {

    IRON(0.5f, 0.5f, 0.5f, 0.5f, 3.5f),
    STEEL(0.7f, 0.7f, 0.7f, 0.5f, 3.5f),
    GOLD(1.0f, 1.0f, 0f, 0.5f, 3.5f),
    ELECTRUM(0.9f,0.9f,0.2f, 0.5f,3.5f),
    MALATIUM(1, 0.5f, 0.4f, 0.5f, 3.5f);

    private final float r;
    private final float g;
    private final float b;
    private final float a;
    private final float size;

    /**
     * Constructs a new LinesColorEnum object.
     *
     * @param r the red component of the color.
     * @param g the green component of the color.
     * @param b the blue component of the color.
     * @param a the alpha component of the color.
     * @param size the size of the lines.
     */
    LinesColorEnum(float r, float g, float b, float a, float size) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        this.size = size;
    }
    /**
     * Returns the red component of the color.
     *
     * @return the red component of the color
     */
    public float getR() {
        return r;
    }
    /**
     * Returns the green component of the color.
     *
     * @return the green component of the color
     */
    public float getG() {
        return g;
    }
    /**
     * Returns the blue component of the color.
     *
     * @return the blue component of the color
     */
    public float getB() {
        return b;
    }
    /**
     * Returns the alpha component of the color.
     *
     * @return the alpha component of the color
     */
    public float getA() {
        return a;
    }
    /**
     * Returns the size of the lines.
     *
     * @return the size of the lines
     */
    public float getSize() {
        return size;
    }
}
