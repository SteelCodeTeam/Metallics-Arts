package net.rudahee.metallics_arts.data.enums.implementations;

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

    LinesColorEnum(float r, float g, float b, float a, float size) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        this.size = size;
    }

    public float getR() {
        return r;
    }

    public float getG() {
        return g;
    }

    public float getB() {
        return b;
    }

    public float getA() {
        return a;
    }

    public float getSize() {
        return size;
    }
}
