package layer.selector;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;

/**
 * @author Chen Jiongyu
 */
public class LayerSelectorApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        VBox layerSelector = fxmlLoader.load(getClass().getResource("LayerSelector.fxml").openStream());
        LayerSelector selector = fxmlLoader.getController();
        selector.addLayer(new LayerSelector.Layer(false, Color.RED, "layer1"));
        selector.addLayer(new LayerSelector.Layer(false, Color.GREEN, "layer2"));
        selector.addLayer(new LayerSelector.Layer(false, Color.BLUE, "layer3"));
        selector.addLayer(new LayerSelector.Layer(false, Color.GRAY, "layer4"));
        PopOver popOver = new PopOver();
        popOver.setContentNode(layerSelector);
        popOver.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);
        Button layerBtn = new Button();
        layerBtn.setOnAction(event -> {
            popOver.show(layerBtn);
            popOver.detach();
        });
        layerBtn.setText("layer");
        StackPane stackPane = new StackPane(layerBtn);
        Scene scene = new Scene(stackPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
