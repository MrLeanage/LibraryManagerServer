package view.feedbackManagement;

import bean.Feedback;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.daoServices.FeedbackService;
import util.utility.userAlerts.AlertPopUp;

import java.net.URL;
import java.util.ResourceBundle;

public class FeedbackCardController implements Initializable {
    @FXML
    private TextField categoryTextField;

    @FXML
    private TextField titleTetField;

    @FXML
    private TextArea commentTextArea;

    @FXML
    private TextField dateTetField;

    @FXML
    private TextField timeTetField;

    @FXML
    private TextField statusTetField;

    @FXML
    private JFXButton reviewedButton;

    @FXML
    private JFXButton pendingButton;

    @FXML
    private JFXButton closeCardButton;

    private static Feedback feedback;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData();
    }

    @FXML
    private void closeCard(ActionEvent event) {
        Stage stage = (Stage) closeCardButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void changeStatus(ActionEvent event) {
        FeedbackService feedbackService = new FeedbackService();
        Feedback updateFeedback = new Feedback();
        updateFeedback.setfID(feedback.getfID());

        if(pendingButton.isVisible())
            updateFeedback.setfStatus("Pending");

        if(reviewedButton.isVisible())
            updateFeedback.setfStatus("Reviewed");



        if(feedbackService.updateFeedbackStatus(updateFeedback)){
            AlertPopUp.updateSuccesfully("Feedback");
            if(reviewedButton.isVisible()){
                reviewedButton.setVisible(false);
                pendingButton.setVisible(true);
                statusTetField.setText("Reviewed");
            }else{
                reviewedButton.setVisible(true);
                pendingButton.setVisible(false);
                statusTetField.setText("Pending");
            }
        }else
            AlertPopUp.updateFailed("Feedback");

    }


    private void setData(){
        try{
            feedback = FeedbackDetailController.selectedFeedback;
            categoryTextField.setText(feedback.getfCategory());
            titleTetField.setText(feedback.getfTitle());
            commentTextArea.setText(feedback.getfDescription());
            dateTetField.setText(feedback.getfDate());
            timeTetField.setText(feedback.getfTime());
            statusTetField.setText(feedback.getfStatus());
            if(feedback.getfStatus().equals("Pending")){
                reviewedButton.setVisible(true);
                pendingButton.setVisible(false);
            }else{
                reviewedButton.setVisible(false);
                pendingButton.setVisible(true);
            }
        }catch(NullPointerException exception){
            exception.printStackTrace();
        }
    }
}
