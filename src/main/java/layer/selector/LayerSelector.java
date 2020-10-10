package layer.selector;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

/**
 * @author Chen Jiongyu
 */
public class LayerSelector {
    private final ObservableList<Layer> layers;
    @FXML
    private TableView<Layer> layerTable;
    @FXML
    private TableColumn<Layer, Boolean> checkedCol;
    @FXML
    private TableColumn<Layer, Color> colorCol;
    @FXML
    private TableColumn<Layer, String> nameCol;

    public LayerSelector() {
        layers = FXCollections.observableArrayList();
    }

    @FXML
    private void initialize() {
        initLayerTable();
    }

    private void initLayerTable() {
        layerTable.setEditable(true);

        layerTable.setItems(layers);
        checkedCol.setCellFactory(CheckBoxTableCell.forTableColumn(checkedCol));
        checkedCol.setCellValueFactory(new PropertyValueFactory<>("checked"));
        colorCol.setCellFactory(ColorTableCell::new);
        colorCol.setCellValueFactory(new PropertyValueFactory<>("color"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        checkedCol.setPrefWidth(30);
//        colorCol.setPrefWidth(30);
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
