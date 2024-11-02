import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;
class FamiliesServiceTest {

    public  FamiliesService familiesService;
    private ByteArrayOutputStream outputStream;
    @BeforeEach
    public void init() {
        FamilyTest temp = new FamilyTest();
        familiesService= new FamiliesService(new CollectionFamilyDAO(temp.CreateFamilyList()));
         outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }
    @Test
    void displayAllFamilies() {
        init();
        familiesService.displayAllFamilies();
        String firstOutput = outputStream.toString();

        outputStream.reset();

        familiesService.displayAllFamilies(familiesService.collectionFamilyDAO.getAllFamilies());
        String secondOutput = outputStream.toString();

        assertEquals(firstOutput, secondOutput, "Виводи методів мають бути однаковими.");
    }
    @Test
    void getAllFamilies() {
        assertNotNull(familiesService.collectionFamilyDAO.getAllFamilies());
        assertFalse(familiesService.collectionFamilyDAO.getAllFamilies().isEmpty(), "Список сімей не повинен бути пустим.");
    }

    @Test
    void getFamiliesBiggerOrLessThan() {
        assertTrue(familiesService.getFamiliesBiggerOrLessThan(3, true)
                .stream().allMatch(family -> family.CountFamily() > 2));

        assertTrue(familiesService.getFamiliesBiggerOrLessThan( 3, false)
                .stream().allMatch(family -> family.CountFamily() < 3));
    }

    @Test
    void countFamiliesWithMemberNumber() {
        int count = familiesService.countFamiliesWithMemberNumber(3);
        assertEquals(1, count, "Кількість сімей з 3 членами має дорівнювати 1.");
    }

    @Test
    void createNewFamily() {
        Man father= null;
        Woman mother= null;
        try {
            father= new Man("Олег","Олькевич","14/07/1996",95);
            mother= new Woman("Ірина","Олькевич","28/03/1995",95);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        familiesService.createNewFamily(mother,father);
        Family newFamily = familiesService.getAllFamilies().getLast();
        assertTrue(familiesService.collectionFamilyDAO.getAllFamilies().contains(newFamily), "Нова сім'я має бути в списку сімей.");
    }

    @Test
    void deleteFamilyByIndex() {
        int initialSize = familiesService.getAllFamilies().size();
        familiesService.deleteFamilyByIndex(0);
        assertEquals(initialSize - 1, familiesService.collectionFamilyDAO.getAllFamilies().size(), "Кількість сімей має зменшитися на 1.");
    }

    @Test
    void bornChild() {
        familiesService.bornChild(familiesService.getFamilyById(0));
        assertEquals(4,familiesService.getFamilyById(0).CountFamily(), "Кількість членів сім'ї повинна збільшитися на 1.");
    }

    @Test
    void adoptChild() {
        Woman child= null;
        try {
            child = new Woman("Катя","Іванчишин","17/12/2019",98);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int initialSize = familiesService.getFamilyById(3).CountFamily();
        familiesService.adoptChild(familiesService.getFamilyById(3),child);
        assertEquals(5, familiesService.getFamilyById(3).CountFamily(), "Кількість членів сім'ї повинна збільшитися на 1 після усиновлення.(4->5)");
        assertNotEquals(initialSize,familiesService.getFamilyById(3).CountFamily());
    }

    @Test
    void deleteAllChildrenOlderThan() {
        int initialSize = familiesService.getFamilyById(0).getChildren().size();
        familiesService.deleteAllChildrenOlderThan( 4);
        assertTrue(familiesService.getFamilyById(0).getChildren().size() < initialSize, "Кількість дітей має зменшитися після видалення.");
    }

    @Test
    void count() {
        int count = familiesService.count();
        assertEquals(familiesService.collectionFamilyDAO.getAllFamilies().size(), count, "Кількість повинна дорівнювати кількості сімей.");
    }

    @Test
    void getFamilyById() {
        Family family = familiesService.getFamilyById(3);
        assertNotNull(family, "Сім'я з даним ідентифікатором повинна існувати.");
    }

    @Test
    void getPets() {
        Family family = familiesService.getFamilyById(0);
        assertNotNull(family.getPets(), "Список домашніх улюбленців не повинен бути пустим.");
    }

    @Test
    void addPet() {

        Pet pet = new Dog(true,"Джек");
        familiesService.getPets(0).add(pet);
        assertTrue(familiesService.getPets(0).contains(pet), "Список домашніх улюбленців має містити нового улюбленця.");
    }
}