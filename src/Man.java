final class Man extends Human{
    public Man(String name, String surname,int age){
        super(name,surname,age);
    }
    public Man(String name, String surname, int year, Woman mother, Man father){
        super(name,surname,year,mother,father);
    }
    public Man(String name, String surname, int year, int iq, Pet pet, Woman mother, Man father, String[][] schedule){
        super(name,surname,year,iq,pet,mother,father,schedule);
    }
    @Override
    public void greetPet(){
        switch(this.getFamily().getPet().getSpecies()){
            case "Dog": System.out.printf("%s: Здоров собако",this.getClass());break;
            case "Domestic Cat": System.out.printf("%s: Здоров котяро",this.getClass());break;
            case "Robot Cat": System.out.printf("%s: Здоров роботе",this.getClass());break;
            case "Fish": System.out.printf("%s: Здоров рибище",this.getClass());break;
            case "Snake": System.out.printf("%s: Здоров зміюче",this.getClass());break;
            case "Turtle": System.out.printf("%s: Здоров черепаха",this.getClass());break;
            case "Bird": System.out.printf("%s: Здоров пташко",this.getClass());break;
            case "Unknown": System.out.printf("%s Здоров, хто б ти не був!",this.getClass());break;
            default:break;
        }
        System.out.println("");
    }
    // Унікальний метод для чоловіків
    public void repairCar() {
        System.out.println("Ремонтую автомобіль.Перевірка двигуна...Замінюю мастило...Автомобіль відремонтовано!");
    }
}
