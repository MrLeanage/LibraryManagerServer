package util.querry;

public class RequestQuery {
    public static final String LOAD_ALL_REQUEST_DATA = "SELECT * FROM request";
    public static final String LOAD_SPECIFIC_REQUEST_DATA = "SELECT * FROM request WHERE rID = ?";
    public static final String INSERT_REQUEST_DATA = "INSERT INTO request (rBName, rDescription, rBAuthor, rBEdition, rBPublisher, rDate, rStatus) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_REQUEST_STATUS = "UPDATE request SET rStatus = ? WHERE rID = ?";
}
