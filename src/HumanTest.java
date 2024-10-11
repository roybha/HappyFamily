import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HumanTest {

    @Test
    void testToString() {
        Human human = new Human("John", "Doe", 1985);
        String expected = "Human{name= John,surname= Doe,year= 1985,iq= 0,schedule =[[Monday, null], [Tuesday, null], [Wednesday, null], [Thursday, null], [Friday, null], [Saturday, null], [Sunday, null]]}";
        assertEquals(expected, human.toString());

        Human anotherHuman = new Human("Jane", "Smith", 1990);
        String incorrectExpected = "Human{name= jane,surname= smith,year= 1990,iq= 0,schedule =[[Monday, null], [Tuesday, null], [Wednesday, null], [Thursday, null], [Friday, null], [Saturday, null], [Sunday, null]]}";
        assertNotEquals(incorrectExpected, anotherHuman.toString());
    }
    @Test
    public void testEqualsReflexive() {
        Human human1 = new Human("John", "Doe", 1980);
        assertTrue(human1.equals(human1), "Об'єкт має дорівнювати самому собі (рефлексивність)");
    }

    @Test
    public void testEqualsSymmetric() {
        Human human1 = new Human("John", "Doe", 1980);
        Human human2 = new Human("John", "Doe", 1980);
        assertTrue(human1.equals(human2), "Якщо human1 дорівнює human2, то human2 має дорівнювати human1 (симетричність)");
        assertTrue(human2.equals(human1), "Якщо human2 дорівнює human1, то human1 має дорівнювати human2 (симетричність)");
    }

    @Test
    public void testEqualsTransitive() {
        Human human1 = new Human("John", "Doe", 1980);
        Human human2 = new Human("John", "Doe", 1980);
        Human human3 = new Human("John", "Doe", 1980);
        assertTrue(human1.equals(human2), "Транзитивність: human1 дорівнює human2");
        assertTrue(human2.equals(human3), "Транзитивність: human2 дорівнює human3");
        assertTrue(human1.equals(human3), "Транзитивність: human1 дорівнює human3");
    }

    @Test
    public void testEqualsConsistency() {
        Human human1 = new Human("John", "Doe", 1980);
        Human human2 = new Human("John", "Doe", 1980);
        assertTrue(human1.equals(human2), "Перевірка консистентності: human1 дорівнює human2");
        assertTrue(human1.equals(human2), "Перевірка консистентності: повторне порівняння повинно бути однаковим");
    }

    @Test
    public void testEqualsNull() {
        Human human1 = new Human("John", "Doe", 1980);
        assertFalse(human1.equals(null), "Об'єкт не має дорівнювати null");
    }

    @Test
    public void testEqualsDifferentClass() {
        Human human1 = new Human("John", "Doe", 1980);
        String notAHuman = "Not a human";
        assertFalse(human1.equals(notAHuman), "Об'єкт не має дорівнювати об'єкту іншого класу");
    }

    @Test
    public void testHashCodeConsistency() {
        Human human1 = new Human("John", "Doe", 1980);
        int initialHashCode = human1.hashCode();
        assertEquals(initialHashCode, human1.hashCode(), "Хеш-код має залишатися постійним при повторних викликах");
    }

    @Test
    public void testHashCodeEqualsContract() {
        Human human1 = new Human("John", "Doe", 1980);
        Human human2 = new Human("John", "Doe", 1980);
        assertEquals(human1.hashCode(), human2.hashCode(), "Рівні об'єкти мають однаковий хеш-код");
    }

    @Test
    public void testNotEquals() {
        Human human1 = new Human("John", "Doe", 1980);
        Human human2 = new Human("Jane", "Doe", 1980);
        assertFalse(human1.equals(human2), "Різні об'єкти не мають дорівнювати один одному");
    }

}