import java.util.Arrays;
import java.util.Objects;

public class Human {
  private String name;
  private String surname;
  private int year;
  private int iq;
  private String[][] schedule = {
          {"Monday", null},
          {"Tuesday", null},
          {"Wednesday", null},
          {"Thursday", null},
          {"Friday", null},
          {"Saturday", null},
          {"Sunday", null}
  };
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

  public String[][] getSchedule() {
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

  public void setSchedule(String[][] schedule) {
    // Перевіряємо, чи нова таблиця має правильні розміри
    if (schedule.length <= this.schedule.length && schedule[0].length == this.schedule[0].length) {
      // Ітеруємо по рядках нової таблиці
      for (int i = 0; i < schedule.length; i++) {
        // Ітеруємо по стовпцях, починаючи з 1 (0 - це день тижня)
        for (int j = 1; j < schedule[i].length; j++) {
          // Якщо значення нової таблиці не є порожнім (null або порожній рядок), оновлюємо існуючу таблицю
          if (schedule[i][j] != null && !schedule[i][j].isEmpty()) {
            this.schedule[i][j] = schedule[i][j];
          }
        }
      }
    }
  }

  public void greetPet() {
    System.out.println("Привіт " + this.family.getPet().getNickname());
  }

  public void describePet() {
    final int MiddleLevelOfHick = 50;
    System.out.print("У мене є " + this.family.getPet().getSpecies() +
            " йому " + this.family.getPet().getAge());
    if (this.family.getPet().getTrickLevel() > MiddleLevelOfHick)
      System.out.println(".Він дуже хитрий");
    else
      System.out.println(".Він майже не хитрий");
  }

  @Override
  public String toString() {
    return "Human{name= " + this.getName() + ",surname= " + this.getSurname() + ",year= " + this.getYear() + ",iq= " + this.getIq() + ",schedule =" + Arrays.deepToString(getSchedule()) + "}";
  }

  public Human(String name, String surname, int year) {
    setName(name);
    setSurname(surname);
    setYear(year);
  }

  public Human(String name, String surname, int year, Human mother, Human father) {
    setName(name);
    setSurname(surname);
    setYear(year);
    mother.getFamily().AddChild(this);
    this.family = mother.family;
    family.setFather(father);
    family.setMother(mother);
    family.AddChild(this);
  }

  public Human(String name, String surname, int year, int iq, Pet pet, Human mother, Human father, String[][] schedule) {
    setName(name);
    setSurname(surname);
    setYear(year);
    setIq(iq);
    father.getFamily().AddChild(this);
    this.family = mother.family;
    this.family.setPet(pet);
    this.family.setMother(mother);
    this.family.setFather(father);
    this.schedule = schedule;
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
            Arrays.deepEquals(schedule, human.schedule); // Порівняння масивів із використанням deepEquals
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, surname, year, iq) + Arrays.deepHashCode(schedule);
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
}
