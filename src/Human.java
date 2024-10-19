import java.util.*;

public class Human {
  private String name;
  private String surname;
  private int year;
  private int iq;
  final private LinkedHashMap<String,String> schedule =  new LinkedHashMap<>();
  private Family family;
  public Family getFamily() {
    return family;
  }

  public void setFamily(Family family) {
    this.family = family;
  }

  public String getName() {
    return this.name;
  }

  public String getSurname() {
    return this.surname;
  }

  public int getYear() {
    return this.year;
  }

  public int getIq() {
    return this.iq;
  }

  public LinkedHashMap<String, String> getSchedule() {
    return this.schedule;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public void setIq(int iq) {
    this.iq = iq;
  }

  public void setSchedule(HashMap<String, String> schedule) {
    final int weekSize = 7;
    // Перевіряємо, чи нова таблиця має правильні розміри
    if (schedule.size() <= weekSize) {
      // Ітеруємо по рядках нової таблиці
      for (String i : schedule.keySet()) {
          if (schedule.get(i) != null && !schedule.get(i).isEmpty()) {
            this.schedule.put(i,schedule.get(i));
          }
      }
    }
  }

  public void greetPets() {
    if (this.family.getPets() != null && !this.family.getPets().isEmpty()) {
      for (Pet pet : this.family.getPets()) {
        greetPet(pet);
      }
    }
  }
  public void greetPet(Pet pet){
    System.out.println("Привіт " + pet.getNickname());
  }


  private  void describePets(){
    if (this.family.getPets() != null && !this.family.getPets().isEmpty()) {
      for (Pet pet : this.family.getPets()) {
        describePet(pet);
      }
    }
  }
  public void describePet(Pet pet) {
    final int MiddleLevelOfHick = 50;
    System.out.print("У мене є " + pet.getSpecies() +
            " йому " + pet.getAge());
    if (pet.getTrickLevel() > MiddleLevelOfHick)
      System.out.println(".Він дуже хитрий");
    else
      System.out.println(".Він майже не хитрий");
  }

  @Override
  public String toString() {

    List<String> entries = new ArrayList<>();
    getSchedule().forEach((k, v) -> entries.add("[" + k + ", " + (v.isEmpty() ? "null" : v) + "]"));
    String scheduleString = "[" + String.join(", ", entries) + "]";

    return this.getClass()+"{name= " + this.getName() + ",surname= " + this.getSurname() + ",year= " + this.getYear() + ",iq= " + this.getIq() + ",schedule =" + scheduleString + "}";
  }

  public Human(String name, String surname, int year) {
    setName(name);
    setSurname(surname);
    setYear(year);
    InitializeSchedule();
  }

  public Human(String name, String surname, int year, Human mother, Human father) {
    setName(name);
    setSurname(surname);
    setYear(year);
    InitializeSchedule();
    if(mother!=null&& mother.family!=null)
    {
      mother.getFamily().AddChild(this);
      this.family = mother.family;
      family.setFather(father);
      family.setMother(mother);
    }
  }

  public Human(String name, String surname, int year, int iq, LinkedHashSet<Pet> pets, Human mother, Human father, LinkedHashMap<String,String> schedule) {
    setName(name);
    setSurname(surname);
    setYear(year);
    setIq(iq);
    InitializeSchedule();
    setSchedule(schedule);
    if(father!=null&& father.family!=null) {
      father.getFamily().AddChild(this);
      this.family = mother.family;
      this.family.setMother(mother);
      this.family.setFather(father);
      this.family.setPets(pets);
    }

  }

  public Human() {
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true; // Перевірка, чи порівнюємо один і той самий об'єкт
    if (obj == null || getClass() != obj.getClass()) return false; // Перевірка на null та однаковість класів

    Human human = (Human) obj;
    if (this.hashCode() != human.hashCode()) {
      return false; // Якщо хеш-коди не збігаються, об'єкти точно не рівні
    }
    // Порівняння полів з урахуванням можливих null-значень
    return year == human.year &&
            iq == human.iq &&
            Objects.equals(name, human.name) &&
            Objects.equals(surname, human.surname) &&
            Objects.equals(schedule, human.schedule); // Порівняння масивів із використанням deepEquals
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, surname, year, iq) + schedule.hashCode();
  }

  @Override
  @SuppressWarnings("deprecation")
  protected void finalize() throws Throwable {
    try {
      System.out.printf("\nВидаляємо людину %s", this.getName());
    } finally {
      super.finalize(); // Завжди викликайте finalize() батьківського класу
    }
  }
  private  void InitializeSchedule() {
    schedule.put("Понеділок", "");
    schedule.put("Вівторок", "");
    schedule.put("Середа", "");
    schedule.put("Четвер", "");
    schedule.put("П'ятниця", "");
    schedule.put("Субота", "");
    schedule.put("Неділя", "");
  }
}
