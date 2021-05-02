package util.querry;

public class FeedbackQuery {
    public static final String LOAD_ALL_FEEDBACK_DATA = "SELECT * FROM feedback";
    public static final String LOAD_SPECIFIC_FEEDBACK_DATA = "SELECT * FROM feedback WHERE fID = ?";
    public static final String INSERT_FEEDBACK_DATA = "INSERT INTO feedback (fCategory, fTitle, fDescription, fDate, fTime, fStatus) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_FEEDBACK_STATUS = "UPDATE feedback SET fStatus = ? WHERE fID = ?";
}
