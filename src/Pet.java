import java.util.Arrays;
import java.util.Objects;

class Pet {
    private String species;
    private String nickname;
    private int age;
    private int trickLevel;
    private String[] habits;
    public String getSpecies()
    {
        return species;
    }
    public void setSpecies(String newSpecies)
    {
        species=newSpecies;
    }
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
    public void setTrickLevel(int newHickLevel)
    {
        trickLevel =newHickLevel;
    }
    public String[] getHabits()
    {
        return habits;
    }
    public void setHabits(String[] newHabits)
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
    public void respond()
    {
        System.out.println("Привіт хазяїне.Мене звати  "+this.nickname+"Мені "+this.age+".Я скучив!");
    }
    public void foul()
    {
        System.out.println("Потрібно добре замести сліди,бо буде халепа");
    }
    @Override
    public String toString()
    {
        return this.species+"{ nickname= "+this.nickname+",age= "+this.age+",tricklevel= "
                +this.trickLevel +",habits= "+String.join(",", this.getHabits())+" }";
    }
    public Pet(String species, String nickname){
        setSpecies(species);
        setNickname(nickname);
    }
    public Pet(String species, String nickname, int age, int trickLevel, String[] habits){
        setSpecies(species);
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
                 Arrays.equals(habits, pet.habits);
     }

     @Override
     public int hashCode() {
         int result = Objects.hash(species, nickname, age, trickLevel);
         result = 31 * result + Arrays.hashCode(habits);
         return result;
     }

}
