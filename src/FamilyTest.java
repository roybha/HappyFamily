import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FamilyTest {
    Family CreateFamily(){
        Human mother = new Human("Anna", "Ivanova", LocalDate.of(1989, Month.MARCH, 1)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli());
        Human father = new Human("Ivan", "Ivanov",LocalDate.of(1989, Month.APRIL, 1)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli());
        Family family = new Family(mother, father);
        family.AddChild(new Human("Alex","Ivanov",LocalDate.of(2018, Month.AUGUST, 23)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli()));
        LinkedHashSet<Pet> pets = new LinkedHashSet<>(List.of(new Dog( true,"Rex"),new Dog( false,"Luna"),new Dog( true,"T-Rex")));
        family.setPets(pets);
        return family;
    }
    @Test
    void testToString() {

        Family family = CreateFamily();
        String expected = "class Family{ mother=class Human{name= Anna,surname= Ivanova,birthDate= 01/03/1989,iq= 0,schedule =[[Понеділок, null], [Вівторок, null], [Середа, null], [Четвер, null], [П'ятниця, null], [Субота, null], [Неділя, null]]},father=class Human{name= Ivan,surname= Ivanov,birthDate= 01/04/1989,iq= 0,schedule =[[Понеділок, null], [Вівторок, null], [Середа, null], [Четвер, null], [П'ятниця, null], [Субота, null], [Неділя, null]]}, children=class Human{name= Alex,surname= Ivanov,birthDate= 23/08/2018,iq= 0,schedule =[[Понеділок, null], [Вівторок, null], [Середа, null], [Четвер, null], [П'ятниця, null], [Субота, null], [Неділя, null]]}, pets=[DOG{ nickname= Rex, age= 0, tricklevel= 0, habits= none, canFly= false, numberOfLegs= 4, hasFur= true }, UNKNOWN{ nickname= Luna, age= 0, tricklevel= 0, habits= none, canFly= false, numberOfLegs= 0, hasFur= false }, DOG{ nickname= T-Rex, age= 0, tricklevel= 0, habits= none, canFly= false, numberOfLegs= 4, hasFur= true }]}";
        assertEquals(expected, family.toString());
        Human anotherHuman = new Human("Jane", "Smith", LocalDate.of(1999, Month.JUNE, 1)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli());
        family.setFather(anotherHuman);
        assertNotEquals(expected, anotherHuman.toString());
    }
    @Test
    void testDeleteChildSuccess() {
        Family family = CreateFamily();
        Human child1 = new Human("Bohdan", "Ivanov",LocalDate.of(2016, Month.AUGUST, 23)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli());
        family.AddChild(child1);

        assertTrue(family.DeleteChild(child1));
        assertEquals(1, family.getChildren().size());
    }

    @Test
    void testDeleteChildNotFound() {
        Family family = CreateFamily();
        Human child1 = new Human("Bohdan", "Ivanov",LocalDate.of(2018, Month.AUGUST, 23)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli());
        Human child2 = new Human("Katya", "Ivanova",LocalDate.of(2018, Month.AUGUST, 23)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli());
        family.AddChild(child1);

        assertFalse(family.DeleteChild(child2));
        assertNotEquals(1, family.getChildren().size());
    }
    @Test
    void testDeleteChildByIndexSuccess() {
        Family family = CreateFamily();
        Human child1 = new Human("Bohdan", "Ivanov",LocalDate.of(2018, Month.AUGUST, 23)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli());
        Human child2 = new Human("Katya", "Ivanova",LocalDate.of(2018, Month.AUGUST, 23)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli());
        family.AddChild(child1);
        family.AddChild(child2);

        assertTrue(family.DeleteChild(0));
        assertEquals(2, family.getChildren().size());
        assertEquals(child1, family.getChildren().getFirst());
    }

    @Test
    void testDeleteChildByIndexOutOfBounds() {
        Family family = CreateFamily();
        Human child1 = new Human("Bohdan", "Ivanov",LocalDate.of(2018, Month.AUGUST, 23)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli());
        family.AddChild(child1);


        assertFalse(family.DeleteChild(2)); // Індекс за межами
        assertNotEquals(1, family.getChildren().size());
    }
    @Test
    void testAddChild() {
       Family family = CreateFamily();

        Human child1 = new Human("Volodymyr", "Ivanov",	LocalDate.of(2018, Month.AUGUST, 23)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli());
        family.AddChild(child1);

        assertEquals(2, family.getChildren().size());
        assertEquals(child1, family.getChildren().get(1));
    }
    @Test
    void testCountFamilyWithChildren() {
        Family family = CreateFamily();
        assertEquals(3, family.CountFamily()); // Мати + Батько + Дитина
    }

    @Test
    void testCountFamilyWithoutChildren() {
        Family family = CreateFamily();
        family.DeleteChild(0);
        assertEquals(2, family.CountFamily()); // Мати + Батько
    }
    @Test
    public void testEqualsReflexive() {
        Family family = CreateFamily();
        assertTrue(family.equals(family), "Об'єкт має дорівнювати самому собі (рефлексивність)");
    }
    @Test
    public void testEqualsSymmetric() {
         Family family1 = CreateFamily();
         Family family2 = CreateFamily();
         assertTrue(family1.equals(family2), "Якщо family1 дорівнює family2, то family2 має дорівнювати family1 (симетричність)");
         assertTrue(family2.equals(family1), "Якщо  family2 дорівнює family1, то family1 має дорівнювати family2 (симетричність)");
    }
    @Test
    public void testEqualsTransitive() {
        Family family1 = CreateFamily();
        Family family2 = CreateFamily();
        Family family3 = CreateFamily();
        assertTrue(family1.equals(family2), "Транзитивність: family1 дорівнює family2");
        assertTrue(family2.equals(family3), "Транзитивність: family2 дорівнює family3");
        assertTrue(family1.equals(family3), "Транзитивність: family1 дорівнює family3");
    }
    @Test
    public void testEqualsConsistency() {
        Family family1 = CreateFamily();
        Family family2 = CreateFamily();
        assertTrue(family1.equals(family2), "Перевірка консистентності: family1 дорівнює family2");
        assertTrue(family1.equals(family2), "Перевірка консистентності: повторне порівняння повинно бути однаковим");
    }
    @Test
    public void testEqualsNull() {
        Family family1 = CreateFamily();
        assertFalse(family1.equals(null), "Об'єкт не має дорівнювати null");
    }
    @Test
    public void testEqualsDifferentClass() {
        Family family1 = CreateFamily();
        Pet testPet = new DomesticCat(false,"Nicky",3,70, new LinkedHashSet<>(Arrays.asList("habit1","habit2")));
        assertFalse(family1.equals(testPet), "Об'єкт не має дорівнювати об'єкту іншого класу");
    }
    @Test
    public void testHashCodeConsistency() {
        Family family1 = CreateFamily();
        int initialHashCode = family1.hashCode();
        assertEquals(initialHashCode,family1.hashCode(), "Хеш-код має залишатися постійним при повторних викликах");
    }
    @Test
    public void testHashCodeEqualsContract() {
         Family family1 = CreateFamily();
         Family family2 = CreateFamily();
        assertEquals(family1.hashCode(),family2.hashCode(), "Рівні об'єкти мають однаковий хеш-код");
    }
    @Test
    public void testNotEquals() {
        Family family1 = CreateFamily();
        Family family2 = CreateFamily();
        family2.setChildren(null);
        assertFalse(family1.equals(family2), "Різні об'єкти не мають дорівнювати один одному");
    }
}