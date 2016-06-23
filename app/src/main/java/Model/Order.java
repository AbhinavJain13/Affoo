package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GlobalData.GlobalData;

/**
 * Created by Ramakant on 6/21/2016.
 */
public class Order {

    String cusName = "default_value";
    String cusMobile = "default_value";
    String cusEmail = "default_value";
    String cusAddress = "default_value";
    String orderStatus = GlobalData.orderStatus.CURRENT.toString();
    String orderTime = "default_value";
    HashMap<String,Object> orderItems;
    String orderAmount;
    String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Order(){
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public HashMap<String,Object> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(HashMap<String,Object> orderItems) {
        this.orderItems = orderItems;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getCusMobile() {
        return cusMobile;
    }

    public void setCusMobile(String cusMobile) {
        this.cusMobile = cusMobile;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusEmail() {
        return cusEmail;
    }

    public void setCusEmail(String cusEmail) {
        this.cusEmail = cusEmail;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("cusName", getCusName());
        result.put("cusMobile", getCusMobile());
        result.put("cusEmail", getCusEmail());
        result.put("cusAddress", getCusAddress());
        result.put("orderStatus", getOrderStatus());
        result.put("orderTime", getOrderTime());
        result.put("orderItems", getOrderItems());
        result.put("orderAmount", getOrderAmount());
        return result;
    }
}
