package view.home;

import bean.About;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.daoServices.AboutService;
import util.utility.userAlerts.AlertPopUp;
import util.validation.DataValidation;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutDetailController implements Initializable {

    @FXML
    private TextField libraryNameTextField;

    @FXML
    private TextField contactNumberTextField;

    @FXML
    private TextField contactPersonTextField;

    @FXML
    private TextField contactDesignationTextField;

    @FXML
    private TextField conatctEmailTextField;

    @FXML
    private TextArea libraryAddressTextField;

    @FXML
    private Label libraryName;

    @FXML
    private Label libraryAddressLabel;

    @FXML
    private Label contactDesignationLabel;

    @FXML
    private Label contactEmailLabel;

    @FXML
    private Label contactNumberLabel;

    @FXML
    private Label contactPersonLabel;

    private static About aboutInfo = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData();
    }

    private void setData(){
        AboutService aboutService = new AboutService();
        aboutInfo = aboutService.getLibraryInformation();
        if(aboutInfo != null){
            libraryNameTextField.setText(aboutInfo.getaLibraryName());
            libraryAddressTextField.setText(aboutInfo.getaLibraryAddress());
            contactPersonTextField.setText(aboutInfo.getaContactPerson());
            contactDesignationTextField.setText(aboutInfo.getaContactDesignation());
            conatctEmailTextField.setText(aboutInfo.getaContactEmail());
            contactNumberTextField.setText(aboutInfo.getaLibraryNumber());
        }
    }
    @FXML
    void rollBackChanges(ActionEvent event) {
        setData();
    }

    @FXML
    void updateInformation(ActionEvent event) {
        clearLabels();
        if(validateFields()){
            About about = new About();

            about.setaLibraryName(libraryNameTextField.getText());
            about.setaLibraryAddress(libraryAddressTextField.getText());
            about.setaContactPerson(contactPersonTextField.getText());
            about.setaContactDesignation(contactDesignationTextField.getText());
            about.setaContactEmail(conatctEmailTextField.getText());
            about.setaLibraryNumber(contactNumberTextField.getText());

            AboutService aboutService = new AboutService();
            if(aboutInfo == null){
                if(aboutService.insertLibraryInformation(about)){
                    AlertPopUp.insertSuccesfully("Library Information");
                    clearFields();
                    setData();
                }else
                    AlertPopUp.insertionFailed("Library Information Insertion failed!");

            }else{
                about.setaInfoID(aboutInfo.getaInfoID());
                if(aboutService.updateLibraryInformation(about)){
                    AlertPopUp.updateSuccesfully("Library Information");
                    clearFields();
                    setData();
                }else
                    AlertPopUp.updateFailed("Library Information");
            }

        }else{
            validateMessage();
        }
    }

    private void clearFields(){
        libraryNameTextField.setText("");
        libraryAddressTextField.setText("");
        contactPersonTextField.setText("");
        contactDesignationTextField.setText("");
        conatctEmailTextField.setText("");
        contactNumberTextField.setText("");

    }

    private void clearLabels(){
        libraryName.setText("");
        libraryAddressLabel.setText("");
        contactPersonLabel.setText("");
        contactDesignationLabel.setText("");
        contactEmailLabel.setText("");
        contactNumberLabel.setText("");
    }

    private boolean validateFields(){
        return DataValidation.TextFieldNotEmpty(libraryNameTextField)
                && DataValidation.TextAreaNotEmpty(libraryAddressTextField)
                && DataValidation.TextFieldNotEmpty(contactPersonTextField)
                && DataValidation.TextFieldNotEmpty(contactDesignationTextField)
                && DataValidation.TextFieldNotEmpty(conatctEmailTextField)
                && DataValidation.TextFieldNotEmpty(contactNumberTextField)

                && DataValidation.isValidMaximumLength(libraryNameTextField.getText(),45)
                && DataValidation.isValidMaximumLength(libraryAddressTextField.getText(),400)
                && DataValidation.isValidMaximumLength(contactPersonTextField.getText(),45)
                && DataValidation.isValidMaximumLength(contactDesignationTextField.getText(),45)
                && DataValidation.isValidMaximumLength(conatctEmailTextField.getText(),45)
                && DataValidation.isValidMaximumLength(contactNumberTextField.getText(),20)

                && DataValidation.isValidEmail(conatctEmailTextField.getText());
    }

    private void validateMessage(){
        if(!(DataValidation.TextFieldNotEmpty(libraryNameTextField)
                && DataValidation.TextAreaNotEmpty(libraryAddressTextField)
                && DataValidation.TextFieldNotEmpty(contactPersonTextField)
                && DataValidation.TextFieldNotEmpty(contactDesignationTextField)
                && DataValidation.TextFieldNotEmpty(conatctEmailTextField)
                && DataValidation.TextFieldNotEmpty(contactNumberTextField))){
            DataValidation.TextFieldNotEmpty(libraryNameTextField,libraryName, "Library Name Required!");
                     DataValidation.TextAreaNotEmpty(libraryAddressTextField, libraryAddressLabel, "Library Address Required!");
                     DataValidation.TextFieldNotEmpty(contactPersonTextField, contactPersonLabel, "Contact Person Required!");
                     DataValidation.TextFieldNotEmpty(contactDesignationTextField, contactDesignationLabel, "Contact Person Designation Required!");
                     DataValidation.TextFieldNotEmpty(conatctEmailTextField, contactEmailLabel, "Contact Email Required!");
                     DataValidation.TextFieldNotEmpty(contactNumberTextField, contactNumberLabel, "Contact Number Required");
        }
        if(!(DataValidation.isValidMaximumLength(libraryNameTextField.getText(),45)
                && DataValidation.isValidMaximumLength(libraryAddressTextField.getText(),400)
                && DataValidation.isValidMaximumLength(contactPersonTextField.getText(),45)
                && DataValidation.isValidMaximumLength(contactDesignationTextField.getText(),45)
                && DataValidation.isValidMaximumLength(conatctEmailTextField.getText(),45)
                && DataValidation.isValidMaximumLength(contactNumberTextField.getText(),20))){
            DataValidation.isValidMaximumLength(libraryNameTextField.getText(),45, libraryName, "Maximum Character Limit 45 Exceeded!");
                     DataValidation.isValidMaximumLength(libraryAddressTextField.getText(),400, libraryAddressLabel, "Maximum Character Limit 400 Exceeded!");
                     DataValidation.isValidMaximumLength(contactPersonTextField.getText(),45, contactPersonLabel, "Maximum Character Limit 45 Exceeded!");
                     DataValidation.isValidMaximumLength(contactDesignationTextField.getText(),45, contactDesignationLabel, "Maximum Character Limit 45 Exceeded!");
                     DataValidation.isValidMaximumLength(conatctEmailTextField.getText(),45, contactEmailLabel, "Maximum Character Limit 45 Exceeded!");
                     DataValidation.isValidMaximumLength(contactNumberTextField.getText(),20, contactNumberLabel, "Maximum Character Limit 45 Exceeded!");
        }
        if(!(DataValidation.isValidEmail(conatctEmailTextField.getText()))){
            DataValidation.isValidEmail(conatctEmailTextField.getText(), contactNumberLabel, "Invalid Email Address!");
        }
    }



}
