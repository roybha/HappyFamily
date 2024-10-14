public class Snake extends Pet implements Foulable {
    public Snake() {
        super();
        setSpecies(Species.UNKNOWN);
    }
    public Snake(boolean isSpeciesKnown,String name) {
        super(name);
        setSpecies(isSpeciesKnown?Species.SNAKE:Species.UNKNOWN);
    }

    public Snake(boolean isSpeciesKnown,String nickname, int age, int trickLevel, String[] habits) {
        super(nickname, age, trickLevel, habits);
        setSpecies(isSpeciesKnown?Species.SNAKE:Species.UNKNOWN);
    }

    @Override
    public void respond() {
        System.out.printf("Привіт, мене звати %s, я - %s. Ш-ш-ш!\n", getNickname(),getSpecies());
    }

    @Override
    public void foul() {
        System.out.printf("%s %s пролізла у шафу і все переплутала!\n",getSpecies(),getNickname());
    }
}
