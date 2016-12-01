package mybatis.entity;

import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/12.
 */
public class Order {

    private int id;

    private int orderId;

    private Date createDate;

    private double price;

    private String buyerUserName;

    private String address;

    public static Order buildOrderUseAutoGenerateKey(int orderId){
        Order order = new Order();
        order.orderId = orderId;
        order.createDate = new Date();
        order.price = orderId + Math.random() * 100;
        order.buyerUserName = "user" + order.id;
        order.address = "yongzhou" + order.id;
        return order;
    }

    public static Order buildOrderUseSpecifyId(int id){
        Order order = new Order();
        order.id = id;
        order.orderId = id;
        order.createDate = new Date();
        order.price = id + Math.random() * 100;
        order.buyerUserName = "user" + order.id;
        order.address = "yongzhou" + order.id;
        return order;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", createDate=" + createDate +
                ", price=" + price +
                ", buyerUserName='" + buyerUserName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBuyerUserName() {
        return buyerUserName;
    }

    public void setBuyerUserName(String buyerUserName) {
        this.buyerUserName = buyerUserName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
