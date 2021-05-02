package services.daoServices;

import bean.Feedback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.dbConnection.DBConnection;
import util.querry.FeedbackQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackService {
    private Connection connection = DBConnection.getDBConnection();

    public ObservableList<Feedback> loadAllFeedbacks() {
        ObservableList<Feedback> feedbackObservableList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(FeedbackQuery.LOAD_ALL_FEEDBACK_DATA);
            while (resultSet.next()) {
                feedbackObservableList.add(new Feedback(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7)));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return feedbackObservableList;
    }

    public Feedback loadSpecificFeedback(Integer id){
        Feedback feedback = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FeedbackQuery.LOAD_SPECIFIC_FEEDBACK_DATA);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               feedback = new Feedback(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return feedback;
    }

    public boolean insertFeedback(Feedback feedback){
        boolean resultValue = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FeedbackQuery.INSERT_FEEDBACK_DATA);
            preparedStatement.setString(1, feedback.getfCategory());
            preparedStatement.setString(2, feedback.getfTitle());
            preparedStatement.setString(3, feedback.getfDescription());
            preparedStatement.setString(4, feedback.getfDate());
            preparedStatement.setString(5, feedback.getfTime());
            preparedStatement.setString(6, feedback.getfStatus());
            preparedStatement.execute();
            resultValue = true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return resultValue;
    }

    public boolean updateFeedbackStatus(Feedback feedback){
        boolean resultValue = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FeedbackQuery.UPDATE_FEEDBACK_STATUS);
            preparedStatement.setString(1, feedback.getfStatus());
            preparedStatement.setInt(2, feedback.getfID());
            preparedStatement.execute();
            resultValue = true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return resultValue;
    }

}
