 class RoboCat extends Pet implements Foulable{
    public RoboCat() {
        super();
        setSpecies(Species.UNKNOWN);
    }
    public RoboCat(boolean isSpeciesKnown,String name) {
        super(name);
        setSpecies(isSpeciesKnown?Species.ROBO_CAT:Species.UNKNOWN);
    }
    public RoboCat(boolean isSpeciesKnown,String nickname, int age, int trickLevel, String[] habits) {
        super(nickname,age,trickLevel, habits);
        setSpecies(isSpeciesKnown?Species.ROBO_CAT:Species.UNKNOWN);
    }
    @Override
     public void respond(){
        System.out.printf("Мяу-біп-мяу,мене звати %s, я - %s\n",getNickname(),getSpecies());
    }
    @Override
     public void foul(){
        System.out.printf("%s %s Розсипав мастило,треба швидше забиратись...\n",getSpecies(),getNickname());
    }
}
