public class DomesticCat  extends Pet implements Foulable{
    public DomesticCat(){
        super();
        setSpecies(Species.UNKNOWN);
    }
    public DomesticCat(boolean isSpeciesKnown,String name) {
        super(name);
        setSpecies(isSpeciesKnown?Species.DOMESTIC_CAT:Species.UNKNOWN);
    }
    public DomesticCat(boolean isSpeciesKnown,String nickname, int age, int trickLevel, String[] habits){
        super(nickname, age, trickLevel, habits);
        setSpecies(isSpeciesKnown?Species.DOMESTIC_CAT:Species.UNKNOWN);
    }
    @Override
    public void respond(){
        System.out.printf("Мяу-мяу,мене звати %s ,я - %s \n",getNickname(),getSpecies());
    }
    @Override
    public void foul(){
        System.out.printf("%s %s наробив гидоти\n",getSpecies(),getNickname());
    }
}
