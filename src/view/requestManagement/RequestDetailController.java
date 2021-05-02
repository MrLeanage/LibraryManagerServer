package view.requestManagement;

import bean.Feedback;
import bean.Request;
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
import services.daoServices.RequestService;
import util.utility.UtilityMethod;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RequestDetailController implements Initializable {
    @FXML
    private TableView<Request> requestTable;

    @FXML
    private TableColumn<Request, Integer> idColumn;

    @FXML
    private TableColumn<Request, String> bookNameColumn;

    @FXML
    private TableColumn<Request, String> authorColumn;

    @FXML
    private TableColumn<Request, String> editionColumn;

    @FXML
    private TableColumn<Request, String> statusColumn;

    @FXML
    private TableColumn<Request, String> actionColumn;

    @FXML
    private ComboBox<String> sortStatusComboBox;

    public static Request selectedRequest;

    public static RequestCardController requestCardController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData();
        loadTableData();
    }
    private void loadTableData(){
        RequestService requestService = new RequestService();
        ObservableList<Request> requestObservableList = FXCollections.observableArrayList();

        for(Request request: requestService.loadAllRequest()){
            if(request.getrStatus().equals(sortStatusComboBox.getValue())){
                requestObservableList.add(request);
            }
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<>("rID"));
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("rBName"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("rBAuthor"));
        editionColumn.setCellValueFactory(new PropertyValueFactory<>("rBEdition"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("rStatus"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        Callback<TableColumn<Request, String>, TableCell<Request, String>> parentCellFactory
                =
                new Callback<TableColumn<Request, String>, TableCell<Request, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Request, String> param) {
                        final TableCell<Request, String> cell = new TableCell<Request, String>() {

                            final Button button = new Button("View");


                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    button.setPrefWidth(95.0);
                                    button.setOnMouseClicked(event -> {
                                        // student = StudentTable.getSelectionModel().getSelectedItem();
                                        //String sID = student.getsID();
                                    });
                                    button.setOnAction(event -> {
                                        Request request = getTableView().getItems().get(getIndex());
                                        selectedRequest = request;
                                        FXMLLoader loader = new FXMLLoader();
                                        loader.setLocation(getClass().getResource("requestCard.fxml"));
                                        try {
                                            loader.load();
                                        } catch (IOException ex) {
                                            ex.printStackTrace();
                                        }
                                        requestCardController = loader.getController();

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
        requestTable.setItems(null);
        requestTable.setItems(requestObservableList);
    }
    private void setData() {
        ObservableList<String> sortStatus = FXCollections.observableArrayList("Reviewed", "Pending");
        sortStatusComboBox.setValue("Pending");
        sortStatusComboBox.setItems(sortStatus);
    }
    @FXML
    private void sortTableData() {
        loadTableData();
    }
}
