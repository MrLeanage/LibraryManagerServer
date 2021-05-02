package services;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import util.authenticate.UserAuthentication;
import util.userAlert.AlertPopUp;

import java.io.IOException;
import java.util.Optional;

public class Navigation {
    public void loadHome(AnchorPane basePane){

        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/home/home.fxml"));
            basePane.getChildren().setAll(pane);
        }catch(IOException ex){
            System.out.println(ex);
            AlertPopUp.generalError(ex);
        }
    }
    public void loadHomeDetailPane(AnchorPane detailPane){

        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/home/homeDetail.fxml"));
            detailPane.getChildren().setAll(pane);
        }catch(IOException ex){
            AlertPopUp.generalError(ex);
        }
    }
    public void logout(AnchorPane basePane){
        try{
            Optional<ButtonType> action = AlertPopUp.logoutConfirmation();
            if(action.get() == ButtonType.OK){
                UserAuthentication.setAuthenticatedUser(null);
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/home/login.fxml"));
                basePane.getChildren().setAll(pane);
            }
        }catch(IOException ex){
            AlertPopUp.generalError(ex);
        }
    }
    public void loadBook(AnchorPane detailPane){

        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/bookManagement/bookDetail.fxml"));
            detailPane.getChildren().setAll(pane);
        }catch(IOException ex){
            System.out.println(ex);
            AlertPopUp.generalError(ex);
        }
    }
    public void loadRequest(AnchorPane detailPane){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/requestManagement/requestDetail.fxml"));
            detailPane.getChildren().setAll(pane);
        }catch(IOException ex){
            System.out.println(ex);
            AlertPopUp.generalError(ex);
        }
    }
    public void loadFeedback(AnchorPane detailPane){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/feedbackManagement/feedbackDetail.fxml"));
            detailPane.getChildren().setAll(pane);
        }catch(IOException ex){
            System.out.println(ex);
            AlertPopUp.generalError(ex);
        }
    }
    public void loadUser(AnchorPane detailPane){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/userManagement/userDetail.fxml"));
            detailPane.getChildren().setAll(pane);
        }catch(IOException ex){
            System.out.println(ex);
            AlertPopUp.generalError(ex);
        }
    }
    public void loadAbout(AnchorPane detailPane){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/home/aboutDetail.fxml"));
            detailPane.getChildren().setAll(pane);
        }catch(IOException ex){
            System.out.println(ex);
            AlertPopUp.generalError(ex);
        }
    }
}
