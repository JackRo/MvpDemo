package cn.jackro.mvpdemo.bean.login;

import cn.jackro.mvpdemo.bean.HttpResult;

/**
 * User bean
 */
public class User extends HttpResult {
    /**
     * 客户id
     */
    private int khid;
    /**
     * 客户名称
     */
    private String khmc;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 电话
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    public int getKhid() {
        return khid;
    }

    public void setKhid(int khid) {
        this.khid = khid;
    }

    public String getKhmc() {
        return khmc;
    }

    public void setKhmc(String khmc) {
        this.khmc = khmc;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
