package view;

import controller.ChartController;
import controller.MainController;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.CalculationsModel;
import model.MainModel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainView extends BorderPane {
    private ExecutorService executor;

    private ChartController chartController = new ChartController();
    private MainController mainController = new MainController();
    private Label hLabel = new Label("h-parameter");
    private TextField hTextField = new TextField();
    private Button startButton = new Button("start");
    private Button stopButton = new Button("stop");

    public MainView(Stage stage) {
        startButton.setOnAction(actionEvent -> {
            Start();
        });

        stopButton.setOnAction(actionEvent -> {
            Stop();
        });

        VBox functionControls = new VBox(hLabel, hTextField, startButton, stopButton);
        this.setLeft(mainController.getFunction2Table());
        this.setRight(functionControls);
        this.setCenter(chartController.getGroupChart());

    }

    public void Stop() {
        if (executor != null) {
            executor.shutdownNow();
        }
    }

    public void Start() {
        MainModel model = new MainModel(hTextField.getText());
        if (model.HParameter == null) {
            return;
        }
        executor = Executors.newFixedThreadPool(1);
        executor.execute(new Runnable() {
            public void run() {
                StartDrawing(model);
            }
        });
    }

    private void StartDrawing(MainModel model) {
        mainController.clearFunction2Table();
        chartController.clear();

        try {
            for (Double i = 0.0; i < 1.75; i += model.HParameter) {
                CalculationsModel calculationsModel = mainController.CalculateFunctions(i);

                if (calculationsModel == null) {
                    return;
                }

                Platform.runLater(new Runnable() {
                    public void run() {
                        chartController.updateChart(calculationsModel);
                        mainController.updateTable(calculationsModel);
                    }
                });

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}