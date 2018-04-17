package rcas.controller;

import com.sun.javafx.binding.BidirectionalBinding;
import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rcas.model.RaceCar;


public class RCASMainModalController {

    @FXML
    private ListView<RaceCar> carView;

    @FXML
    private TextField addName;

    @FXML
    private TextField detailName;

    @FXML
    private TextField leftFrontTier;

    @FXML
    private TextField rightFrontTier;

    @FXML
    private TextField leftBackTire;

    @FXML
    private TextField rightBackTier;

    @FXML
    private TextField fronttrack;

    @FXML
    private TextField reartrack;

    @FXML
    private TextField wheelbase;

    @FXML
    private TextField cogheight;

    @FXML
    private TextField frontrolldist;

    private ObservableList<RaceCar> data =
            FXCollections.observableArrayList();

    private RaceCar selectedCar;

    @FXML
    public void initialize() {
        carView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        carView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    //Use ListView's getSelected Item
                    selectedCar = carView.getSelectionModel()
                            .getSelectedItem();
                    carBindings();
                }
            }
        });
    }

    @FXML
    private void add(ActionEvent event) {
        RaceCar raceCar = new RaceCar(0, 0, 0, 0);
        raceCar.setName(addName.getText());
        data.add(raceCar);
        carView.setItems(data);
        addName.clear();
    }

    public void carBindings() {
        detailName.textProperty().bind(selectedCar.nameProperty());
        leftFrontTier.textProperty().bind(selectedCar.cornerWeightFLProperty().asString());
        rightFrontTier.textProperty().bind(selectedCar.cornerWeightFRProperty().asString());
        leftBackTire.textProperty().bind(selectedCar.cornerWeightRLProperty().asString());
        rightBackTier.textProperty().bind(selectedCar.cornerWeightRRProperty().asString());

        fronttrack.textProperty().bind(selectedCar.frontTrackProperty().asString());
        reartrack.textProperty().bind(selectedCar.rearTrackProperty().asString());
        wheelbase.textProperty().bind(selectedCar.wheelbaseProperty().asString());
        cogheight.textProperty().bind(selectedCar.cogHeightProperty().asString());
        frontrolldist.textProperty().bind(selectedCar.frontRollDistProperty().asString());
    }

    @FXML
    private void plusButtonClick(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/RCASNewTire.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }    }
}
