package Display;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import org.json.simple.parser.ParseException;
import Management.DictionaryManagement;
import GoogleTranslator.GoogleTranslator.LANGUAGE;

public class AdvancedSearchController {

    @FXML
    private TextArea wordTarget;
    @FXML
    private TextArea wordExplain;
    @FXML
    private ChoiceBox<String> languageChoices1 = new ChoiceBox<>();
    @FXML
    private ChoiceBox<String> languageChoices2 = new ChoiceBox<>();

    @FXML
    private void initialize() {
        wordTarget.setWrapText(true);
        wordExplain.setWrapText(true);
        languageChoices1.getItems().addAll(
                "Auto",
                "VIETNAMESE",
                "ENGLISH",
                "FRENCH",
                "GERMAN",
                "RUSSIAN",
                "KOREAN",
                "JAPANESE",
                "CHINESE");
        languageChoices1.setValue("Auto");
        languageChoices2.getItems().addAll(
                "VIETNAMESE",
                "ENGLISH",
                "FRENCH",
                "GERMAN",
                "RUSSIAN",
                "KOREAN",
                "JAPANESE",
                "CHINESE");
        languageChoices2.setValue("VIETNAMESE");
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
    public void handleAdvanceSearchButton(ActionEvent event) throws IOException, ParseException {
        String word = wordTarget.getText();
        String languageTarget = languageChoices1.getValue();
        String languageExplain = languageChoices2.getValue();

        String result = DictionaryManagement.googleTranslator(word,
                        LANGUAGE.stringToLanguage(languageTarget), LANGUAGE.stringToLanguage(languageExplain));
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
