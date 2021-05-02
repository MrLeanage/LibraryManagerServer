package bean;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class User implements Serializable {

    private String uEmmail = null;
    private String uName = null;
    private String uPassword = null;
    private String uType = null;

    public User() {
    }

    public User(String uEmmail, String uName, String uPassword, String uType) {
        this.uEmmail = uEmmail;
        this.uName = uName;
        this.uPassword = uPassword;
        this.uType = uType;
    }

    public String getuName() {
        return uName;
    }

    public StringProperty uNameProperty(){
        return new SimpleStringProperty(uName);
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuEmmail() {
        return uEmmail;
    }

    public StringProperty uEmmailProperty(){
        return new SimpleStringProperty(uEmmail);
    }

    public void setuEmmail(String uEmmail) {
        this.uEmmail = uEmmail;
    }

    public String getuPassword() {
        return uPassword;
    }

    public StringProperty uPasswordProperty(){
        return new SimpleStringProperty(uPassword);
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getuType() {
        return uType;
    }

    public StringProperty uTypeProperty(){
        return new SimpleStringProperty(uType);
    }

    public void setuType(String uType) {
        this.uType = uType;
    }
}
