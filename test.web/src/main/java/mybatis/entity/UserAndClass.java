package mybatis.entity;

/**
 * Created by Administrator on 2015/12/11.
 */
public class UserAndClass {

    private int userId;

    private String userName;

    private int userSex;

    private int userGrade;

    private int gradeId;

    private String gradeClassName;

    private String address;

    @Override
    public String toString() {
        return "UserAndClass{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userSex=" + userSex +
                ", userGrade=" + userGrade +
                ", gradeId=" + gradeId +
                ", gradeClassName='" + gradeClassName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public int getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(int userGrade) {
        this.userGrade = userGrade;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeClassName() {
        return gradeClassName;
    }

    public void setGradeClassName(String gradeClassName) {
        this.gradeClassName = gradeClassName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
