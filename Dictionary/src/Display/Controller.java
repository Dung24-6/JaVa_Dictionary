package Display;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class Controller {
        private Stage stage;
        private Scene scene;
        private Parent root;

        public void toNormalSearch(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("SceneSearch.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    public void toEdit(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("SceneEdit.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }





}
