package sample;

import DataBase.DataBase;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller extends DataBase implements Initializable{

    private double x,y;

    private boolean state;//daca e activa partea login sau signup   true pentru login si invers

    Parent rootLogin;
    Parent rootSignup;

    @FXML
    private AnchorPane parentContainer;

    @FXML
    private FontAwesomeIcon arrowIndicator;

    @FXML
    private AnchorPane Switch;

    @FXML
    private Label labelSwitch;

    private void loginToSignup() throws IOException {
        URL fxmlURL = Paths.get("/Users/dimitrii/IdeaProjects/Test Grila/src/Signup/Signup.fxml").toUri().toURL();
        rootSignup = FXMLLoader.load(fxmlURL);
        rootSignup.requestFocus();
        parentContainer.getChildren().add(rootSignup);

        Switch.setDisable(true);

        FadeTransition fadeLableSwitch =
                new FadeTransition(Duration.millis(250), labelSwitch);
        fadeLableSwitch.setFromValue(1.0f);
        fadeLableSwitch.setToValue(0.0f);
        fadeLableSwitch.setCycleCount(1);
        fadeLableSwitch.setAutoReverse(false);
        fadeLableSwitch.setOnFinished(event1 ->{

            labelSwitch.setText(" Log In");//space ca sa nu ma mai joc cu alinierea
            FadeTransition visibleLableSwitch =
                    new FadeTransition(Duration.millis(250), labelSwitch);
            visibleLableSwitch.setFromValue(0.0f);
            visibleLableSwitch.setToValue(1.0f);
            visibleLableSwitch.setCycleCount(1);
            visibleLableSwitch.setAutoReverse(false);
            visibleLableSwitch.play();


        });

        TranslateTransition translateSwitch =
                new TranslateTransition(Duration.millis(500), Switch);
        translateSwitch.setFromX(0);
        translateSwitch.setToX(480);
        translateSwitch.setCycleCount(1);
        translateSwitch.setAutoReverse(false);

        TranslateTransition translateSignupForm =
                new TranslateTransition(Duration.millis(500), rootSignup);
        translateSignupForm.setFromX(-480);
        translateSignupForm.setToX(0);
        translateSignupForm.setCycleCount(1);
        translateSignupForm.setAutoReverse(false);

        TranslateTransition translateLoginForm =
                new TranslateTransition(Duration.millis(500), rootLogin);
        translateLoginForm.setFromX(200);
        translateLoginForm.setToX(680);
        translateLoginForm.setCycleCount(1);
        translateLoginForm.setAutoReverse(false);

        RotateTransition rotateArrowIndicator =
                new RotateTransition(Duration.millis(500), arrowIndicator);
        rotateArrowIndicator.setByAngle(180f);
        rotateArrowIndicator.setCycleCount(1);
        rotateArrowIndicator.setAutoReverse(false);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                fadeLableSwitch,
                translateSwitch,
                translateLoginForm,
                translateSignupForm,
                rotateArrowIndicator
        );
        parallelTransition.setCycleCount(1);
        parallelTransition.play();
        parallelTransition.setOnFinished(finisAnimation ->{
            Switch.setDisable(false);
            parentContainer.getChildren().remove(rootLogin);
        });
    }

    private void signupToLogin() throws IOException {
        URL fxmlURL = Paths.get("/Users/dimitrii/IdeaProjects/Test Grila/src/Login/Login.fxml").toUri().toURL();
        rootLogin = FXMLLoader.load(fxmlURL);
        rootLogin.requestFocus();
        parentContainer.getChildren().add(rootLogin);

        Switch.setDisable(true);

        FadeTransition fadeLableSwitch =
                new FadeTransition(Duration.millis(250), labelSwitch);
        fadeLableSwitch.setFromValue(1.0f);
        fadeLableSwitch.setToValue(0.0f);
        fadeLableSwitch.setCycleCount(1);
        fadeLableSwitch.setAutoReverse(false);
        fadeLableSwitch.setOnFinished(event1 ->{

            labelSwitch.setText("Sign Up");
            FadeTransition visibleLableSwitch =
                    new FadeTransition(Duration.millis(250), labelSwitch);
            visibleLableSwitch.setFromValue(0.0f);
            visibleLableSwitch.setToValue(1.0f);
            visibleLableSwitch.setCycleCount(1);
            visibleLableSwitch.setAutoReverse(false);
            visibleLableSwitch.play();


        });

        TranslateTransition translateSwitch =
                new TranslateTransition(Duration.millis(500), Switch);
        translateSwitch.setFromX(480);
        translateSwitch.setToX(0);
        translateSwitch.setCycleCount(1);
        translateSwitch.setAutoReverse(false);

        TranslateTransition translateSignupForm =
                new TranslateTransition(Duration.millis(500), rootSignup);
        translateSignupForm.setFromX(0);
        translateSignupForm.setToX(-480);
        translateSignupForm.setCycleCount(1);
        translateSignupForm.setAutoReverse(false);

        TranslateTransition translateLoginForm =
                new TranslateTransition(Duration.millis(500), rootLogin);
        translateLoginForm.setFromX(680);
        translateLoginForm.setToX(200);
        translateLoginForm.setCycleCount(1);
        translateLoginForm.setAutoReverse(false);

        RotateTransition rotateArrowIndicator =
                new RotateTransition(Duration.millis(500), arrowIndicator);
        rotateArrowIndicator.setByAngle(180f);
        rotateArrowIndicator.setCycleCount(1);
        rotateArrowIndicator.setAutoReverse(false);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                fadeLableSwitch,
                translateSwitch,
                translateLoginForm,
                translateSignupForm,
                rotateArrowIndicator
        );
        parallelTransition.setCycleCount(1);
        parallelTransition.play();
        parallelTransition.setOnFinished(finisAnimation ->{
            Switch.setDisable(false);
            parentContainer.getChildren().remove(rootSignup);
        });
    }

    @FXML
    void switchClicked(MouseEvent event) throws IOException {
        if (!state) {
            loginToSignup();
            state = true;
        }
        else{
            signupToLogin();
            state = false;
        }
    }

    @FXML
    void switchEntered(MouseEvent event) {
        FadeTransition visible = new FadeTransition(Duration.millis(200), arrowIndicator);
        visible.setFromValue(0.0f);
        visible.setToValue(1.0f);
        visible.setCycleCount(1);
        visible.setAutoReverse(false);
        visible.play();
    }

    @FXML
    void switchExited(MouseEvent event) {
        FadeTransition hidden = new FadeTransition(Duration.millis(200), arrowIndicator);
        hidden.setToValue(0.0f);
        hidden.setCycleCount(1);
        hidden.setAutoReverse(false);
        hidden.play();
    }



    @FXML
    void closeAction(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void minimizeAction(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void titlebarDragged(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    void titlebarPressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
        System.out.println(x+"   "+y);
    }

    private void switchScene() throws IOException {
        URL fxmlURL = Paths.get("/Users/dimitrii/IdeaProjects/Test Grila/src/Login/Login.fxml").toUri().toURL();
        rootLogin = FXMLLoader.load(fxmlURL);
        rootLogin.setLayoutX(200);
        parentContainer.getChildren().add(rootLogin);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            switchScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
        arrowIndicator.setOpacity(0.0f);
    }
}
