package model;

import javafx.scene.control.Alert;

public class MainModel {
    public Double HParameter;

    public MainModel(String HParam) {
        Double h;
        try {
            h = Double.parseDouble(HParam);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("H-parameter should be double");
            alert.show();
            HParameter = null;
            return;
        }

        if (h > 1.75 || h <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("H-parameter should be set from 0 to 1.75");
            alert.show();
            HParameter = null;
            return;
        }

        HParameter = h;
    }
}
