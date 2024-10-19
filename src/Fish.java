import java.util.HashSet;
import java.util.LinkedHashSet;

public class Fish extends Pet{
    public Fish(){
        super();
        setSpecies(Species.UNKNOWN);
    }
    public Fish(boolean isSpeciesKnown,String name){
        super(name);
        setSpecies(isSpeciesKnown?Species.FISH:Species.UNKNOWN);
    }
    public Fish( boolean isSpeciesKnown,String nickname, int age, int trickLevel, LinkedHashSet<String> habits){
        super(nickname,age,trickLevel,habits);
        setSpecies(isSpeciesKnown?Species.FISH:Species.UNKNOWN);
    }
    public void eat(){
        super.eat();
    }
    @Override
    public void respond(){
        System.out.printf("Привіт,мене звати %s ,я - %s\n",getNickname(),getSpecies());
    }

}
