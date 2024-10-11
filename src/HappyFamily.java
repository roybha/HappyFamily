import java.util.Arrays;

public class HappyFamily {
    public static void main(String[] args)  {

        for(int i=0;i<30000;i++){
            Human newHuman1= new Human("Name"+i,"Surname"+i,i+1);
            Human newHuman2= new Human("Name"+i,"Surname"+i,i+2);
            Pet newPet1 = new Pet(Species.SNAKE,"Animal"+i);
            Pet newPet2 = new Pet(Species.TURTLE,"Animal"+(i+1));
            Family newFamily = new Family(newHuman1, newHuman2);
            newFamily.setPet(newPet1);
            newFamily.setPet(newPet2);
            newFamily.AddChild(newHuman1);
        }
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        Human newHuman3 = new Human("TestHuman","TestSurname",10);
        for(int i=0;i<7;i++){
            newHuman3.getSchedule()[i][0]=DayOfWeek.values()[i].getTitle()+" changed day";
            newHuman3.getSchedule()[i][1]="sth important "+(i+1);
        }
        System.out.println("\n"+newHuman3);
    }
}
