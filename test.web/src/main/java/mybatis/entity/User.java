package mybatis.entity;

/**
 * Created by Administrator on 2015/12/9.
 */
public class User {

    private int id;

    private String name;

    private int grade;

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    private int sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                ", sex=" + sex +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }
}
