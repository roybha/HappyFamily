import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PetTest {

    @Test
    void testToString() {
        Pet pet = new Fish(true,"Nicky");
        String expected = "FISH{ nickname= Nicky, age= 0, tricklevel= 0, habits= none, canFly= false, numberOfLegs= 0, hasFur= false }";
        assertEquals(expected, pet.toString());
        Pet anotherPet = new Dog(true,"Nicky");
        String incorrectExpected = "SNAKE{ nickname= Nicky, age= 0, tricklevel= 0, habits= none, canFly= false, numberOfLegs= 4, hasFur= true}";
        assertNotEquals(incorrectExpected, anotherPet.toString());
    }

    @Test
    public void testEqualsReflexive() {
        Pet testPet = new Dog(true,"Nicky");
        assertTrue(testPet.equals(testPet), "Об'єкт має дорівнювати самому собі (рефлексивність)");
    }
    @Test
    public void testEqualsSymmetric() {
        Pet testPet = new Fish(true,"Nicky");
        Pet anotherPet = new Fish(true,"Nicky");
        assertTrue(testPet.equals(anotherPet), "Якщо testPet дорівнює anotherPet, то anotherPet має дорівнювати testPet (симетричність)");
        assertTrue(anotherPet.equals(testPet), "Якщо  anotherPet дорівнює testPet, то testPet має дорівнювати anotherPet (симетричність)");
    }
    @Test
    public void testEqualsTransitive() {
        Pet onePet = new Dog(true,"Nicky");
        Pet twoPet = new Dog(true,"Nicky");
        Pet threePet = new Dog(true,"Nicky");
        assertTrue(onePet.equals(twoPet), "Транзитивність: onePet дорівнює twoPet");
        assertTrue(twoPet.equals(threePet), "Транзитивність: twoPet дорівнює threePet");
        assertTrue(onePet.equals(threePet), "Транзитивність: onePet дорівнює threePet");
    }
    @Test
    public void testEqualsConsistency() {
        Pet onePet = new Fish(true,"Nicky");
        Pet twoPet = new Fish(true,"Nicky");
        assertTrue(onePet.equals(twoPet), "Перевірка консистентності: onePet дорівнює twoPet");
        assertTrue(onePet.equals(twoPet), "Перевірка консистентності: повторне порівняння повинно бути однаковим");
    }
    @Test
    public void testEqualsNull() {
        Pet testPet = new Dog(true,"Nicky");
        assertFalse(testPet.equals(null), "Об'єкт не має дорівнювати null");
    }
    @Test
    public void testEqualsDifferentClass() {
        Pet testPet = new Dog(true,"Nicky");
        Human testHuman = new Human("Andriy","Siryi",33);
        assertFalse(testPet.equals(testHuman), "Об'єкт не має дорівнювати об'єкту іншого класу");
    }
    @Test
    public void testHashCodeConsistency() {
        Pet testPet = new Fish(true,"Nicky");
        int initialHashCode = testPet.hashCode();
        assertEquals(initialHashCode, testPet.hashCode(), "Хеш-код має залишатися постійним при повторних викликах");
    }
    @Test
    public void testHashCodeEqualsContract() {
        Pet testPet1 = new Fish(true,"Nicky");
        Pet testPet2 = new Fish(true,"Nicky");
        assertEquals(testPet1.hashCode(), testPet2.hashCode(), "Рівні об'єкти мають однаковий хеш-код");
    }
    @Test
    public void testNotEquals() {
       Pet testPet1 = new Fish(true,"Nicky");
       Pet testPet2 = new Dog(false,"Nicky");
       assertFalse(testPet1.equals(testPet2), "Різні об'єкти не мають дорівнювати один одному");
    }

}