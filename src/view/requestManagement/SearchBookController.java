package view.requestManagement;

import bean.Book;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.daoServices.BookService;
import util.utility.WebApiHandler;
import util.utility.userAlerts.AlertPopUp;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SearchBookController implements Initializable {
    @FXML
    private JFXButton closeCardButton;

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, String> ISBNColumn;

    @FXML
    private TableColumn<Book, String> titleColumn;

    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TextField searchTextField;

    @FXML
    private TextField isbnTextField;

    @FXML
    private TextField bookNameTextField;

    @FXML
    private TextField authorTextField;

    private static Book selectedBook = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData();
    }

    @FXML
    private void closeCard(ActionEvent event) {
        Stage stage = (Stage) closeCardButton.getScene().getWindow();
        stage.close();
    }


    private void setData(){
        try{
        searchTextField.setText(RequestCardController.request.getrBName());
        }catch(NullPointerException exception){
            exception.printStackTrace();
        }
    }

    @FXML
    private void searchBook(ActionEvent actionEvent){
        bookTable.setItems(null);
        char ch = '+';
        String searchTitle = String.valueOf(searchTextField.getText()).toLowerCase().replace(' ', ch);
        System.out.println(searchTitle);
        List<Book> bookList = null;
        try {
            bookList = WebApiHandler.searchBook(searchTitle);
        } catch (IOException exception) {
            //exception.printStackTrace();
        }
        ObservableList<Book> bookObservableList = FXCollections.observableList(bookList);

        ISBNColumn.setCellValueFactory(new PropertyValueFactory<>("bISBN"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("bName"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("bAuthor"));


        bookTable.setItems(bookObservableList);
    }

    @FXML
    private void setSelectedBookInfo(){
        try{
            selectedBook = bookTable.getSelectionModel().getSelectedItem();
            isbnTextField.setText(selectedBook.getbISBN());
            bookNameTextField.setText(selectedBook.getbName());
            authorTextField.setText(selectedBook.getbAuthor());
        }catch(NullPointerException nullPointerException){

        }
    }

    @FXML
    private void addAsPendingBook(ActionEvent actionEvent){
        if(selectedBook != null){
            selectedBook.setbAvailableStatus("Not Available");
            selectedBook.setbArrivalStatus("Pending Arrival");
            BookService bookService = new BookService();
            if(bookService.insertData(selectedBook)){
                AlertPopUp.insertSuccesfully("Book Info");
                RequestDetailController.requestCardController.setReviewed();
                closeCard(actionEvent);
            }else
                AlertPopUp.insertionFailed("Failed to insert Book Info");
        }
    }

}
