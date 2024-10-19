import java.util.HashSet;
import java.util.LinkedHashSet;

public class Bird extends Pet implements Foulable {
    public Bird() {
        super();
        setSpecies(Species.UNKNOWN);
    }
    public Bird(boolean isSpeciesKnown,String name) {
        super(name);
        setSpecies(isSpeciesKnown?Species.BIRD:Species.UNKNOWN);
    }

    public Bird(boolean isSpeciesKnown,String nickname, int age, int trickLevel, LinkedHashSet<String> habits) {
        super(nickname, age, trickLevel, habits);
        setSpecies(isSpeciesKnown?Species.BIRD:Species.UNKNOWN);
    }


    @Override
    public void respond() {
        System.out.printf("Привіт, мене звати %s, я - %s. Чірік-чірік!\n", getNickname(),getSpecies());
    }

    @Override
    public void foul() {
        System.out.printf("%s  %s наробила безлад на підлозі.\n",getSpecies(),getNickname());
    }
}
