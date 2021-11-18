package controller;

import javafx.scene.control.Alert;
import model.CalculationsModel;
import model.Point;
import view.Table;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MainController {
    private FunctionCalculator functionCalculator = new FunctionCalculator();
    private Table table = new Table();

    public CalculationsModel CalculateFunctions(Double x) throws InterruptedException, ExecutionException {
        Future<Double> future1 = functionCalculator.CalculateYForFunction1(x);
        Future<Double> future2 = functionCalculator.CalculateYForFunction2(x);

        while (!(future1.isDone() && future2.isDone()) ) {
//                    System.out.println(
//                            String.format(
//                                    "future1 is %s and future2 is %s",
//                                    future1.isDone() ? "done" : "not done",
//                                    future2.isDone() ? "done" : "not done"
//                            )
//                    );
           try {
               Thread.sleep(10);
            } catch (InterruptedException e) {
              return null;

           }
       }

        Double result1 = future1.get();
        Double result2 = future2.get();

        return new CalculationsModel(x, result1, result2);
    }

    public Table getFunction2Table() {
        return table;
    }

    public void clearFunction2Table() {
        table.clearTable();
    }

    public void updateTable(CalculationsModel calculationsModel) {
        table.updateTable(new Point(calculationsModel.X, Math.round(calculationsModel.Function2Value * 10000.0) / 10000.0));
    }
}
