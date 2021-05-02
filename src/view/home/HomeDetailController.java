package view.home;

import bean.Book;
import bean.BookView;
import bean.ChartData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import services.daoServices.BookService;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeDetailController implements Initializable {
    @FXML
    private BarChart<String, Integer> viewBarChart;

    @FXML
    private CategoryAxis viewsCategoryAxis;

    @FXML
    private NumberAxis viewsNumberAxis;

    @FXML
    private ComboBox<String> sortDateComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setChartData();
    }
    private void setChartData(){
        BookService bookService = new BookService();
        ObservableList<BookView> bookViewObservableList = bookService.loadAllBookViews();
        ObservableList<Book> bookObservableList = bookService.loadAllData();
        ObservableList<ChartData> unsortedChartDataList = FXCollections.observableArrayList();

        for(Book book: bookObservableList){
            Integer counter = 0;
            for(BookView bookView : bookViewObservableList){
                if(book.getbID().equals(bookView.getViewBookID())){
                    counter ++;
                }
            }
            unsortedChartDataList.add(new ChartData(book.getbName(), counter));
        }
        unsortedChartDataList.sorted();
        viewBarChart.getData().clear();
        XYChart.Series bookSeries = new XYChart.Series<>();
        bookSeries.setName("Book Names");
        for(ChartData chartData : unsortedChartDataList){
            if(chartData.getChartValue() != 0){
                bookSeries.getData().add( new XYChart.Data<>(chartData.getChartCategory(), chartData.getChartValue()));
            }
        }
        viewBarChart.getData().add(bookSeries);
    }
}
