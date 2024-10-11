import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PetTest {

    @Test
    void testToString() {
        Pet pet = new Pet(Species.SNAKE,"Nicky");
        String expected = "SNAKE{ nickname= Nicky, age= 0, tricklevel= 0, habits= none, canFly= false, numberOfLegs= 0, hasFur= false }";
        assertEquals(expected, pet.toString());
        Pet anotherPet = new Pet(Species.DOG,"Nicky");
        String incorrectExpected = "SNAKE{ nickname= Nicky, age= 0, tricklevel= 0, habits= none, canFly= false, numberOfLegs= 4, hasFur= true}";
        assertNotEquals(incorrectExpected, anotherPet.toString());
    }

    @Test
    public void testEqualsReflexive() {
        Pet testPet = new Pet(Species.SNAKE,"Nicky");
        assertTrue(testPet.equals(testPet), "Об'єкт має дорівнювати самому собі (рефлексивність)");
    }
    @Test
    public void testEqualsSymmetric() {
        Pet testPet = new Pet(Species.SNAKE,"Nicky");
        Pet anotherPet = new Pet(Species.SNAKE,"Nicky");
        assertTrue(testPet.equals(anotherPet), "Якщо testPet дорівнює anotherPet, то anotherPet має дорівнювати testPet (симетричність)");
        assertTrue(anotherPet.equals(testPet), "Якщо  anotherPet дорівнює testPet, то testPet має дорівнювати anotherPet (симетричність)");
    }
    @Test
    public void testEqualsTransitive() {
        Pet onePet = new Pet(Species.SNAKE,"Nicky");
        Pet twoPet = new Pet(Species.SNAKE,"Nicky");
        Pet threePet = new Pet(Species.SNAKE,"Nicky");
        assertTrue(onePet.equals(twoPet), "Транзитивність: onePet дорівнює twoPet");
        assertTrue(twoPet.equals(threePet), "Транзитивність: twoPet дорівнює threePet");
        assertTrue(onePet.equals(threePet), "Транзитивність: onePet дорівнює threePet");
    }
    @Test
    public void testEqualsConsistency() {
        Pet onePet = new Pet(Species.SNAKE,"Nicky");
        Pet twoPet = new Pet(Species.SNAKE,"Nicky");
        assertTrue(onePet.equals(twoPet), "Перевірка консистентності: onePet дорівнює twoPet");
        assertTrue(onePet.equals(twoPet), "Перевірка консистентності: повторне порівняння повинно бути однаковим");
    }
    @Test
    public void testEqualsNull() {
        Pet testPet = new Pet(Species.SNAKE,"Nicky");
        assertFalse(testPet.equals(null), "Об'єкт не має дорівнювати null");
    }
    @Test
    public void testEqualsDifferentClass() {
        Pet testPet = new Pet(Species.SNAKE,"Nicky");
        Human testHuman = new Human("Andriy","Siryi",33);
        assertFalse(testPet.equals(testHuman), "Об'єкт не має дорівнювати об'єкту іншого класу");
    }
    @Test
    public void testHashCodeConsistency() {
        Pet testPet = new Pet(Species.SNAKE,"Nicky");
        int initialHashCode = testPet.hashCode();
        assertEquals(initialHashCode, testPet.hashCode(), "Хеш-код має залишатися постійним при повторних викликах");
    }
    @Test
    public void testHashCodeEqualsContract() {
        Pet testPet1 = new Pet(Species.SNAKE,"Nicky");
        Pet testPet2 = new Pet(Species.SNAKE,"Nicky");
        assertEquals(testPet1.hashCode(), testPet2.hashCode(), "Рівні об'єкти мають однаковий хеш-код");
    }
    @Test
    public void testNotEquals() {
       Pet testPet1 = new Pet(Species.SNAKE,"Nicky");
       Pet testPet2 = new Pet(Species.TURTLE,"Nicky");
       assertFalse(testPet1.equals(testPet2), "Різні об'єкти не мають дорівнювати один одному");
    }

}