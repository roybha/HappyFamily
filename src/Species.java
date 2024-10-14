public enum Species {
    DOG("Dog", false, 4, true),
    FISH("Fish", false, 0, false),
    DOMESTIC_CAT("Domestic Cat", false, 4, true),
    ROBO_CAT("Robot Cat", true, 4, false),
    SNAKE("Snake", false, 0, false),
    TURTLE("Turtle", false, 4, false),
    BIRD("Bird", true, 2, false),
    UNKNOWN("Unknown", false, 0, false);

    private final String title;
    private final boolean canFly;
    private  final int numberOfLegs;
    private final boolean hasFur;

    // Оновлений конструктор
    Species(String title, boolean canFly, int numberOfLegs, boolean hasFur) {
        this.title = title;
        this.canFly = canFly;
        this.numberOfLegs = numberOfLegs;
        this.hasFur = hasFur;
    }

    // Геттери та сеттери для нових полів
    public String getTitle() {
        return title;
    }

    public boolean canFly() {
        return canFly;
    }

    public int getNumberOfLegs() {
        return numberOfLegs;
    }

    public boolean hasFur() {
        return hasFur;
    }
}
