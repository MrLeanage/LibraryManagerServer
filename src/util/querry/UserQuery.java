package util.querry;

public class UserQuery {
    public static final String LOAD_ALL_USER_DATA = "SELECT * FROM user";
    public static final String LOAD_SPECIFIC_USER_DATA = "SELECT * FROM user WHERE uEmail = ?";
    public static final String INSERT_USER_DATA = "INSERT INTO user (uEmail, uName, uPassword, uType) VALUES (?, ?, ?, ?)";
    public static final String UPDATE_USER_DATA = "UPDATE user SET uName = ?, uType = ? WHERE uEmail = ?";
    public static final String UPDATE_USER_PASSWORD = "UPDATE user SET uPassword = ? WHERE uEmail = ?";
    public static final String DELETE_USER_DATA = "DELETE FROM user WHERE uEmail = ?";
}
