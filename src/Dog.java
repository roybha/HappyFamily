import java.util.LinkedHashSet;

class Dog  extends Pet implements Foulable{
    public Dog(){
        super();
        setSpecies(Species.UNKNOWN);
    }
    public Dog(boolean isSpeciesKnown,String name) {
        super(name);
        setSpecies(isSpeciesKnown?Species.DOG:Species.UNKNOWN);
    }
    public Dog(boolean isSpeciesKnown,String nickname, int age, int trickLevel, LinkedHashSet<String> habits){
        super(nickname,age,trickLevel,habits);
        setSpecies(isSpeciesKnown?Species.DOG:Species.UNKNOWN);
    }
    @Override
    public void respond(){
        System.out.printf("Гав-гав,мене звати %s ,я - %s\n",getNickname(),getSpecies());
    }
    @Override
    public void foul(){
        System.out.printf("%s %s розірвав диван,подушки,погриз меблі...",getSpecies(),getNickname());
    }
}
