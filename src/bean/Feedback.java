package bean;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Feedback implements Serializable {
    private Integer fID = null;
    private String fCategory = null;
    private String fTitle = null;
    private String fDescription = null;
    private String fDate = null;
    private String fTime = null;
    private String fStatus = null;

    public Feedback() {
    }

    public Feedback(Integer fID, String fCategory, String fTitle, String fDescription, String fDate, String fTime, String fStatus) {
        this.fID = fID;
        this.fCategory = fCategory;
        this.fTitle = fTitle;
        this.fDescription = fDescription;
        this.fDate = fDate;
        this.fTime = fTime;
        this.fStatus = fStatus;
    }

    public Integer getfID() {
        return fID;
    }

    public IntegerProperty fIDProperty(){
        return new SimpleIntegerProperty(fID);
    }

    public void setfID(Integer fID) {
        this.fID = fID;
    }

    public String getfCategory() {
        return fCategory;
    }

    public StringProperty fCategoryProperty(){
        return new SimpleStringProperty(fCategory);
    }

    public void setfCategory(String fCategory) {
        this.fCategory = fCategory;
    }

    public String getfTitle() {
        return fTitle;
    }

    public StringProperty fTitleProperty(){
        return new SimpleStringProperty(fTitle);
    }

    public void setfTitle(String fTitle) {
        this.fTitle = fTitle;
    }

    public String getfDescription() {
        return fDescription;
    }

    public StringProperty fDescriptionProperty(){
        return new SimpleStringProperty(fDescription);
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    public String getfDate() {
        return fDate;
    }

    public StringProperty fDateProperty(){
        return new SimpleStringProperty(fDate);
    }

    public void setfDate(String fDate) {
        this.fDate = fDate;
    }

    public String getfTime() {
        return fTime;
    }

    public StringProperty fTimeProperty(){
        return new SimpleStringProperty(fTime);
    }

    public void setfTime(String fTime) {
        this.fTime = fTime;
    }

    public String getfStatus() {
        return fStatus;
    }

    public StringProperty fStatusProperty(){
        return new SimpleStringProperty(fStatus);
    }

    public void setfStatus(String fStatus) {
        this.fStatus = fStatus;
    }
}
