public enum Species {
    DOG("Dog", false, 4, true),
    CAT("Cat", false, 4, true),
    BIRD("Bird", true, 2, false),
    SNAKE("Snake", false, 0, false),
    TURTLE("Turtle", false, 4, false);

    private String title;
    private boolean canFly;
    private int numberOfLegs;
    private boolean hasFur;

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

    public void setTitle(String title) {
        this.title = title;
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
