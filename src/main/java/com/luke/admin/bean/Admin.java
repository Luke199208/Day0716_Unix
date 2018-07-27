package com.luke.admin.bean;

import com.luke.role.bean.Role_info;

import java.util.List;


/***
 * com.luke.admin.bean
 * dllo
 * 18/7/26
 *             ,%%%%%%%%,
 *           ,%%/\%%%%/\%%
 *          ,%%%\c "" J/%%%
 * %.       %%%%/ 0  0 \%%%
 * `%%.     %%%%    _  |%%%
 *  `%%     `%%%%(__Y__)%%'
 *  //       ;%%%%`\-/%%%'
 * ((       /  `%%%%%%%'
 *  \\    .'     '%%%'|    攻
 *   \\  /       \  | |    城
 *    \\/         ) | |    湿
 *     \         /_ | |__
 *     (___________))))))) 
 *
 *       我湿一吼  BUG无有                        
 */
public class Admin {
    private String admin_id,admin_code
            ,admin_password,admin_name
            ,admin_telephone,admin_email
            ,enrolldate;
    private List<Role_info> role_infoList;


    @Override
    public String toString() {
        return "Admin{" +
                "admin_id='" + admin_id + '\'' +
                ", admin_code='" + admin_code + '\'' +
                ", admin_password='" + admin_password + '\'' +
                ", admin_name='" + admin_name + '\'' +
                ", admin_telephone='" + admin_telephone + '\'' +
                ", admin_email='" + admin_email + '\'' +
                ", enrolldate='" + enrolldate + '\'' +
                ", role_infoList=" + role_infoList +
                '}';
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_code() {
        return admin_code;
    }

    public void setAdmin_code(String admin_code) {
        this.admin_code = admin_code;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_telephone() {
        return admin_telephone;
    }

    public void setAdmin_telephone(String admin_telephone) {
        this.admin_telephone = admin_telephone;
    }

    public String getAdmin_email() {
        return admin_email;
    }

    public void setAdmin_email(String admin_email) {
        this.admin_email = admin_email;
    }

    public String getEnrolldate() {
        return enrolldate;
    }

    public void setEnrolldate(String enrolldate) {
        this.enrolldate = enrolldate;
    }

    public List<Role_info> getRole_infoList() {
        return role_infoList;
    }

    public void setRole_infoList(List<Role_info> role_infoList) {
        this.role_infoList = role_infoList;
    }
}
