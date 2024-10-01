import java.util.Arrays;
import java.util.Objects;

public class Human {
  private Pet pet;
  private String name;
  private String surname;
  private int year;
  private int iq;
  private String[][] schedule;
  private Human mother;
  private Human father;
  public String getName() {return this.name;}
    public String getSurname() {return this.surname;}
    public int getYear() {return this.year;}
    public int getIq() {return this.iq;}
    public Pet getPet(){return this.pet;}
    public Human getMother(){return this.mother;}
    public String[][] getSchedule() {return this.schedule;}
    public void setName(String name) { this.name = name; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setYear(int year) { this.year = year; }
    public void setIq(int iq) { this.iq = iq; }
    public void setSchedule(String[][] schedule){this.schedule = schedule;}
    public void setMother(Human mother) {this.mother = mother;}
    public Human getFather(){return this.father;}
    public void setFather(Human father) {this.father = father;}
    public void setPet(Pet pet) {this.pet = pet;}
    public  void greetPet(){
        System.out.println("Привіт "+this.getPet().getNickname());
    }
    public  void describePet(){
      final int MiddleLevelOfHick=50;
        System.out.print("У мене є "+this.getPet().getSpecies()+
                " йому "+this.getPet().getAge());
        if(this.getPet().getTrickLevel()>MiddleLevelOfHick)
            System.out.println(".Він дуже хитрий");
        else
            System.out.println(".Він майже не хитрий");
    }
    @Override
    public  String toString(){
      return "Human{name= "+this.getName()+",surname= "+this.getSurname()+",year= "+this.getYear()+",iq= "+this.getIq()+",mother="+this.getMother().getName()+",father= "+this.getFather().getName()+this.getPet().toString()+",schedule ="+ Arrays.deepToString(getSchedule())+"}";
    }
    public Human(String name,String surname,int year){
      setName(name);
      setSurname(surname);
      setYear(year);
    }
    public Human(String name,String surname,int year,Human mother,Human father){
      setName(name);
      setSurname(surname);
      setYear(year);
      setFather(father);
      setMother(mother);
    }
    public Human(String name,String surname,int year,int iq,Pet pet,Human mother,Human father, String[][] schedule){
      setName(name);
      setSurname(surname);
      setYear(year);
      setIq(iq);
      setPet(pet);
      setMother(mother);
      setFather(father);
      setSchedule(schedule);
    }
    public Human(){}
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Human human = (Human) obj;
        return year == human.year &&
                iq == human.iq &&
                Objects.equals(name, human.name) &&
                Objects.equals(surname, human.surname) &&
                Objects.equals(this.getPet(), human.getPet()) &&
                Objects.equals(this.getMother(), human.getMother()) &&
                Objects.equals(this.getFather(), human.getFather()) &&
                Arrays.deepEquals(schedule, human.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, year, iq, getPet(), getMother(), getFather()) + Arrays.deepHashCode(schedule);
    }
}
