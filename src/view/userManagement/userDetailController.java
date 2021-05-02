package view.userManagement;

import bean.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.daoServices.UserService;
import util.utility.userAlerts.AlertPopUp;
import util.validation.DataValidation;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class userDetailController implements Initializable {

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, String> userNameColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, String> passwordColumn;

    @FXML
    private TableColumn<User, String> userTypeColumn;

    @FXML
    private ComboBox<String> actionTypeComboBox;

    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private ChoiceBox<String> userTypeChoiceBox;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label userNameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label userTypeLabel;

    @FXML
    private Label actionTypeLabel;

    @FXML
    private Label passwordValidationLabel;

    @FXML
    private TextField SearchTextField;

    @FXML
    private Button updateButton;

    @FXML
    private Button addButton;

    private User selectedUser = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setComponentData();
        loadData();
        searchTable();
    }

    private void loadData() {
        UserService userService = new UserService();
        ObservableList<User> userObservableList = userService.loadAllUserData();

        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("uName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("uEmmail"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("uPassword"));
        userTypeColumn.setCellValueFactory(new PropertyValueFactory<>("uType"));

        userTable.setItems(null);
        userTable.setItems(userObservableList);
    }

    @FXML
    void addUser(ActionEvent event) {
        if(userValidation()){
            User user = new User();
            UserService userService = new UserService();

            user.setuName(userNameTextField.getText());
            user.setuEmmail(emailTextField.getText());
            user.setuPassword(passwordField.getText());
            user.setuType(userTypeChoiceBox.getValue());

            if(userService.insertUserData(user)) {
                AlertPopUp.insertSuccesfully("User Added Successfully");
                loadData();
                clearFields(event);
            }else
                AlertPopUp.insertionFailed("Failed to Add User");

        }else
            dataValidationMessage();
    }

    @FXML
    void updateUser(ActionEvent event) {
        if(userValidation()){
            User user = new User();
            UserService userService = new UserService();

            user.setuName(userNameTextField.getText());
            user.setuEmmail(emailTextField.getText());
            user.setuPassword(passwordField.getText());
            user.setuType(userTypeChoiceBox.getValue());

            if(actionTypeComboBox.getValue().equals("Update User Info")){
                if(userService.updateUserData(user)) {
                    AlertPopUp.updateSuccesfully("User");
                    loadData();
                    clearFields(event);
                }else
                    AlertPopUp.updateFailed("User");
            }else {
                if(userService.updateUserPassword(user)) {
                    AlertPopUp.updateSuccesfully("User Password");
                    loadData();
                    clearFields(event);
                }else
                    AlertPopUp.updateFailed("User Password");
            }

        }else
            dataValidationMessage();
    }

    @FXML
    void deleteUser(ActionEvent event) {
        if(selectedUser.getuEmmail() != null){
            Optional<ButtonType> action = AlertPopUp.deleteConfirmation("User");
            if(action.get() == ButtonType.OK){
                UserService userService = new UserService();
                if(userService.deleteUser(selectedUser.getuEmmail())){
                    AlertPopUp.deleteSuccesfull("User");
                    loadData();
                    clearFields(event);
                    clearLabels();
                }else{
                    AlertPopUp.deleteFailed("User");
                }
            }
        }
    }

    @FXML
    void setSelectedUserDataToFields(MouseEvent event) {
        try{
            selectedUser = userTable.getSelectionModel().getSelectedItem();
            userNameTextField.setText(selectedUser.getuName());
            emailTextField.setText(selectedUser.getuEmmail());
            userTypeChoiceBox.setValue(selectedUser.getuType());
            emailTextField.setEditable(false);
        }catch (NullPointerException exception){

        }
    }

    @FXML
    void clearFields(ActionEvent event) {
        userNameTextField.setText("");
        emailTextField.setText("");
        userTypeChoiceBox.setValue("");
        passwordField.setText("");
        confirmPasswordField.setText("");
        clearLabels();
    }

    private void clearLabels() {
        userNameLabel.setText("");
        emailLabel.setText("");
        passwordValidationLabel.setText("");
        userTypeLabel.setText("");
        actionTypeLabel.setText("");
    }

    private void setComponentData() {
        ObservableList<String> actionList = FXCollections.observableArrayList("Add User Info", "Update User Info", "Update Password");
        ObservableList<String> userTypeList = FXCollections.observableArrayList("Admin", "Guest");

        userTypeChoiceBox.setValue("Admin");
        userTypeChoiceBox.setItems(userTypeList);

        actionTypeComboBox.setValue("Add User Info");
        actionTypeComboBox.setItems(actionList);
    }

    private boolean userValidation() {
        if (actionTypeComboBox.getValue().equals("Add User Info")) {
            return DataValidation.TextFieldNotEmpty(userNameTextField.getText())
                    && DataValidation.TextFieldNotEmpty(emailTextField.getText())
                    && DataValidation.TextFieldNotEmpty(passwordField.getText())
                    && DataValidation.TextFieldNotEmpty(confirmPasswordField.getText())

                    && DataValidation.isValidMaximumLength(userNameTextField.getText(), 45)
                    && DataValidation.isValidMaximumLength(emailTextField.getText(), 45)
                    && DataValidation.isValidMaximumLength(passwordField.getText(), 45)
                    && DataValidation.isValidMaximumLength(confirmPasswordField.getText(), 45)

                    && DataValidation.isValidEmail(emailTextField.getText())
                    && DataValidation.PasswordFieldMatch(passwordField, confirmPasswordField)
                    && checkUserNameAvailability();
        } else if (actionTypeComboBox.getValue().equals("Update User Info")) {
            return DataValidation.TextFieldNotEmpty(userNameTextField.getText())
                    && DataValidation.TextFieldNotEmpty(emailTextField.getText())

                    && DataValidation.isValidMaximumLength(userNameTextField.getText(), 45)
                    && DataValidation.isValidMaximumLength(emailTextField.getText(), 45)

                    && DataValidation.isValidEmail(emailTextField.getText());
        } else {
            return DataValidation.TextFieldNotEmpty(passwordField.getText())
                    && DataValidation.TextFieldNotEmpty(confirmPasswordField.getText())

                    && DataValidation.isValidMaximumLength(passwordField.getText(), 45)
                    && DataValidation.isValidMaximumLength(confirmPasswordField.getText(), 45)

                    && DataValidation.PasswordFieldMatch(passwordField, confirmPasswordField);
        }
    }

    private void dataValidationMessage() {
        if (actionTypeComboBox.getValue().equals("Add User Info")) {
            if (!(DataValidation.TextFieldNotEmpty(userNameTextField.getText())
                    && DataValidation.TextFieldNotEmpty(emailTextField.getText())
                    && DataValidation.TextFieldNotEmpty(passwordField.getText())
                    && DataValidation.TextFieldNotEmpty(confirmPasswordField.getText()))
                    && checkUserNameAvailability()) {
                DataValidation.TextFieldNotEmpty(userNameTextField.getText(), userNameLabel,"User Name cannot be Empty");
                         DataValidation.TextFieldNotEmpty(emailTextField.getText(), emailLabel, "Email Field cannot be Empty");
                         DataValidation.TextFieldNotEmpty(confirmPasswordField.getText(), passwordValidationLabel, "Confirm Password Required");
                         DataValidation.TextFieldNotEmpty(passwordField.getText(), passwordValidationLabel, "Password Required");
            }
            if (!(DataValidation.isValidMaximumLength(userNameTextField.getText(), 45)
                    && DataValidation.isValidMaximumLength(emailTextField.getText(), 45)
                    && DataValidation.isValidMaximumLength(passwordField.getText(), 45)
                    && DataValidation.isValidMaximumLength(confirmPasswordField.getText(), 45))) {

                DataValidation.isValidMaximumLength(userNameTextField.getText(), 45, userNameLabel, "Maximum limit 45 Exceeded");
                         DataValidation.isValidMaximumLength(emailTextField.getText(), 45, emailLabel, "Maximum limit 45 Exceeded");
                         DataValidation.isValidMaximumLength(passwordField.getText(), 40, passwordValidationLabel, "Maximum limit 40 Exceeded");
                         DataValidation.isValidMaximumLength(confirmPasswordField.getText(), 40, passwordValidationLabel, "Maximum limit 40 Exceeded");
            }
            if (!(DataValidation.isValidEmail(emailTextField.getText())
                    && DataValidation.PasswordFieldMatch(passwordField, confirmPasswordField))) {
                DataValidation.isValidEmail(emailTextField.getText(), emailLabel, "Invalid Email Address!");
                DataValidation.PasswordFieldMatch(passwordField, confirmPasswordField, passwordValidationLabel, passwordValidationLabel,"Invalid Password Combination");

            }
        } else if (actionTypeComboBox.getValue().equals("Update User Info")) {
            if (!(DataValidation.TextFieldNotEmpty(userNameTextField.getText())
                    && DataValidation.TextFieldNotEmpty(emailTextField.getText()))) {
                DataValidation.TextFieldNotEmpty(userNameTextField.getText(), userNameLabel, "User Name cannot be Empty");
                         DataValidation.TextFieldNotEmpty(emailTextField.getText(), emailLabel, "Email Field cannot be Empty");
            }
            if (!(DataValidation.isValidMaximumLength(userNameTextField.getText(), 45)
                    && DataValidation.isValidMaximumLength(emailTextField.getText(), 45))) {
                DataValidation.isValidMaximumLength(userNameTextField.getText(), 45, userNameLabel, "Maximum limit 45 Exceeded");
                         DataValidation.isValidMaximumLength(emailTextField.getText(), 45, emailLabel, "Maximum limit 45 Exceeded");
            }
            if (!( DataValidation.isValidEmail(emailTextField.getText()))) {
                DataValidation.isValidEmail(emailTextField.getText(), emailLabel, "Invalid Email Address!");
            }
        } else {
            if (!(DataValidation.TextFieldNotEmpty(passwordField.getText())
                    && DataValidation.TextFieldNotEmpty(confirmPasswordField.getText()))) {
                DataValidation.TextFieldNotEmpty(confirmPasswordField.getText(), passwordValidationLabel, "Confirm Password Required");
                DataValidation.TextFieldNotEmpty(passwordField.getText(), passwordValidationLabel, "Password Required");
            }
            if (!(DataValidation.isValidMaximumLength(passwordField.getText(), 45)
                    && DataValidation.isValidMaximumLength(confirmPasswordField.getText(), 45))) {
                DataValidation.isValidMaximumLength(passwordField.getText(), 45, passwordValidationLabel, "Maximum limit 40 Exceeded");
                         DataValidation.isValidMaximumLength(confirmPasswordField.getText(), 45, passwordValidationLabel, "Maximum limit 40 Exceeded");
            }
            if (!DataValidation.PasswordFieldMatch(passwordField, confirmPasswordField)) {
                DataValidation.PasswordFieldMatch(passwordField, confirmPasswordField, passwordValidationLabel, passwordValidationLabel, "Invalid Password Combination");
            }
        }
    }
    @FXML
    private boolean checkUserNameAvailability(){
        ObservableList<User> modelList = userTable.getItems();
        ArrayList<String> userList = new ArrayList<>();
        for(User user: modelList){
            userList.add(user.getuEmmail().toLowerCase());
        }
        if(emailTextField.getText().isEmpty()){
            emailLabel.setStyle("-fx-text-fill: #ff0000 ");
            emailLabel.setText("Email Cannot be empty");
            return false;
        } else if(userList.contains(emailTextField.getText().toLowerCase())){
            emailLabel.setStyle("-fx-text-fill: #ff0000 ");
            emailLabel.setText("Email Already Taken!!");
            return false;
        }else {
            emailLabel.setStyle("-fx-text-fill: #00B605 ");
            emailLabel.setText("Email Available");
            return true;
        }
    }

    @FXML
    private void setActionType(){
        clearLabels();
        if (actionTypeComboBox.getValue().equals("Add User Info")){
            resetComponentEditable();
            emailTextField.setEditable(true);
        }else if(actionTypeComboBox.getValue().equals("Update User Info")){
            resetComponentEditable();
            addButton.setVisible(false);
            passwordField.setEditable(false);
            confirmPasswordField.setEditable(false);
            emailTextField.setEditable(false);
        }else{
            resetComponentEditable();
            addButton.setVisible(false);
            userNameTextField.setEditable(false);
            emailTextField.setEditable(false);
            userTypeChoiceBox.setDisable(true);
            emailTextField.setEditable(false);
        }
    }

    private void resetComponentEditable(){
        addButton.setVisible(true);
        userNameTextField.setEditable(true);
        emailTextField.setEditable(true);
        userTypeChoiceBox.setDisable(false);
        passwordField.setEditable(true);
        confirmPasswordField.setEditable(true);
    }

    public void searchTable(){
        UserService userService = new UserService();
        SortedList<User> sortedData = userService.searchTable(SearchTextField);
        //binding the SortedList to TableView
        sortedData.comparatorProperty().bind(userTable.comparatorProperty());
        //adding sorted and filtered data to the table
        userTable.setItems(sortedData);
    }

}
