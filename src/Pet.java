import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;

abstract class Pet {
    private Species species;
    private String nickname;
    private int age;
    private int trickLevel;
    private LinkedHashSet<String> habits;
    public String getSpecies() {return  species.getTitle();}
    public void setSpecies(Species newSpecies) {this.species=newSpecies;}
    public int getAge()
    {
        return age;
    }
    public void setAge(int newAge)
    {
        age=newAge;
    }
    public int getTrickLevel()
    {
        return trickLevel;
    }
    public void setTrickLevel(int newHickLevel) {trickLevel =newHickLevel;}
    public LinkedHashSet<String> getHabits()
    {
        return habits;
    }
    public void setHabits(LinkedHashSet<String> newHabits)
    {
      habits = newHabits;
    }
    public String getNickname()
    {
        return nickname;
    }
    public void setNickname(String newName)
    {
        nickname=newName;
    }
    public void eat()
    {
        System.out.println("Я їм.");
    }
    public  abstract void respond();
    @Override
    public String toString()
    {
        return this.species + "{ nickname= " + this.nickname +
                ", age= " + this.age +
                ", tricklevel= " + this.trickLevel +
                ", habits= " + (this.getHabits() != null && this.getHabits().size() > 0
                ? String.join(", ", this.getHabits())
                : "none") +
                ", canFly= " + this.species.canFly() +
                ", numberOfLegs= " + this.species.getNumberOfLegs() +
                ", hasFur= " + this.species.hasFur() + " }";
    }
    public Pet(){
        setSpecies(Species.UNKNOWN);
    }
    public Pet(String nickname){
        setNickname(nickname);
    }
    public Pet(String nickname, int age, int trickLevel, LinkedHashSet<String> habits){
        setNickname(nickname);
        setAge(age);
        setTrickLevel(trickLevel);
        setHabits(habits);
    }
     @Override
     public boolean equals(Object obj) {
         if (this == obj) return true;
         if (obj == null || getClass() != obj.getClass()) return false;
         Pet pet = (Pet) obj;
         return age == pet.age &&
                 trickLevel == pet.trickLevel &&
                 Objects.equals(species, pet.species) &&
                 Objects.equals(nickname, pet.nickname) &&
                 Objects.equals(habits, pet.habits);
     }

     @Override
     public int hashCode() {
         int result = Objects.hash(species, nickname, age, trickLevel);
         result = 31 * result + Objects.hashCode(habits);
         return result;
     }
    @Override
    @SuppressWarnings("deprecation")
    protected void finalize() throws Throwable {
        try {
            System.out.printf("\nВидаляємо тварину %s", this.getNickname());
        } finally {
            super.finalize();
        }
    }

}
