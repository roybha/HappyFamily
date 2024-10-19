import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Objects;

public class Family {
private Human mother;
private Human father;
private ArrayList<Human> children;
private LinkedHashSet<Pet> pets;
public Human getMother() {
    return mother;
}
public void setMother(Human mother) {
    this.mother = mother;
}
public Human getFather() {
    return father;
}
public void setFather(Human father) {
    this.father = father;
}
public ArrayList<Human> getChildren() {
    return children;
}
public void setChildren(ArrayList<Human> children) {
    this.children = children;
}
public LinkedHashSet<Pet> getPets() {
    return pets;
}
public Pet getPet(Pet pet) {
    if (pets.contains(pet)) {
        return pet;
    }
    return null;
}
public void setPets(LinkedHashSet<Pet> pets) {
    this.pets = pets;
}
public Family(Human mother, Human father) {
    this.mother = mother;
    this.father = father;
    mother.setFamily(this);
    father.setFamily(this);
    this.children = null;
}
@Override
    public String toString() {

    return this.getClass()+"{ mother="+((this.getMother()!=null)?this.mother.toString():" null")+",father="+((this.getFather()!=null)?this.father.toString():"null")+", children="+InfoAboutChildren()+
            ", pets="+((this.getPets()!=null) ? this.pets.toString()+"}":" null");
}
public  String InfoAboutChildren(){
    if (this.children == null || this.children.isEmpty()) {
        return "none";
    }

    String result = "";
    for (Human human : this.children) {
        if (human != null) {
            result += human.toString() + ", ";
        }
    }

    // Видаляємо останню кому і пробіл, якщо додано дітей
    if (!result.isEmpty()) {
        result = result.substring(0, result.length() - 2); // Вилучаємо ", "
    }

    return result;
}
public void AddChild(Human child) {
    if (this.getChildren() == null) {
        this.children = new ArrayList<>();
        this.children.add(child);
        child.setFamily(this);
    } else {
        this.children.add(child); // Додаємо нову дитину в кінець
        child.setFamily(this);
    }
}
    public boolean DeleteChild(int i){
        if(children!=null&&i<this.children.size() && i>=0){
            ArrayList<Human> newChildren = new ArrayList<>(this.children);
            newChildren.remove(newChildren.get(i));
            setChildren(newChildren);
            return true;
        }
        else
            return false;

    }
    public  boolean DeleteChild(Human child){
         if(this.children.contains(child)){
            this.children.remove(child);
            return true;
         }
         return false;
    }
    public  int CountFamily(){
    int fatherCount = 1;
    int motherCount = 1;
    int result=fatherCount+motherCount;
    if(children!=null){
        for(Human child : this.children){
            if(child!=null){
                result++;
            }

    }

    }
    return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Family family = (Family) obj;
        return Objects.equals(mother, family.mother) &&
                Objects.equals(father, family.father) &&
                Objects.equals(children, family.children) &&
                Objects.equals(pets, family.pets);
    }
    @Override
    public int hashCode() {
        return Objects.hash(mother, father, pets) + Objects.hashCode(children);
    }
    @Override
    @SuppressWarnings("deprecation")
    protected void finalize() throws Throwable {
        try {
            System.out.printf("\nВидаляємо сім'ю кількістю %d",this.CountFamily() );
        } finally {
            super.finalize();
        }
    }
}
