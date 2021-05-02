package util.querry;

public class AboutQuery {
    /**
     *
     * private Integer aInfoID = null;
     *     private String aLibraryName = null;
     *     private String aLibraryAddress = null;
     *     private String aContactPerson = null;
     *     private String aContactDesignation = null;
     *     private String aContactEmail = null;
     *     private String aLibraryNumber = null;
     */
    public static final String LOAD_LIBRARY_INFO = "SELECT * FROM about";
    public static final String INSERT_LIBRARY_INFO = "INSERT INTO about (aLibraryName, aLibraryAddress, aContactPerson, aContactDesignation, aContactEmail, aLibraryNumber) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_LIBRARY_INFO = "UPDATE about SET aLibraryName = ?, aLibraryAddress = ?, aContactPerson = ?, aContactDesignation = ?, aContactEmail = ?, aLibraryNumber = ? WHERE aInfoID = ?";
}
