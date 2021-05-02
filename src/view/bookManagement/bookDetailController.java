package view.bookManagement;

import bean.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.daoServices.BookService;
import services.daoServices.UserService;
import util.utility.userAlerts.AlertPopUp;
import util.validation.DataValidation;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class bookDetailController implements Initializable {
    @FXML
    private TextField searchTextField;

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, Integer> bIDColumn;

    @FXML
    private TableColumn<Book, String> isbnColumn;

    @FXML
    private TableColumn<Book, String> bookNameColumn;

    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TableColumn<Book, String> availabilityColumn;

    @FXML
    private TableColumn<Book, String> arrivalColumn;

    @FXML
    private TextField isbnTextField;

    @FXML
    private TextField bookNameTextField;

    @FXML
    private TextField authorTextField;

    @FXML
    private ChoiceBox<String> availableStatusChoiceBox;

    @FXML
    private Label isbnLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private Label bookNameLabel;

    @FXML
    private Label availableStatusLabel;

    @FXML
    private ChoiceBox<String> arrivalStatusChoiceBox;

    @FXML
    private Label arrivalStatusLabel;

    private ObservableList<String> availableChoiceBoxList = FXCollections.observableArrayList("Available", "Not Available");

    private ObservableList<String> arrivalStatusChoiceBoxList = FXCollections.observableArrayList("New Arrival", "Old Arrival");

    private static Book selectedBook;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
        searchTable();
        availableStatusChoiceBox.setValue("Available");
        availableStatusChoiceBox.setItems(availableChoiceBoxList);
        arrivalStatusChoiceBox.setValue("New Arrival");
        arrivalStatusChoiceBox.setItems(arrivalStatusChoiceBoxList);
    }

    private void loadData(){
        BookService bookService = new BookService();
        ObservableList<Book> bookObservableList = bookService.loadAllData();

        bIDColumn.setCellValueFactory( new PropertyValueFactory<>("bID"));
        isbnColumn.setCellValueFactory( new PropertyValueFactory<>("bISBN"));
        bookNameColumn.setCellValueFactory( new PropertyValueFactory<>("bName"));
        authorColumn.setCellValueFactory( new PropertyValueFactory<>("bAuthor"));
        availabilityColumn.setCellValueFactory( new PropertyValueFactory<>("bAvailableStatus"));
        arrivalColumn.setCellValueFactory( new PropertyValueFactory<>("bArrivalStatus"));

        bookTable.setItems(null);
        bookTable.setItems(bookObservableList);
    }

    @FXML
    private void setSelectedDataToFields(MouseEvent event) {
        try{
            selectedBook = bookTable.getSelectionModel().getSelectedItem();
            isbnTextField.setText(selectedBook.getbISBN());
            bookNameTextField.setText(selectedBook.getbName());
            authorTextField.setText(selectedBook.getbAuthor());
            availableStatusChoiceBox.setValue(selectedBook.getbAvailableStatus());
            arrivalStatusChoiceBox.setValue(selectedBook.getbArrivalStatus());
        }catch(Exception exception){
            AlertPopUp.generalError(exception);
        }

    }

    @FXML
    private void addBook(ActionEvent event) {
        clearLabels();
        if(validateData()){
            Book book = new Book();
            BookService bookService = new BookService();

            book.setbISBN(isbnTextField.getText());
            book.setbName(bookNameTextField.getText());
            book.setbAuthor(authorTextField.getText());
            book.setbAvailableStatus(availableStatusChoiceBox.getValue());
            book.setbArrivalStatus(arrivalStatusChoiceBox.getValue());

            if(bookService.insertData(book)) {
                AlertPopUp.insertSuccesfully("Book info");
                clearFields();
                loadData();
            }
            else
                AlertPopUp.insertionFailed("Book Info");
        }else
            validationMessage();
    }

    @FXML
    private void updateBook(ActionEvent event) {
        clearLabels();
        if(validateData()){
            Book book = new Book();
            BookService bookService = new BookService();

            book.setbID(selectedBook.getbID());
            book.setbISBN(isbnTextField.getText());
            book.setbName(bookNameTextField.getText());
            book.setbAuthor(authorTextField.getText());
            book.setbAvailableStatus(availableStatusChoiceBox.getValue());
            book.setbArrivalStatus(arrivalStatusChoiceBox.getValue());

            if(bookService.updateBook(book)) {
                AlertPopUp.updateSuccesfully("Book info");
                clearFields();
                loadData();
            }
            else
                AlertPopUp.updateFailed("Book Info");
        }else
            validationMessage();
    }
    @FXML
    private void deleteBook(ActionEvent event) {
        if(selectedBook.getbID() != null){
            Optional<ButtonType> action = AlertPopUp.deleteConfirmation("Book");
            if(action.get() == ButtonType.OK){
                BookService bookService = new BookService();
                if(bookService.deleteBook(selectedBook.getbID())){
                    AlertPopUp.deleteSuccesfull("Book");
                    loadData();
                    clearFields();
                    clearLabels();
                }else{
                    AlertPopUp.deleteFailed("Book");
                }
            }
        }
    }

    @FXML
    private void clearFields() {
        isbnTextField.setText("");
        bookNameTextField.setText("");
        authorTextField.setText("");
    }

    private void clearLabels(){
        isbnLabel.setText("");
        bookNameLabel.setText("");
        authorLabel.setText("");
        arrivalStatusLabel.setText("");
        availableStatusLabel.setText("");
    }

    private boolean validateData() {
        return DataValidation.TextFieldNotEmpty(isbnTextField.getText())
                && DataValidation.TextFieldNotEmpty(bookNameTextField.getText())
                && DataValidation.TextFieldNotEmpty(authorTextField.getText())
                && DataValidation.TextFieldNotEmpty(availableStatusChoiceBox.getValue())
                && DataValidation.TextFieldNotEmpty(arrivalStatusChoiceBox.getValue())

                && DataValidation.isValidMaximumLength(isbnTextField.getText(), 13)
                && DataValidation.isValidMaximumLength(bookNameTextField.getText(), 45)
                && DataValidation.isValidMaximumLength(authorTextField.getText(), 45);
    }

    private void validationMessage() {
        if (DataValidation.TextFieldNotEmpty(isbnTextField.getText())
                && DataValidation.TextFieldNotEmpty(bookNameTextField.getText())
                && DataValidation.TextFieldNotEmpty(authorTextField.getText())
                && DataValidation.TextFieldNotEmpty(availableStatusChoiceBox.getValue())
                && DataValidation.TextFieldNotEmpty(arrivalStatusChoiceBox.getValue())) {
            DataValidation.TextFieldNotEmpty(isbnTextField.getText(), isbnLabel, "ISBN Field Cannot be Empty!");
            DataValidation.TextFieldNotEmpty(bookNameTextField.getText(), bookNameLabel, "Book Name Cannot be Empty!");
            DataValidation.TextFieldNotEmpty(authorTextField.getText(), authorLabel, "Author Cannot be Empty!");
            DataValidation.TextFieldNotEmpty(availableStatusChoiceBox.getValue(), availableStatusLabel, "Availability Status Cannot be Empty!");
            DataValidation.TextFieldNotEmpty(arrivalStatusChoiceBox.getValue(), arrivalStatusLabel, "Arrival Status Cannot be Empty!");
        }
        if (DataValidation.isValidMaximumLength(isbnTextField.getText(), 13)
                && DataValidation.isValidMaximumLength(bookNameTextField.getText(), 45)
                && DataValidation.isValidMaximumLength(authorTextField.getText(), 45)) {
            DataValidation.isValidMaximumLength(isbnTextField.getText(), 13, isbnLabel, "Field Length exceeded, Limit 13");
            DataValidation.isValidMaximumLength(bookNameTextField.getText(), 45, bookNameLabel, "Field Length exceeded, Limit 45");
            DataValidation.isValidMaximumLength(authorTextField.getText(), 45, authorLabel, "Field Length exceeded, Limit 45");
        }
    }

    public void searchTable(){

        BookService bookService = new BookService();

        SortedList<Book> sortedData = bookService.searchTable(searchTextField);

        //binding the SortedList to TableView
        sortedData.comparatorProperty().bind(bookTable.comparatorProperty());
        //adding sorted and filtered data to the table
        bookTable.setItems(sortedData);
    }
}
