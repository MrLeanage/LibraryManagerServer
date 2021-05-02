package bean;

public class BookView {
    private Integer viewID = null;
    private Integer viewBookID = null;
    private String viewBookName = null;
    private String viewDate = null;

    public BookView() {
    }

    public BookView(Integer viewID, Integer viewBookID, String viewBookName, String viewDate) {
        this.viewID = viewID;
        this.viewBookID = viewBookID;
        this.viewBookName = viewBookName;
        this.viewDate = viewDate;
    }

    public Integer getViewID() {
        return viewID;
    }

    public void setViewID(Integer viewID) {
        this.viewID = viewID;
    }

    public Integer getViewBookID() {
        return viewBookID;
    }

    public void setViewBookID(Integer viewBookID) {
        this.viewBookID = viewBookID;
    }

    public String getViewBookName() {
        return viewBookName;
    }

    public void setViewBookName(String viewBookName) {
        this.viewBookName = viewBookName;
    }

    public String getViewDate() {
        return viewDate;
    }

    public void setViewDate(String viewDate) {
        this.viewDate = viewDate;
    }
}
