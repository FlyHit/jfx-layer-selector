package layer.selector;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * @author Chen Jiongyu
 */
public class LayerSelector {
    private final ObservableList<Layer> layers;
    private static final int EXTRA_WIDTH = 10;
    private static final int EXTRA_HEIGHT = 10;
    @FXML
    private TableColumn<Layer, Boolean> checkedCol;
    @FXML
    private TableColumn<Layer, Color> colorCol;
    @FXML
    private TableColumn<Layer, String> nameCol;
    private static final int ROW_HEIGHT = 40;
    @FXML
    private VBox vBox;
    @FXML
    private TableView<Layer> layerTable;

    public LayerSelector() {
        layers = FXCollections.observableArrayList();
    }

    @FXML
    private void initialize() {
        // add extra width to offset borders or insets' width
        vBox.prefWidthProperty().bind(checkedCol.widthProperty().add(colorCol.widthProperty()).add(nameCol.widthProperty()).add(EXTRA_HEIGHT));
        initLayerTable();
    }

    private void initLayerTable() {
        GUIUtils.autoFitTable(layerTable);
        layerTable.setEditable(true);

        layerTable.setItems(layers);
        checkedCol.setCellFactory(CheckBoxTableCell.forTableColumn(checkedCol));
        checkedCol.setCellValueFactory(new PropertyValueFactory<>("checked"));
        colorCol.setCellFactory(ColorTableCell::new);
        colorCol.setCellValueFactory(new PropertyValueFactory<>("color"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        layerTable.setFixedCellSize(ROW_HEIGHT);
        layerTable.prefHeightProperty().bind(layerTable.fixedCellSizeProperty().multiply(Bindings.size(layerTable.getItems())).add(EXTRA_WIDTH));
        layerTable.minHeightProperty().bind(layerTable.prefHeightProperty());
    }

    public void addLayer(Layer layer) {
        layers.add(layer);
    }

    public static class Layer {
        private final BooleanProperty checked;
        private final ObjectProperty<Color> color;
        private final StringProperty name;

        public Layer(boolean checked, Color color, String name) {
            this.checked = new SimpleBooleanProperty(checked);
            this.color = new SimpleObjectProperty<>(color);
            this.name = new SimpleStringProperty(name);
        }

        public boolean isChecked() {
            return checked.get();
        }

        public void setChecked(boolean checked) {
            this.checked.set(checked);
        }

        public Color getColor() {
            return color.get();
        }

        public void setColor(Color color) {
            this.color.set(color);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String name) {
            this.name.set(name);
        }
    }
}
