package view.home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import services.Navigation;
import services.rmiService.RMIServer;
import util.authenticate.UserAuthentication;
import util.utility.MenuBarController;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private AnchorPane basePane;

    @FXML
    private AnchorPane detailPane;

    @FXML
    private Button homeMenuButton;

    @FXML
    private Button bookMenuButton;

    @FXML
    private Button requestMenuButton;

    @FXML
    private Button feedbackMenuButton;

    @FXML
    private Button userMenuButton;

    @FXML
    private Label dateLabel;

    @FXML
    private Label serverStatusLabel;

    @FXML
    private Label userLabel;

    @FXML
    private Button aboutLibraryButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setStageData();
    }
    @FXML
        private void loadHome(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadHome(basePane);
    }

    private void loadHomeDetail(){
        Navigation navigation = new Navigation();
        navigation.loadHomeDetailPane(detailPane);
    }
    @FXML
    private void loadBook(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadBook(detailPane);
    }
    @FXML
    private void loadRequest(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadRequest(detailPane);
    }
    @FXML
    private void loadFeedback(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadFeedback(detailPane);
    }
    @FXML
    private void loadUser(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.loadUser(detailPane);
    }

    @FXML
    void loadAbout(ActionEvent event) {
        Navigation navigation = new Navigation();
        navigation.loadAbout(detailPane);
    }

    @FXML
    private void logout(ActionEvent actionEvent){
        Navigation navigation = new Navigation();
        navigation.logout(basePane);
    }

    @FXML
    private void setSelection(){

        if(homeMenuButton.isPressed()){
            MenuBarController.setMenuNumber(0);
        } else if(bookMenuButton.isPressed()){
            MenuBarController.setMenuNumber(1);
        }else if(requestMenuButton.isPressed()){
            MenuBarController.setMenuNumber(2);
        }else if(feedbackMenuButton.isPressed()){
            MenuBarController.setMenuNumber(3);
        }else if(userMenuButton.isPressed()){
            MenuBarController.setMenuNumber(4);
        }else if(aboutLibraryButton.isPressed()){
            MenuBarController.setMenuNumber(5);
        }
        setStyle();
    }

    public void setStyle(){
        String selectionColor = "-fx-background-color:   #00a8ff; ";
        resetButtons();
        switch (MenuBarController.getMenuNumber()){
            case 0 :
                homeMenuButton.setStyle(selectionColor);
                break;
            case 1 :
                bookMenuButton.setStyle(selectionColor);
                break;
            case 2 :
                requestMenuButton.setStyle(selectionColor);
                break;
            case 3 :
                feedbackMenuButton.setStyle(selectionColor);
                break;
            case 4 :
                userMenuButton.setStyle(selectionColor);
                break;
            case 5 :
                aboutLibraryButton.setStyle(selectionColor);
                break;
            default:
                homeMenuButton.setStyle(selectionColor);

        }
    }
    private void resetButtons(){
        String defaultColor = "-fx-background-color:   #006a9f; ";
        homeMenuButton.setStyle(defaultColor);
        bookMenuButton.setStyle(defaultColor);
        requestMenuButton.setStyle(defaultColor);
        feedbackMenuButton.setStyle(defaultColor);
        userMenuButton.setStyle(defaultColor);
        aboutLibraryButton.setStyle(defaultColor);
    }

    private void setStageData(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        String strDate = formatter.format(date);
        dateLabel.setText(strDate);

        loadHomeDetail();
        userLabel.setText("  "+ UserAuthentication.getAuthenticatedUser().getuName());
        serverStatusLabel.setText(RMIServer.serverStatus);

    }
}
