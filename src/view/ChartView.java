package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChartView {
    private NumberAxis xAxis = new NumberAxis("X", -5, 5, 0.01);//(start,end,step)
    private NumberAxis yAxis = new NumberAxis("Y", -5, 5, 0.01);

    private LineChart lineChart = new LineChart(xAxis, yAxis);
    private VBox vBox = new VBox();
    private ScrollPane scrollPane = new ScrollPane();
    private ComboBox scaleComboBox = new ComboBox(FXCollections.observableArrayList(0.01, 0.03, 0.05, 0.1, 0.5, 1.0));
    private Label scaleLabel = new Label("Scale");


    private XYChart.Series series1 = new XYChart.Series();
    private XYChart.Series series2 = new XYChart.Series();

    private double mouseOnChartX;
    private double mouseOnChartY;

    public ChartView() {
        Button zoomIn = new Button("zoom in");
        zoomIn.setOnAction(actionEvent -> {
            zoomInChart();
        });

        Button zoomOut = new Button("zoom out");
        zoomOut.setOnAction(actionEvent -> {
            zoomOutChart();
        });

        setChartPressAndDragEvents();

        HBox chartButtons = new HBox(zoomIn, zoomOut);
        chartButtons.setSpacing(5);

        series1.setName("Linear function");
        series2.setName("Function 2");

        lineChart.getData().add(series1);
        lineChart.getData().add(series2);

        scrollPane.setContent(lineChart);
        scrollPane.setPrefSize(600, 400);

        scaleComboBox.getSelectionModel().selectFirst();
        HBox scale = new HBox(scaleLabel, scaleComboBox);
        scale.setSpacing(5);

        vBox.getChildren().addAll(scrollPane, chartButtons, scale);
        vBox.setSpacing(5);

        scaleComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Double>() {

            @Override
            public void changed(ObservableValue<? extends Double> observableValue, Double oldV, Double newV) {
                scaleChanger(newV);
            }
        });


        lineChart.setOnScroll(new EventHandler<ScrollEvent>() {
            public void handle(ScrollEvent event) {
                double deltaY = event.getDeltaY();
                if (deltaY < 0) {
                    zoomOutChart();
                } else {
                    zoomInChart();
                }
            }
        });
    }

    public VBox getGroupChart() {
        return vBox;
    }

    private void setChartPressAndDragEvents() {
        lineChart.setOnMousePressed(mouseEvent -> {
            mouseOnChartX = (double) mouseEvent.getX();
            mouseOnChartY = (double) mouseEvent.getY();
        });

        lineChart.setOnMouseDragged(mouseEvent -> {
            xAxis.setLowerBound((double) xAxis.getLowerBound() + ((double) mouseOnChartX - (double) mouseEvent.getX()) / 100.0);
            xAxis.setUpperBound((double) xAxis.getUpperBound() + ((double) mouseOnChartX - (double) mouseEvent.getX()) / 100.0);
            mouseOnChartX = (double) mouseEvent.getX();

            yAxis.setLowerBound((double) yAxis.getLowerBound() + ((double) mouseEvent.getY() - (double) mouseOnChartY) / 100.0);
            yAxis.setUpperBound((double) yAxis.getUpperBound() + ((double) mouseEvent.getY() - (double) mouseOnChartY) / 100.0);
            mouseOnChartY = (double) mouseEvent.getY();
        });

    }

    public void createPointFor1(Double x, Double y) {
        series1.getData().add(new XYChart.Data(x, y));
    }

    public void createPointFor2(Double x, Double y) {
        series2.getData().add(new XYChart.Data(x, y));
    }

    public void zoomInChart() {
        lineChart.setPrefWidth(lineChart.getWidth() + 10);
        lineChart.setPrefHeight(lineChart.getHeight() + 10);

        xAxis.setLowerBound(xAxis.getLowerBound() + 0.1);
        xAxis.setUpperBound(xAxis.getUpperBound() - 0.1);
        yAxis.setLowerBound(yAxis.getLowerBound() + 0.1);
        yAxis.setUpperBound(yAxis.getUpperBound() + 0.1);
    }

    public void zoomOutChart() {
        lineChart.setPrefWidth(lineChart.getWidth() - 10);
        lineChart.setPrefHeight(lineChart.getHeight() - 10);
        xAxis.setLowerBound(xAxis.getLowerBound() - 0.1);
        xAxis.setUpperBound(xAxis.getUpperBound() + 0.1);
        yAxis.setLowerBound(yAxis.getLowerBound() - 0.1);
        yAxis.setUpperBound(yAxis.getUpperBound() + 0.1);
    }


    public void Clear() {
        series1.getData().clear();
        series2.getData().clear();
    }

    public void scaleChanger(Double newV) {
        xAxis.setTickUnit(newV);
        yAxis.setTickUnit(newV);
    }
}
