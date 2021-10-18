package Display;

import Management.DictionaryCommandline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main1 extends Application {
    Stage window;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, ParseException {
        window = primaryStage;
        window.setTitle("Dictionary");


        DictionaryCommandline d = new DictionaryCommandline();



        //Text Search
        TextField nameinput = new TextField("Let's find your word");
        nameinput.setLayoutY(121);
        nameinput.setLayoutX(80);



        //Button
        Button button = new Button("Search");
        button.setLayoutY(120);
        button.setLayoutX(250);
        Button button1 = new Button("Search");
        button1.setLayoutY(750);
        button1.setLayoutX(20);
        Button button2 = new Button("  Add  ");
        button2.setLayoutY(750);
        button2.setLayoutX(20+75);
        Button button3 = new Button("   Fix   ");
        button3.setLayoutY(750);
        button3.setLayoutX(20+75*2);
        Button button4 = new Button("Delete");
        button4.setLayoutY(750);
        button4.setLayoutX(20+75*3);
        Button button5 = new Button("  API  ");
        button5.setLayoutY(750);
        button5.setLayoutX(20+75*4);

        //Image
        Pane root = new Pane();


        Image Img = new Image("/images/oxford4.png", 400,115,false,true);
        ImageView oxford = new ImageView(Img);

        Image Img2 = new Image("/images/frames7.png", 360,580,false,true);
        ImageView frames = new ImageView(Img2);
        frames.setY(200);
        frames.setX(20);

        Image Img3 = new Image("/images/line.png", 340,300,false,true);
        ImageView line = new ImageView(Img3);
        line.setX(30);
        line.setY(350);



        //Print1


        root.getChildren().addAll(nameinput, button, oxford, button1, button2, button3, button4, button5);
        Scene scene = new Scene(root,400,800);
        scene.getStylesheets().add("/Display/Style.css");
        window.setScene(scene);
        window.show();





        //Search with google API
        button.setOnAction(event -> {
            String wordE = nameinput.getText();
            Label nameLable = new Label(wordE);
            nameLable.setId("bold-label");
            nameLable.setLayoutX(70);
            nameLable.setLayoutY(250);
            String word = "zo";

            Label mean = new Label(word);
            mean.setId("bold-label");
            mean.setLayoutX(70);
            mean.setLayoutY(500);
            Pane root2 = new Pane();

            root2.getChildren().addAll(nameLable, line, frames, mean, nameinput, button, oxford);
            Scene scene2 = new Scene(root2,400,800);
            scene2.getStylesheets().add("/Display/Style.css");

            window.setScene(scene2);

        });



        //Search with my Dictionary

        //Add new word

        //Fix my word

        //Delete word






    }


}
