package Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class SelectDifficultyController{

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private URL location;

    private void save_data(String difficulty) throws IOException {
        File file = new File("/Users/dimitrii/IdeaProjects/Test Grila/src/Test/data.txt");

        String domeniu = "";

        try {
            Scanner freader = new Scanner(file);

            domeniu = freader.nextLine();
            freader.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            PrintWriter pw = new PrintWriter(file);
            pw.println(domeniu);
            pw.println(difficulty);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void dific(ActionEvent event) throws IOException {
        save_data(((Button)event.getSource()).getText());

        URL fxmlURL = Paths.get("/Users/dimitrii/IdeaProjects/Test Grila/src/Test/QuizController.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(fxmlURL);
        root.requestFocus();

        AnchorPane scene = (AnchorPane) mainAnchorPane.getParent();

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(300), mainAnchorPane);
        fadeTransition.setFromValue(1.0f);
        fadeTransition.setToValue(0.0f);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(false);
        fadeTransition.play();

        fadeTransition.setOnFinished(actionEvent1 -> {
            root.setLayoutY(75);
            root.setOpacity(0.0f);
            scene.getChildren().add(root);
            FadeTransition fadeTransitionRevers = new FadeTransition(Duration.millis(300), root);
            fadeTransitionRevers.setFromValue(0.0f);
            fadeTransitionRevers.setToValue(1.0f);
            fadeTransitionRevers.setCycleCount(1);
            fadeTransitionRevers.setAutoReverse(false);
            fadeTransitionRevers.play();
            scene.getChildren().remove(mainAnchorPane);
        });
    }

    @FXML
    void initialize() {

    }
}

