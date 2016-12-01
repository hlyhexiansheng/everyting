package serialzable;

import java.io.*;
import java.util.Date;

/**
 * Created by Administrator on 2015/12/13.
 */
public class SerialzaleTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("c:\\objectFile.obj"));
        Person person = new Person();
        person.setId(10);
        person.setAge(10.1f);

        StringBuffer stringBuffer = new StringBuffer();

        for(int i = 0; i < 100000;i++){
            stringBuffer.append(i);
        }
        person.setName(stringBuffer.toString());

        out.writeObject(person);


        out.writeObject(new Date());

        out.writeObject("String");

        out.writeObject("String a ");

        out.close();


        ObjectInputStream in = new ObjectInputStream(new FileInputStream("c:\\objectFile.obj"));
        Person person1 = (Person)in.readObject();


        Date date = (Date)in.readObject();

        String s = (String)in.readObject();

        String s2 = (String)in.readObject();

        System.out.println(person1);
        System.out.println(date);
        System.out.println(s);

        System.out.println(s2);
    }

}

class Person implements Serializable{

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    private int id;

    private String name;

    private float age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }
}
