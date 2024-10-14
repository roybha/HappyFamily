import java.util.Random;
import java.time.LocalDate;

final class Woman extends Human implements HumanCreator{
    public Woman(String name,String surname,int year){
        super(name,surname,year);
    }
    public Woman (String name,String surname,int year,Woman mother,Man father){
        super(name,surname,year,mother,father);
    }
    public Woman(String name, String surname, int year, int iq, Pet pet, Woman mother, Man father, String[][] schedule){
        super(name,surname,year,iq,pet,mother,father,schedule);
    }
    @Override
    public void greetPet(){
        switch(this.getFamily().getPet().getSpecies()){
            case "Dog": System.out.printf("%s: Привіт цуцику",this.getClass());break;
            case "Domestic Cat": System.out.printf("%s: Привіт котику",this.getClass());break;
            case "Robo Cat": System.out.printf("%s: Привіт роботику",this.getClass());break;
            case "Fish": System.out.printf("%s: Привіт рибонько",this.getClass());break;
            case "Snake": System.out.printf("%s: Привіт змієнько",this.getClass());break;
            case "Turtle": System.out.printf("%s: Привіт черепашко",this.getClass());break;
            case "Bird": System.out.printf("%s: Привіт пташечка",this.getClass());break;
            case "Unknown": System.out.printf("%s Привіт, таємниче створіннячко",this.getClass());break;
            default:break;
        }
        System.out.println("");
    }
    public void makeup() {
        System.out.println("Швиденько підфарбовуюсь.Нанесення основи...Нанесення румянцю...Макіяж нанесено!");
    }

    @Override
    public Human bornChild() {
        String[] manNames = {"Антон","Олег","Богдан","Ігор","Андрій","Дмитро","Василь"};
        String[] womanNames={"Юля","Олена","Аня","Лера","Марічка","Настя","Іра"};
        Random rand = new Random();
        boolean childSex=rand.nextBoolean();
        Human child=(childSex) ? new Woman(womanNames[rand.nextInt(womanNames.length)],
                this.getFamily().getFather().getSurname(),LocalDate.now().getYear(),this,(Man)this.getFamily().getFather()) :
                new Man(manNames[rand.nextInt(manNames.length)],
                this.getFamily().getFather().getSurname(),LocalDate.now().getYear(),this,(Man)this.getFamily().getFather());
        child.setIq((this.getIq()+this.getFamily().getFather().getIq())/2);
        return child;
    }
}
