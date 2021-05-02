package bean;

public class ChartData {
    private String chartCategory = null;
    private Integer chartValue = null;
    private String chartDate = null;

    public ChartData() {
    }

    public ChartData(String chartCategory, Integer chartValue) {
        this.chartCategory = chartCategory;
        this.chartValue = chartValue;
    }

    public ChartData(String chartCategory, Integer chartValue, String chartDate) {
        this.chartCategory = chartCategory;
        this.chartValue = chartValue;
        this.chartDate = chartDate;
    }

    public String getChartCategory() {
        return chartCategory;
    }

    public void setChartCategory(String chartCategory) {
        this.chartCategory = chartCategory;
    }

    public Integer getChartValue() {
        return chartValue;
    }

    public void setChartValue(Integer chartValue) {
        this.chartValue = chartValue;
    }

    public String getChartDate() {
        return chartDate;
    }

    public void setChartDate(String chartDate) {
        this.chartDate = chartDate;
    }
}
