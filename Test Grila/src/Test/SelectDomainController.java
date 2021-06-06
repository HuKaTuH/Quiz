package Test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class SelectDomainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    void initialize() {

    }
    private void save_data(String domaine) throws IOException {
        File file = new File("/Users/dimitrii/IdeaProjects/Test Grila/src/Test/data.txt");
        try{
            file.createNewFile();

            PrintWriter pw = new PrintWriter(file);
            pw.println(domaine);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dom(javafx.event.ActionEvent actionEvent) throws IOException {
        save_data(((Button)actionEvent.getSource()).getText());

        URL fxmlURL = Paths.get("/Users/dimitrii/IdeaProjects/Test Grila/src/Test/SelectDifficultyController.fxml").toUri().toURL();
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
}
