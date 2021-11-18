package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Point;

public class Table extends TableView {

    ObservableList<Point> tableData = FXCollections.observableArrayList();
    //HBox hBox=new HBox();

    public Table() {
        super();
        setItems(tableData);

        TableColumn<Point, Double> xColumn = new TableColumn<Point, Double>("X");
        xColumn.setCellValueFactory(new PropertyValueFactory<Point, Double>("x"));
        this.getColumns().add(xColumn);

        TableColumn<Point, Double> yColumn = new TableColumn<Point, Double>("Y");
        yColumn.setCellValueFactory(new PropertyValueFactory<Point, Double>("y"));
        this.getColumns().add(yColumn);
        this.setColumnResizePolicy(javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY);


    }

    public TableView getGroup() {
        return this;
    }

    public void updateTable(Point point) {
        tableData.add(point);
    }

    public void clearTable() {
        tableData.clear();
    }
}
