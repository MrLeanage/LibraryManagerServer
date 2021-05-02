package bean;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Request implements Serializable {
    private Integer rID = null;
    private String rBName = null;
    private String rDescription = null;
    private String rBAuthor = null;
    private String rBEdition = null;
    private String rBPublisher = null;
    private String rDate = null;
    private String rStatus = null;

    public Request() {
    }

    public Request(Integer rID, String rBName, String rDescription, String rBAuthor, String rBEdition, String rBPublisher, String rDate, String rStatus) {
        this.rID = rID;
        this.rBName = rBName;
        this.rDescription = rDescription;
        this.rBAuthor = rBAuthor;
        this.rBEdition = rBEdition;
        this.rBPublisher = rBPublisher;
        this.rDate = rDate;
        this.rStatus = rStatus;
    }

    public Integer getrID() {
        return rID;
    }

    public IntegerProperty rIDProperty(){
        return new SimpleIntegerProperty(rID);
    }

    public void setrID(Integer rID) {
        this.rID = rID;
    }

    public String getrBName() {
        return rBName;
    }

    public StringProperty rBNameProperty(){
        return new SimpleStringProperty(rBName);
    }

    public void setrBName(String rBName) {
        this.rBName = rBName;
    }

    public String getrDescription() {
        return rDescription;
    }

    public StringProperty rDescriptionProperty(){
        return new SimpleStringProperty(rDescription);
    }

    public void setrDescription(String rDescription) {
        this.rDescription = rDescription;
    }

    public String getrBAuthor() {
        return rBAuthor;
    }

    public StringProperty rBAuthorProperty(){
        return new SimpleStringProperty(rBAuthor);
    }

    public void setrBAuthor(String rBAuthor) {
        this.rBAuthor = rBAuthor;
    }

    public String getrBEdition() {
        return rBEdition;
    }

    public StringProperty rBEditionProperty(){
        return new SimpleStringProperty(rBEdition);
    }

    public void setrBEdition(String rBEdition) {
        this.rBEdition = rBEdition;
    }

    public String getrBPublisher() {
        return rBPublisher;
    }

    public StringProperty rBPublisherProperty(){
        return new SimpleStringProperty(rBPublisher);
    }

    public void setrBPublisher(String rBPublisher) {
        this.rBPublisher = rBPublisher;
    }

    public String getrDate() {
        return rDate;
    }

    public StringProperty rDateProperty(){
        return new SimpleStringProperty(rDate);
    }

    public void setrDate(String rDate) {
        this.rDate = rDate;
    }

    public String getrStatus() {
        return rStatus;
    }

    public StringProperty rStatusProperty(){
        return new SimpleStringProperty(rStatus);
    }

    public void setrStatus(String rStatus) {
        this.rStatus = rStatus;
    }
}
