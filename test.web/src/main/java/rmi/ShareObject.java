package rmi;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/13.
 */
public class ShareObject implements Serializable{

    private int id;

    private String name;

    private String password;


    public static ShareObject build(int id,String name,String password){
        ShareObject object = new ShareObject();
        object.setId(id);
        object.setName(name);
        object.setPassword(password);
        return object;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ShareObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
