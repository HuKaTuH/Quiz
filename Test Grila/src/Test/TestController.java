package Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Main;

public class TestController {

    @FXML
    private Label header_label;

    @FXML
    private MenuButton username_menu;

    @FXML
    private AnchorPane mainAnchorPane;


    @FXML
    void initialize() throws IOException {
        String item[] = new String[1];

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("/Users/dimitrii/IdeaProjects/Test Grila/src/Test/data.txt"));

            String line;

            int i = 0;
            while ((line = br.readLine()) != null) {
                item[i] = line;
                i++;
            }

            br.close();
            username_menu.setText(item[0]);
            header_label.setText("Bun venit " + item[0]);
        } catch (Exception e) {
            header_label.setText("Bun venit user");
            username_menu.setText("User");
        }

        URL fxmlURL = Paths.get("/Users/dimitrii/IdeaProjects/Test Grila/src/Test/SelectDomainController.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(fxmlURL);
        root.requestFocus();

        AnchorPane scene = (AnchorPane) username_menu.getParent().getParent();

        root.setLayoutY(75);
        scene.getChildren().add(root);

    }

    public void logout(ActionEvent actionEvent) throws IOException {
        System.out.println("Log out");


        URL fxmlURL = Paths.get("/Users/dimitrii/IdeaProjects/Test Grila/src/sample/sample.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(fxmlURL);

        Stage window = (Stage) mainAnchorPane.getScene().getWindow();
        window.setScene(new Scene(root,680,460));
    }
}


