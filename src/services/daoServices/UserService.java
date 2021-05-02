package services.daoServices;

import bean.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;
import util.dbConnection.DBConnection;
import util.querry.UserQuery;
import util.utility.DataEncryption;
import util.utility.userAlerts.AlertPopUp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    public ObservableList<User> loadAllUserData(){
        ObservableList<User> userObservableList = FXCollections.observableArrayList();
        Connection conn = DBConnection.getDBConnection();

        try{
            ResultSet resultSet = conn.createStatement().executeQuery(UserQuery.LOAD_ALL_USER_DATA);
            while(resultSet.next()){
                userObservableList.add(new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
            }

        }catch(SQLException sqlException){
            AlertPopUp.sqlQueryError(sqlException);
        }
        return userObservableList;
    }

    public User loadSpecificUser(String email){
        User user = null;
        Connection conn = DBConnection.getDBConnection();

        try{
            PreparedStatement preparedStatement = conn.prepareStatement(UserQuery.LOAD_SPECIFIC_USER_DATA);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                user = new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            }

        }catch(SQLException exception){
            AlertPopUp.sqlQueryError(exception);
        }catch(NullPointerException exception){
            AlertPopUp.connectionError(exception);
        }
        return user;
    }

    public boolean insertUserData(User user){
        Connection conn = DBConnection.getDBConnection();
        PreparedStatement psUser = null;
        try {

            psUser = conn.prepareStatement(UserQuery.INSERT_USER_DATA);

            psUser.setString(1, user.getuEmmail());
            psUser.setString(2, user.getuName());
            psUser.setString(3, DataEncryption.passwordEncryption(user.getuPassword()));
            psUser.setString(4, user.getuType());
            psUser.execute();
            return true;

        } catch (SQLException ex) {
            AlertPopUp.sqlQueryError(ex);
            return false;
        }
    }
    public boolean updateUserData(User user){
        Connection conn = DBConnection.getDBConnection();
        PreparedStatement psUser = null;
        try {

            psUser = conn.prepareStatement(UserQuery.UPDATE_USER_DATA);

            psUser.setString(1, user.getuName());
            psUser.setString(2, user.getuType());
            psUser.setString(3, user.getuEmmail());
            psUser.execute();
            return true;

        } catch (SQLException ex) {
            AlertPopUp.sqlQueryError(ex);
            return false;
        }
    }

    public boolean updateUserPassword(User user){
        Connection conn = DBConnection.getDBConnection();
        PreparedStatement psUser = null;
        try {

            psUser = conn.prepareStatement(UserQuery.UPDATE_USER_PASSWORD);

            psUser.setString(1, DataEncryption.passwordEncryption(user.getuPassword()));
            psUser.setString(2, user.getuEmmail());
            psUser.execute();
            return true;

        } catch (SQLException ex) {
            AlertPopUp.sqlQueryError(ex);
            return false;
        }
    }

    public boolean deleteUser(String email){
        Connection conn = DBConnection.getDBConnection();
        PreparedStatement psUser = null;
        try {

            psUser = conn.prepareStatement(UserQuery.DELETE_USER_DATA);
            psUser.setString(1, email);
            psUser.execute();
            return true;

        } catch (SQLException ex) {
            AlertPopUp.sqlQueryError(ex);
            return false;
        }
    }

    public SortedList<User> searchTable(TextField searchTextField){
        //Retreiving all data from database
        ObservableList<User> userData = loadAllUserData();
        //Wrap the ObservableList in a filtered List (initially display all data)
        FilteredList<User> filteredData = new FilteredList<>(userData, b -> true);

        searchTextField.textProperty().addListener((observable,oldValue,newValue) ->{
            filteredData.setPredicate(user -> {
                //if filter text is empty display all data
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                //comparing search text with table columns one by one
                String lowerCaseFilter = newValue.toLowerCase();

                if(user.getuName().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    //return if filter matches data
                    return true;
                }else if(user.getuEmmail().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    //return if filter matches data
                    return true;
                }else if(user.getuType().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    //return if filter matches data
                    return true;
                }else{
                    //have no matchings
                    return false;
                }
            });
        });
        //wrapping the FilteredList in a SortedList
        SortedList<User> sortedData = new SortedList<>(filteredData);
        return sortedData;
    }

}
