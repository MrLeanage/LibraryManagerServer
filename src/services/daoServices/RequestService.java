package services.daoServices;


import bean.Feedback;
import bean.Request;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.dbConnection.DBConnection;
import util.querry.FeedbackQuery;
import util.querry.RequestQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestService {
    private Connection connection = DBConnection.getDBConnection();

    public ObservableList<Request> loadAllRequest(){
        ObservableList<Request> requestObservableList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(RequestQuery.LOAD_ALL_REQUEST_DATA);
            while(resultSet.next()){
                requestObservableList.add(new Request(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8)));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return requestObservableList;
    }
    public boolean insertRequest(Request request){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(RequestQuery.INSERT_REQUEST_DATA);
            preparedStatement.setString(1, request.getrBName());
            preparedStatement.setString(2, request.getrDescription());
            preparedStatement.setString(3, request.getrBAuthor());
            preparedStatement.setString(4, request.getrBEdition());
            preparedStatement.setString(5, request.getrBPublisher());
            preparedStatement.setString(6, request.getrDate());
            preparedStatement.setString(7, request.getrStatus());
            preparedStatement.execute();
            return true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }
    public boolean updateRequestStatus(Request request){
        boolean resultValue = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(RequestQuery.UPDATE_REQUEST_STATUS);
            preparedStatement.setString(1, request.getrStatus());
            preparedStatement.setInt(2, request.getrID());
            preparedStatement.execute();
            resultValue = true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return resultValue;
    }
}
