package view.home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.Navigation;
import util.authenticate.UserAuthentication;
import util.validation.DataValidation;

public class LoginController {
    @FXML
    private AnchorPane basePane;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label validationLabel;


    private void loadHome(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadHome(basePane);
    }

    @FXML
    public void loginUser(ActionEvent actionEvent){
        if(fieldValidation()){
            String authenticateMessage = UserAuthentication.authenticateUser(emailTextField.getText(), passwordField.getText());
            if(authenticateMessage.equals("success"))
                loadHome(actionEvent);
            else
                validationLabel.setText(authenticateMessage);
        }else
            fieldValidationMessage();
    }
    private boolean fieldValidation(){
        return DataValidation.TextFieldNotEmpty(emailTextField.getText())
                && DataValidation.TextFieldNotEmpty(passwordField.getText());
    }
    private void fieldValidationMessage(){
        if(!(DataValidation.TextFieldNotEmpty(emailTextField.getText())
                && DataValidation.TextFieldNotEmpty(passwordField.getText()))){
            DataValidation.TextFieldNotEmpty(emailTextField.getText(),validationLabel, "User Name/Password Fields Cannot be Empty");
            DataValidation.TextFieldNotEmpty(passwordField.getText(), validationLabel, "User Name/Password Fields Cannot be Empty");
        }
    }
}
