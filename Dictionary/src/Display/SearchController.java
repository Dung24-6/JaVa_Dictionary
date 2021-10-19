package Display;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import Management.DictionaryManagement;

public class SearchController {

    @FXML
    private TextArea wordTarget;
    @FXML
    private TextArea wordExplain;
    @FXML
    private void initialize() {
        wordTarget.setWrapText(true);
        wordExplain.setWrapText(true);
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
    public void handleSearchButton() {
        String word = wordTarget.getText();
        String result = DictionaryManagement.dictionaryBinaryLookup(word);
        if (result.equalsIgnoreCase("Sử dụng google translate API")) {
            String suggest = DictionaryManagement.dictionarySearcher(word);
            wordExplain.setText(suggest);
            return;
        }
        wordExplain.setText(result);
    }
    public void handleVoiceButton1(ActionEvent event) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        voice.allocate();
        voice.speak(wordTarget.getText());
        voice.deallocate();
    }
    public void handleVoiceButton2(ActionEvent event) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        voice.allocate();
        voice.speak(wordExplain.getText());
        voice.deallocate();
    }
}
