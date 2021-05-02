package util.querry;

public class BookQuery {
    public static final String LOAD_ALL_BOOK_DATA = "SELECT * FROM book";
    public static final String LOAD_SPECIFIC_BOOK_DATA = "SELECT * FROM book WHERE bID = ?";
    public static final String INSERT_BOOK_DATA = "INSERT INTO book (bISBN, bName, bAuthor, bAvailableStatus, bArrivalStatus) VALUES (?, ?, ?, ?, ?)";
    public static final String UPDATE_BOOK_DATA = "UPDATE book SET bISBN = ?, bName = ?, bAuthor = ?, bAvailableStatus = ?, bArrivalStatus = ? WHERE bID = ?";
    public static final String DELETE_BOOK_DATA = "DELETE FROM book WHERE bID = ?";

    public static final String LOAD_ALL_BOOK_VIEW = "SELECT * FROM bookview";
    public static final String INSERT_BOOK_VIEW = "INSERT INTO bookview (vBID, vDate) VALUES (?, ?)";


}
