package services.daoServices;

import bean.About;
import util.dbConnection.DBConnection;
import util.querry.AboutQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AboutService {
    private Connection connection = DBConnection.getDBConnection();

    public About getLibraryInformation(){
        About about = null;
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(AboutQuery.LOAD_LIBRARY_INFO);
            while(resultSet.next()){
                about = new About(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return about;
    }

    public boolean insertLibraryInformation(About about){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(AboutQuery.INSERT_LIBRARY_INFO);
            preparedStatement.setString(1, about.getaLibraryName());
            preparedStatement.setString(2, about.getaLibraryAddress());
            preparedStatement.setString(3, about.getaContactPerson());
            preparedStatement.setString(4,about.getaContactDesignation());
            preparedStatement.setString(5, about.getaContactEmail());
            preparedStatement.setString(6, about.getaLibraryNumber());
            preparedStatement.execute();

            return true;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }

    }

    public boolean updateLibraryInformation(About about){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(AboutQuery.UPDATE_LIBRARY_INFO);
            preparedStatement.setString(1, about.getaLibraryName());
            preparedStatement.setString(2, about.getaLibraryAddress());
            preparedStatement.setString(3, about.getaContactPerson());
            preparedStatement.setString(4,about.getaContactDesignation());
            preparedStatement.setString(5, about.getaContactEmail());
            preparedStatement.setString(6, about.getaLibraryNumber());
            preparedStatement.setInt(7, about.getaInfoID());
            preparedStatement.execute();

            return true;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }

    }
}
