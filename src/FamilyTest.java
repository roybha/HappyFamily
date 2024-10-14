import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FamilyTest {
    Family CreateFamily(){
        Human mother = new Human("Anna", "Ivanova",33);
        Human father = new Human("Ivan", "Ivanov",36);
        Family family = new Family(mother, father);
        family.AddChild(new Human("Alex","Ivanov",10));
        family.setPet(new Dog( true,"Rex"));
        return family;
    }
    @Test
    void testToString() {

        Family family = CreateFamily();
        String expected = "class Family{ mother=class Human{name= Anna,surname= Ivanova,year= 33,iq= 0,schedule =[[Monday, null], [Tuesday, null], [Wednesday, null], [Thursday, null], [Friday, null], [Saturday, null], [Sunday, null]]},father=class Human{name= Ivan,surname= Ivanov,year= 36,iq= 0,schedule =[[Monday, null], [Tuesday, null], [Wednesday, null], [Thursday, null], [Friday, null], [Saturday, null], [Sunday, null]]}, children=class Human{name= Alex,surname= Ivanov,year= 10,iq= 0,schedule =[[Monday, null], [Tuesday, null], [Wednesday, null], [Thursday, null], [Friday, null], [Saturday, null], [Sunday, null]]}, pet=DOG{ nickname= Rex, age= 0, tricklevel= 0, habits= none, canFly= false, numberOfLegs= 4, hasFur= true }}";
        assertEquals(expected, family.toString());
        Human anotherHuman = new Human("Jane", "Smith", 1990);
        family.setFather(anotherHuman);
        assertNotEquals(expected, anotherHuman.toString());
    }
    @Test
    void testDeleteChildSuccess() {
        Family family = CreateFamily();
        Human child1 = new Human("Bohdan", "Ivanov",6);
        family.AddChild(child1);

        assertTrue(family.DeleteChild(child1));
        assertEquals(1, family.getChildren().length);
    }

    @Test
    void testDeleteChildNotFound() {
        Family family = CreateFamily();
        Human child1 = new Human("Bohdan", "Ivanov",6);
        Human child2 = new Human("Katya", "Ivanova",8);
        family.AddChild(child1);

        assertFalse(family.DeleteChild(child2));
        assertNotEquals(1, family.getChildren().length);
    }
    @Test
    void testDeleteChildByIndexSuccess() {
        Family family = CreateFamily();
        Human child1 = new Human("Bohdan", "Ivanov",6);
        Human child2 = new Human("Katya", "Ivanova",8);
        family.AddChild(child1);
        family.AddChild(child2);

        assertTrue(family.DeleteChild(0));
        assertEquals(2, family.getChildren().length);
        assertEquals(child1, family.getChildren()[0]);
    }

    @Test
    void testDeleteChildByIndexOutOfBounds() {
        Family family = CreateFamily();
        Human child1 = new Human("Bohdan", "Ivanov",6);
        family.AddChild(child1);


        assertFalse(family.DeleteChild(2)); // Індекс за межами
        assertNotEquals(1, family.getChildren().length);
    }
    @Test
    void testAddChild() {
       Family family = CreateFamily();

        Human child1 = new Human("Volodymyr", "Ivanov",11);
        family.AddChild(child1);

        assertEquals(2, family.getChildren().length);
        assertEquals(child1, family.getChildren()[1]);
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
        Pet testPet = new DomesticCat(false,"Nicky");
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