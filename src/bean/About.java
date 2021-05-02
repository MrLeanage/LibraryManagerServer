package bean;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class About implements Serializable {
    private Integer aInfoID = null;
    private String aLibraryName = null;
    private String aLibraryAddress = null;
    private String aContactPerson = null;
    private String aContactDesignation = null;
    private String aContactEmail = null;
    private String aLibraryNumber = null;

    public About() {
    }

    public About(Integer aInfoID, String aLibraryName, String aLibraryAddress, String aContactPerson, String aContactDesignation, String aContactEmail, String aLibraryNumber) {
        this.aInfoID = aInfoID;
        this.aLibraryName = aLibraryName;
        this.aLibraryAddress = aLibraryAddress;
        this.aContactPerson = aContactPerson;
        this.aContactDesignation = aContactDesignation;
        this.aContactEmail = aContactEmail;
        this.aLibraryNumber = aLibraryNumber;
    }

    public Integer getaInfoID() {
        return aInfoID;
    }

    public IntegerProperty aInfoIDProperty(){
        return new SimpleIntegerProperty(aInfoID);
    }

    public void setaInfoID(Integer aInfoID) {
        this.aInfoID = aInfoID;
    }

    public String getaLibraryName() {
        return aLibraryName;
    }

    public SimpleStringProperty aLibraryNameProperty(){
        return new SimpleStringProperty(aLibraryName);
    }

    public void setaLibraryName(String aLibraryName) {
        this.aLibraryName = aLibraryName;
    }

    public String getaLibraryAddress() {
        return aLibraryAddress;
    }

    public SimpleStringProperty aLibraryAddressProperty(){
        return new SimpleStringProperty(aLibraryAddress);
    }

    public void setaLibraryAddress(String aLibraryAddress) {
        this.aLibraryAddress = aLibraryAddress;
    }

    public String getaContactPerson() {
        return aContactPerson;
    }

    public SimpleStringProperty aContactPersonProperty(){
        return new SimpleStringProperty(aContactPerson);
    }

    public void setaContactPerson(String aContactPerson) {
        this.aContactPerson = aContactPerson;
    }

    public String getaContactDesignation() {
        return aContactDesignation;
    }

    public SimpleStringProperty aContactDesignationProperty(){
        return new SimpleStringProperty(aContactDesignation);
    }

    public void setaContactDesignation(String aContactDesignation) {
        this.aContactDesignation = aContactDesignation;
    }

    public String getaContactEmail() {
        return aContactEmail;
    }

    public SimpleStringProperty aContactEmailProperty(){
        return new SimpleStringProperty(aContactEmail);
    }

    public void setaContactEmail(String aContactEmail) {
        this.aContactEmail = aContactEmail;
    }

    public String getaLibraryNumber() {
        return aLibraryNumber;
    }

    public SimpleStringProperty aLibraryNumberProperty(){
        return new SimpleStringProperty(aLibraryNumber);
    }

    public void setaLibraryNumber(String aLibraryNumber) {
        this.aLibraryNumber = aLibraryNumber;
    }
}
