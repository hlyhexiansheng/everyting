package btracetest;

/**
 * Created by noodles on 2017/11/21 16:29.
 */
public class BTTest1 {

    public static void main(String[] args) throws InterruptedException {
        final BTTest1 btTest1 = new BTTest1();

        for (int i = 0; i < 1000; i++) {
            Family family = new Family(new Person("person" + i, i), "--");
            btTest1.test(family, "p" + i);
            Thread.sleep(3000);
        }
    }

    public void test(Family family, String p) {
        System.out.println(family);
        System.out.println(p);
    }
}

class Family {
    public Person person;
    private String familiName;


    public Family(Person person, String familiName) {
        this.person = person;
        this.familiName = familiName;
    }

    @Override
    public String toString() {
        return "Family{" +
                "person=" + person +
                ", familiName='" + familiName + '\'' +
                '}';
    }
}

class Person {
    public String name;
    public long age;

    Person(String s, int i) {
        this.name = s;
        this.age = i;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
