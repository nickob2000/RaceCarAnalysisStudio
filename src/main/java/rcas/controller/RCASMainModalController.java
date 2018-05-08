package rcas.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import rcas.model.MagicFormulaTireModel;
import rcas.model.RaceCar;
import rcas.model.TireModel;

import java.util.Locale;
import java.util.ResourceBundle;

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

        carView.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2) {
                //Use ListView's getSelected Item
                boolean hasSelectedItem = carView.getSelectionModel().getSelectedItem() != null;
                if (hasSelectedItem) {
                    selectedCar = carView.getSelectionModel()
                            .getSelectedItem();
                    carBindings();
                }
            }
        });

        addName.setOnKeyPressed(ke -> {
            if (ke.getCode() == KeyCode.ENTER) {
                addNewRaceCar();
            }
        });
    }

    @FXML
    private void add(ActionEvent event) {
        addNewRaceCar();
    }

    private void addNewRaceCar() {
        String carName = addName.getText();
        if (!carName.trim().isEmpty() && carName.trim().length() <= 20) {
            RaceCar raceCar = new RaceCar(0, 0, 0, 0);
            raceCar.setName(addName.getText());
            data.add(raceCar);
            carView.setItems(data);
        }
        addName.clear();
    }

    @FXML
    private void openDiagram(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/RCASDiagram.fxml"));
            ResourceBundle resourceBundle = ResourceBundle.getBundle("RCASResources", Locale.getDefault());
            fxmlLoader.setResources(resourceBundle);

            Parent root = fxmlLoader.load();

            RCASDiagramController diagramController = fxmlLoader.getController();
            diagramController.useRaceCar(selectedCar);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
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
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
