import java.util.Arrays;
import java.util.Objects;

public class Family {
private Human mother;
private Human father;
private Human[] children;
private Pet pet;
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
public Human[] getChildren() {
    return children;
}
public void setChildren(Human[] children) {
    this.children = children;
}
public Pet getPet() {
    return pet;
}
public void setPet(Pet pet) {
    this.pet = pet;
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

    return "Family{ mother="+this.mother.toString()+",father="+this.father.toString()+", children="+InfoAboutChildren()+", pet="+this.pet.toString()+"}";
}
public  String InfoAboutChildren(){
    for(Human human : this.children){
        if(human != null){
            System.out.print(human.toString()+", ");
        }
    }
    return "";
}
public void AddChild(Human child) {
    if (this.getChildren() == null) {
        this.children = new Human[1];
        this.children[0] = child;
        child.setFamily(this);
    } else {
        Human[] newChildren = new Human[this.children.length + 1];
        System.arraycopy(this.children, 0, newChildren, 0, this.children.length);
        newChildren[this.children.length] = child; // Додаємо нову дитину в кінець
        this.children = newChildren;
        child.setFamily(this);
    }
}
    public boolean DeleteChild(int i){
        if(children!=null&&i<this.children.length && i>=0){
            Human[] newChildren = new Human[this.children.length-1];
            System.arraycopy(this.children, 0, newChildren, 0, i);
            System.arraycopy(this.children, i + 1, newChildren, i, (this.children.length - i) - 1);
            setChildren(newChildren);
            return true;
        }
        else
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
                Arrays.equals(children, family.children) &&
                Objects.equals(pet, family.pet);
    }
    @Override
    public int hashCode() {
        return Objects.hash(mother, father, pet) + Arrays.hashCode(children);
    }
}