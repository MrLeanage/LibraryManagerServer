package view.feedbackManagement;

import bean.Feedback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.daoServices.FeedbackService;
import util.utility.UtilityMethod;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FeedbackDetailController implements Initializable {
    @FXML
    private TableView<Feedback> feedbackTable;

    @FXML
    private TableColumn<Feedback, String> categoryColumn;

    @FXML
    private TableColumn<Feedback, String> titleColumn;

    @FXML
    private TableColumn<Feedback, String> dateColumn;

    @FXML
    private TableColumn<Feedback, String> statusColumn;

    @FXML
    private TableColumn<Feedback, String> actionColumn;

    @FXML
    private ComboBox<String> sortDateComboBox;

    @FXML
    private ComboBox<String> sortStatusComboBox;

    public static Feedback selectedFeedback;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData();
        loadTableData();

    }

    private void loadTableData() {
        FeedbackService feedbackService = new FeedbackService();

        ObservableList<Feedback> tableData = feedbackService.loadAllFeedbacks();
        ObservableList<Feedback> sortedData = FXCollections.observableArrayList();

        try {
            if (sortDateComboBox.getValue().equals("All Feedbacks")) {
                for (Feedback feedback : tableData) {
                    if (feedback.getfStatus().equals(sortStatusComboBox.getValue())) {
                        sortedData.add(feedback);
                    }
                }
            } else {
                for (Feedback feedback : tableData) {
                    if (feedback.getfStatus().equals(sortStatusComboBox.getValue()) && UtilityMethod.getYearMonth(feedback.getfDate()).equals(sortDateComboBox.getValue())) {
                        sortedData.add(feedback);
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("fCategory"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("fTitle"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("fDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("fStatus"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        Callback<TableColumn<Feedback, String>, TableCell<Feedback, String>> parentCellFactory
                =
                new Callback<TableColumn<Feedback, String>, TableCell<Feedback, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Feedback, String> param) {
                        final TableCell<Feedback, String> cell = new TableCell<Feedback, String>() {

                            final Button button = new Button("View Card");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    button.setOnMouseClicked(event -> {
                                        // student = StudentTable.getSelectionModel().getSelectedItem();
                                        //String sID = student.getsID();
                                    });
                                    button.setOnAction(event -> {
                                        Feedback feedback = getTableView().getItems().get(getIndex());
                                        selectedFeedback = feedback;
                                        FXMLLoader loader = new FXMLLoader();
                                        loader.setLocation(getClass().getResource("feedbackCard.fxml"));
                                        try {
                                            loader.load();
                                        } catch (IOException ex) {
                                            ex.printStackTrace();
                                        }
                                        FeedbackCardController feedbackCardController = loader.getController();

                                        Parent p = loader.getRoot();
                                        Stage stage = new Stage();
                                        stage.setScene(new Scene(p));
                                        stage.setResizable(false);
                                        stage.sizeToScene();

                                        stage.showAndWait();
                                        loadTableData();
                                    });
                                    setGraphic(button);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        actionColumn.setCellFactory(parentCellFactory);

        feedbackTable.setItems(null);
        feedbackTable.setItems(sortedData);
    }

    private void setData() {
        ObservableList<String> yearMonthDuplicateList = FXCollections.observableArrayList();
        try {
            FeedbackService feedbackService = new FeedbackService();
            for (Feedback feedback : feedbackService.loadAllFeedbacks()) {
                yearMonthDuplicateList.add(UtilityMethod.getYearMonth(feedback.getfDate()));
            }
            ObservableList<String> yearMonthList = FXCollections.observableArrayList();


            int listSize = UtilityMethod.removeStringDuplicates(yearMonthDuplicateList).size();
            for (int i = 1; i <= 6 && listSize >= i; i++) {
                yearMonthList.add(UtilityMethod.removeStringDuplicates(yearMonthDuplicateList).get(listSize - 1));
            }
            yearMonthList.add("All Feedbacks");

            sortDateComboBox.setValue(yearMonthList.get(0));
            sortDateComboBox.setItems(yearMonthList);

            ObservableList<String> sortStatus = FXCollections.observableArrayList("Reviewed", "Pending");
            sortStatusComboBox.setValue("Pending");
            sortStatusComboBox.setItems(sortStatus);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    @FXML
    private void sortTableData() {
        loadTableData();
    }
}
