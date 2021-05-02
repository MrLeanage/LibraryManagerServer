package bean;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Book implements Serializable {
    private Integer bID = null;
    private String bISBN = null;
    private String bName = null;
    private String bAuthor = null;
    private String bAvailableStatus = null;
    private String bArrivalStatus = null;

    public Book() {
    }

    public Book(Integer bID, String bISBN, String bName, String bAuthor, String bAvailableStatus, String bArrivalStatus) {
        this.bID = bID;
        this.bISBN = bISBN;
        this.bName = bName;
        this.bAuthor = bAuthor;
        this.bAvailableStatus = bAvailableStatus;
        this.bArrivalStatus = bArrivalStatus;
    }

    public Integer getbID() {
        return bID;
    }

    public IntegerProperty bIDProperty() {
        return new SimpleIntegerProperty(bID);
    }

    public void setbID(Integer bID) {
        this.bID = bID;
    }

    public String getbISBN() {
        return bISBN;
    }

    public StringProperty bISBNProperty() {
        return new SimpleStringProperty(bISBN);
    }


    public void setbISBN(String bISBN) {
        this.bISBN = bISBN;
    }

    public String getbName() {
        return bName;
    }

    public StringProperty bNameProperty() {
        return new SimpleStringProperty(bName);
    }


    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getbAuthor() {
        return bAuthor;
    }

    public StringProperty bAuthorProperty() {
        return new SimpleStringProperty(bAuthor);
    }

    public void setbAuthor(String bAuthor) {
        this.bAuthor = bAuthor;
    }

    public String getbAvailableStatus() {
        return bAvailableStatus;
    }

    public StringProperty bAvailableStatusProperty() {
        return new SimpleStringProperty(bAvailableStatus);
    }

    public void setbAvailableStatus(String bAvailableStatus) {
        this.bAvailableStatus = bAvailableStatus;
    }

    public String getbArrivalStatus() {
        return bArrivalStatus;
    }

    public StringProperty bArrivalStatusProperty() {
        return new SimpleStringProperty(bArrivalStatus);
    }

    public void setbArrivalStatus(String bArrivalStatus) {
        this.bArrivalStatus = bArrivalStatus;
    }
}
