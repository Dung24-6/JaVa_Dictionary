package Display;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import Management.Word;
import Management.DictionaryManagement;

public class HistoryController {

    @FXML
    private TableView<Word> recentWordTable = new TableView<>();
    @FXML
    private TableColumn<Word, String> englishColumn = new TableColumn<>("English");
    @FXML
    private TableColumn<Word, String> vietnameseColumn = new TableColumn<>("Vietnamese");

    ObservableList<Word> wordObservableList = FXCollections.observableList(DictionaryManagement.recentWordList);

    @FXML
    private void initialize() {
        englishColumn.setCellValueFactory(new PropertyValueFactory<>("word_target"));
        vietnameseColumn.setCellValueFactory(new PropertyValueFactory<>("word_explain"));
        recentWordTable.setItems(wordObservableList);
    }

    public void handleSceneMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuScene.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(String.valueOf(getClass().getResource("Style.css")));
        stage.setTitle("Dictionary");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}
