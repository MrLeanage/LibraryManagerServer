package view.requestManagement;

import bean.Feedback;
import bean.Request;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.daoServices.FeedbackService;
import services.daoServices.RequestService;
import util.utility.userAlerts.AlertPopUp;
import view.feedbackManagement.FeedbackDetailController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RequestCardController implements Initializable {
    @FXML
    private TextField bookNameTextField;

    @FXML
    private TextField authorTextField;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private TextField editionTextField;

    @FXML
    private TextField publisherTextField;

    @FXML
    private TextField dateTextField;

    @FXML
    private TextField statusTextField;

    @FXML
    private JFXButton closeCardButton;

    @FXML
    private JFXButton reviewedButton;

    @FXML
    private JFXButton pendingButton;

    @FXML
    private JFXButton workRequestButton;

    public static Request request;

    public static Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData();
    }

    @FXML
    public void closeCard(ActionEvent event) {
        stage = (Stage) closeCardButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void changeStatus(ActionEvent event) {
        RequestService requestService = new RequestService();
        Request updateRequest = new Request();
        updateRequest.setrID(request.getrID());

        if(pendingButton.isVisible())
            updateRequest.setrStatus("Pending");

        if(reviewedButton.isVisible())
            updateRequest.setrStatus("Reviewed");



        if(requestService.updateRequestStatus(updateRequest)){
            AlertPopUp.updateSuccesfully("Feedback");
            if(reviewedButton.isVisible()){
                reviewedButton.setVisible(false);
                pendingButton.setVisible(true);
                statusTextField.setText("Reviewed");
            }else{
                reviewedButton.setVisible(true);
                pendingButton.setVisible(false);
                statusTextField.setText("Pending");
            }
        }else
            AlertPopUp.updateFailed("Feedback");

    }


    private void setData(){
        try{
            request = RequestDetailController.selectedRequest;
            bookNameTextField.setText(request.getrBName());
            descriptionTextArea.setText(request.getrDescription());
            authorTextField.setText(request.getrBAuthor());
            editionTextField.setText(request.getrBEdition());
            publisherTextField.setText(request.getrBPublisher());
            dateTextField.setText(request.getrDate());
            statusTextField.setText(request.getrStatus());
            if(request.getrStatus().equals("Pending")){
                reviewedButton.setVisible(true);
                pendingButton.setVisible(false);
                workRequestButton.setVisible(true);
            }else{
                reviewedButton.setVisible(false);
                pendingButton.setVisible(true);
                workRequestButton.setVisible(false);
            }
        }catch(NullPointerException exception){
            exception.printStackTrace();
        }
    }

    @FXML
    private void workOnRequest(ActionEvent actionEvent){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("searchBook.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        SearchBookController searchBookController = loader.getController();

        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.setResizable(false);
        stage.sizeToScene();

        stage.showAndWait();
    }
    public void setReviewed(){
        request.setrStatus("Reviewed");
        setData();
        RequestService requestService = new RequestService();
        requestService.updateRequestStatus(request);
    }

}
