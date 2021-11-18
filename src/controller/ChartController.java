package controller;

import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import model.CalculationsModel;
import view.ChartView;

public class ChartController {
    private ChartView chartView = new ChartView();

    public VBox getGroupChart() {
        return chartView.getGroupChart();
    }

    public void clear() {
        chartView.Clear();
    }

    public void updateChart(CalculationsModel calculationsModel) {
        chartView.createPointFor1(calculationsModel.X, calculationsModel.Function1Value);
        chartView.createPointFor2(calculationsModel.X, calculationsModel.Function2Value);

    }
}
