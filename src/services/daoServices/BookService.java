package services.daoServices;

import bean.Book;
import bean.BookView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;
import util.dbConnection.DBConnection;
import util.querry.BookQuery;
import util.utility.userAlerts.AlertPopUp;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class BookService implements Serializable {
    private Connection conn = DBConnection.getDBConnection();
    public ObservableList<Book> loadAllData(){
        ObservableList<Book> bookList = FXCollections.observableArrayList();

        try{
            ResultSet resultSet = conn.createStatement().executeQuery(BookQuery.LOAD_ALL_BOOK_DATA);
            while(resultSet.next()){
                bookList.add(new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
            }

        }catch(SQLException sqlException){
            AlertPopUp.sqlQueryError(sqlException);
        }
        return bookList;
    }

    public boolean insertData(Book book){
        PreparedStatement psRequest = null;
        try {

            psRequest = conn.prepareStatement(BookQuery.INSERT_BOOK_DATA);

            psRequest.setString(1, book.getbISBN());
            psRequest.setString(2, book.getbName());
            psRequest.setString(3, book.getbAuthor());
            psRequest.setString(4, book.getbAvailableStatus());
            psRequest.setString(5, book.getbArrivalStatus());
            psRequest.execute();
            //AlertPopUp.insertSuccesfully("Data");
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            AlertPopUp.sqlQueryError(ex);
            return false;
        }
    }

    public boolean updateBook(Book book){
        try {

            PreparedStatement psRequest = conn.prepareStatement(BookQuery.UPDATE_BOOK_DATA);

            psRequest.setString(1, book.getbISBN());
            psRequest.setString(2, book.getbName());
            psRequest.setString(3, book.getbAuthor());
            psRequest.setString(4, book.getbAvailableStatus());
            psRequest.setString(5, book.getbArrivalStatus());
            psRequest.setInt(6, book.getbID());
            psRequest.execute();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            AlertPopUp.sqlQueryError(ex);
            return false;
        }
    }

    public boolean deleteBook(Integer id){
        try {

            PreparedStatement psRequest = conn.prepareStatement(BookQuery.DELETE_BOOK_DATA);
            psRequest.setInt(1, id);
            psRequest.execute();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            AlertPopUp.sqlQueryError(ex);
            return false;
        }
    }

    public SortedList<Book> searchTable(TextField searchTextField){
        //Retreiving all data from database
        ObservableList<Book> bookData = loadAllData();
        //Wrap the ObservableList in a filtered List (initially display all data)
        FilteredList<Book> filteredData = new FilteredList<>(bookData, b -> true);

        searchTextField.textProperty().addListener((observable,oldValue,newValue) ->{
            filteredData.setPredicate(book -> {
                //if filter text is empty display all data
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                //comparing search text with table columns one by one
                String lowerCaseFilter = newValue.toLowerCase();

                if(String.valueOf(book.getbID()).toLowerCase().indexOf(lowerCaseFilter) != -1){
                    //return if filter matches data
                    return true;
                }else if(book.getbISBN().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    //return if filter matches data
                    return true;
                }else if(book.getbName().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    //return if filter matches data
                    return true;
                }else if(book.getbAuthor().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                    //return if filter matches data
                    return true;
                }else if(book.getbArrivalStatus().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    //return if filter matches data
                    return true;
                }else if(book.getbAvailableStatus().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                    //return if filter matches data
                    return true;
                }else{
                    //have no matchings
                    return false;
                }
            });
        });
        //wrapping the FilteredList in a SortedList
        SortedList<Book> sortedData = new SortedList<>(filteredData);
        return sortedData;
    }

    public ObservableList<BookView> loadAllBookViews(){
        ObservableList<BookView> bookViewObservableList = FXCollections.observableArrayList();
        try {
            ResultSet bookViewResultSet = conn.createStatement().executeQuery(BookQuery.LOAD_ALL_BOOK_VIEW);
            PreparedStatement bookPreparedStatement = conn.prepareStatement(BookQuery.LOAD_SPECIFIC_BOOK_DATA);
            while(bookViewResultSet.next()){
                bookPreparedStatement.setInt(1, bookViewResultSet.getInt(2));
                ResultSet bookResultSet = bookPreparedStatement.executeQuery();
                while(bookResultSet.next()){
                    bookViewObservableList.add(new BookView(bookViewResultSet.getInt(1), bookViewResultSet.getInt(2), bookResultSet.getString(3), bookViewResultSet.getString(3)));
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return bookViewObservableList;
    }
    public boolean addBookView(Integer id){
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(BookQuery.INSERT_BOOK_VIEW);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, String.valueOf(LocalDate.now()));
            preparedStatement.execute();
            return true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }

}
