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

public class EditController {

    @FXML
    private TextField addTextField1;
    @FXML
    private TextField addTextField2;
    @FXML
    private TextField modifyTextField1;
    @FXML
    private TextField modifyTextField2;
    @FXML
    private TextField deleteTextField;
    @FXML
    private TableView<Word> wordTable = new TableView<>();
    @FXML
    private TableColumn<Word, String> englishColumn = new TableColumn<>("English");
    @FXML
    private TableColumn<Word, String> vietnameseColumn = new TableColumn<>("Vietnamese");

    ObservableList<Word> wordObservableList = FXCollections.observableList(DictionaryManagement.wordList);

    @FXML
    private void initialize() {
        englishColumn.setCellValueFactory(new PropertyValueFactory<>("word_target"));
        vietnameseColumn.setCellValueFactory(new PropertyValueFactory<>("word_explain"));
        wordTable.setItems(wordObservableList);
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
    public void handleAddButton() {
        String wordTarget = addTextField1.getText();
        String wordExplain = addTextField2.getText();
        if (wordTarget.equalsIgnoreCase("")) {
            return;
        }
        DictionaryManagement.addWord(wordTarget, wordExplain);

        wordObservableList = FXCollections.observableList(DictionaryManagement.wordList);
        wordTable.setItems(wordObservableList);
        wordTable.refresh();

        addTextField1.clear();
        addTextField2.clear();
    }
    public void handleModifyButton() {
        String wordTarget = modifyTextField1.getText();
        String wordExplain = modifyTextField2.getText();
        DictionaryManagement.modifyWord(wordTarget, wordExplain);

        wordObservableList = FXCollections.observableList(DictionaryManagement.wordList);
        wordTable.setItems(wordObservableList);
        wordTable.refresh();

        modifyTextField1.clear();
        modifyTextField2.clear();
    }
    public void handleDeleteButton() {
        // Xoa tu tim kiem
        String word = deleteTextField.getText();
        if (word.equals("")) {
            // Xoa tu duoc chon
            ObservableList<Word> wordSelected;
            ObservableList<Word> allWord;
            allWord = wordTable.getItems();
            wordSelected = wordTable.getSelectionModel().getSelectedItems();

            wordSelected.forEach(allWord :: remove);
            wordTable.refresh();
        } else {
            DictionaryManagement.deleteWord(word);

            wordObservableList = FXCollections.observableList(DictionaryManagement.wordList);
            wordTable.setItems(wordObservableList);
            wordTable.refresh();

            deleteTextField.clear();
        }
    }

}
