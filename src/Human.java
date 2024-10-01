import java.util.Arrays;
import java.util.Objects;

public class Human {
  private String name;
  private String surname;
  private int year;
  private int iq;
  private String[][] schedule;
  private Family family;
  public Family getFamily() {
    return family;
  }
  public void setFamily(Family family) {
    this.family = family;
  }
  public String getName() {return this.name;}
    public String getSurname() {return this.surname;}
    public int getYear() {return this.year;}
    public int getIq() {return this.iq;}
    public String[][] getSchedule() {return this.schedule;}
    public void setName(String name) { this.name = name; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setYear(int year) { this.year = year; }
    public void setIq(int iq) { this.iq = iq; }
    public void setSchedule(String[][] schedule){this.schedule = schedule;}
    public  void greetPet(){
        System.out.println("Привіт "+this.family.getPet().getNickname());
    }
    public  void describePet(){
      final int MiddleLevelOfHick=50;
        System.out.print("У мене є "+this.family.getPet().getSpecies()+
                " йому "+this.family.getPet().getAge());
        if(this.family.getPet().getTrickLevel()>MiddleLevelOfHick)
            System.out.println(".Він дуже хитрий");
        else
            System.out.println(".Він майже не хитрий");
    }
    @Override
    public  String toString(){
      return "Human{name= "+this.getName()+",surname= "+this.getSurname()+",year= "+this.getYear()+",iq= "+this.getIq()+",schedule ="+ Arrays.deepToString(getSchedule())+"}";
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
      mother.getFamily().AddChild(this);
      this.family=mother.family;
      family.setFather(father);
      family.setMother(mother);
      family.AddChild(this);
    }
    public Human(String name,String surname,int year,int iq,Pet pet,Human mother,Human father, String[][] schedule){
      setName(name);
      setSurname(surname);
      setYear(year);
      setIq(iq);
      father.getFamily().AddChild(this);
      this.family=mother.family;
      this.family.setPet(pet);
      this.family.setMother(mother);
      this.family.setFather(father);
      this.schedule = schedule;
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
                Objects.equals(this.family.getPet(), human.family.getPet()) &&
                Objects.equals(this.family.getMother(), human.family.getMother()) &&
                Objects.equals(this.family.getFather(), human.family.getFather()) &&
                Arrays.deepEquals(schedule, human.schedule)&&
                Objects.equals(family, human.family);
    }

    @Override
    public int hashCode() {
      return Objects.hash(name, surname, year, iq) + Arrays.deepHashCode(schedule);
    }
}
