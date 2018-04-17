package rcas.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import rcas.model.RaceCar;

public class RCASMainModalController {

    @FXML
    private ListView<RaceCar> carView;

    @FXML
    private TextField name;

    public static final ObservableList<RaceCar> data =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        carView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        carView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {
                    //Use ListView's getSelected Item
                    RaceCar car = carView.getSelectionModel()
                            .getSelectedItem();
                    System.out.println(car.getName());
                    //use this to do whatever you want to. Open Link etc.
                }
            }
        });
    }

    @FXML
    private void add(ActionEvent event) {
        RaceCar raceCar = new RaceCar(0, 0, 0, 0);
        raceCar.setName(name.getText());
        data.add(raceCar);
        carView.setItems(data);
        name.clear();
    }


}
