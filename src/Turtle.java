public class Turtle extends Pet implements Foulable {
    public Turtle() {
        super();
        setSpecies(Species.UNKNOWN);
    }
    public Turtle(boolean isSpeciesKnown,String name) {
        super(name);
        setSpecies(isSpeciesKnown?Species.TURTLE:Species.UNKNOWN);
    }

    public Turtle(boolean isSpeciesKnown,String nickname, int age, int trickLevel, String[] habits) {
        super(nickname, age, trickLevel, habits);
        setSpecies(isSpeciesKnown?Species.TURTLE:Species.UNKNOWN);
    }

    @Override
    public void respond() {
        System.out.printf("Привіт, мене звати %s, я - %s. Я рухаюся повільно, але впевнено.\n", getNickname(),getSpecies());
    }

    @Override
    public void foul() {
        System.out.printf("%s %s розкидала свою їжу по всій клітці.\n",getSpecies(),getNickname());
    }
}
