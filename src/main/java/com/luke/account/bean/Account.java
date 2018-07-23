package com.luke.account.bean;

/***
 * com.luke.account.bean
 * dllo
 * 18/7/17
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
public class Account {
    private String account_id,recommender_id,login_name,login_passwd,status,create_date,
                   pause_date,close_date,real_name,idcard_no,birthdate,gender,occupation,
                   telephone,email,mailaddress,zipcode,qq,last_login_time,last_login_ip,
                   login_id,reloginpasswd;

    //service业务
    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_id='" + account_id + '\'' +
                ", recommender_id='" + recommender_id + '\'' +
                ", login_name='" + login_name + '\'' +
                ", login_passwd='" + login_passwd + '\'' +
                ", status='" + status + '\'' +
                ", create_date='" + create_date + '\'' +
                ", pause_date='" + pause_date + '\'' +
                ", close_date='" + close_date + '\'' +
                ", real_name='" + real_name + '\'' +
                ", idcard_no='" + idcard_no + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", gender='" + gender + '\'' +
                ", occupation='" + occupation + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", mailaddress='" + mailaddress + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", qq='" + qq + '\'' +
                ", last_login_time='" + last_login_time + '\'' +
                ", last_login_ip='" + last_login_ip + '\'' +
                '}';
    }

    public Account() {
    }

    public String getReloginpasswd() {
        return reloginpasswd;
    }

    public void setReloginpasswd(String reloginpasswd) {
        this.reloginpasswd = reloginpasswd;
    }

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getAccount_id() {

        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getRecommender_id() {
        return recommender_id;
    }

    public void setRecommender_id(String recommender_id) {
        this.recommender_id = recommender_id;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getLogin_passwd() {
        return login_passwd;
    }

    public void setLogin_passwd(String login_passwd) {
        this.login_passwd = login_passwd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getPause_date() {
        return pause_date;
    }

    public void setPause_date(String pause_date) {
        this.pause_date = pause_date;
    }

    public String getClose_date() {
        return close_date;
    }

    public void setClose_date(String close_date) {
        this.close_date = close_date;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getIdcard_no() {
        return idcard_no;
    }

    public void setIdcard_no(String idcard_no) {
        this.idcard_no = idcard_no;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMailaddress() {
        return mailaddress;
    }

    public void setMailaddress(String mailaddress) {
        this.mailaddress = mailaddress;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(String last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getLast_login_ip() {
        return last_login_ip;
    }

    public void setLast_login_ip(String last_login_ip) {
        this.last_login_ip = last_login_ip;
    }

}
