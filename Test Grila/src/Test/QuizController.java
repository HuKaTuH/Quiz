package Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import javafx.animation.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class QuizController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button raspundeButton;

    @FXML
    private Label domainLabel;

    @FXML
    private RadioButton answerbtn1;

    @FXML
    private ToggleGroup answersGroup;

    @FXML
    private Label questionLabel;

    @FXML
    private RadioButton answerbtn2;

    @FXML
    private RadioButton answerbtn3;

    @FXML
    private RadioButton answerbtn4;

    @FXML
    private ProgressBar progresBarTimer;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Label questionNumber;

    int timeMill;

    private Test q;
    private String domeniu = "";
    private String dificultate = "";

    private int nowQuestion = -1, correctAnswers;
    private String nowCorrectAnswer;

    public void readData(){
        File file = new File("/Users/dimitrii/IdeaProjects/Test Grila/src/Test/data.txt");

        try {
            Scanner freader = new Scanner(file);

            domeniu = freader.nextLine();
            dificultate = freader.nextLine();
            freader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    Question QList[] = new Question[10];

    public void start(){

        QList = q.getQuestionList();

        AtomicReference<Boolean> finisat = new AtomicReference<>(false);

        IntegerProperty seconds = new SimpleIntegerProperty();
        progresBarTimer.progressProperty().bind(seconds.divide(60.0));
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(timeMill), new KeyValue(seconds, 0)),
                new KeyFrame(Duration.minutes(0), e -> {
                    System.out.println("time over");

                    RadioButton selectedRadioButton = (RadioButton) answersGroup.getSelectedToggle();
                    nowQuestion++;
                    nowCorrectAnswer = QList[nowQuestion].correctAnswers();
                    questionNumber.setText(nowQuestion+1+"/10");
                    questionLabel.setText(QList[nowQuestion].getQuestion());
                    String[] answers = QList[nowQuestion].getAnswers();

                    List<String> intList = Arrays.asList(answers);

                    Collections.shuffle(intList);

                    answerbtn1.setText(intList.get(0));
                    answerbtn2.setText(intList.get(1));
                    answerbtn3.setText(intList.get(2));
                    answerbtn4.setText(intList.get(3));

                    if (selectedRadioButton != null)
                        selectedRadioButton.setSelected(false);
                }, new KeyValue(seconds, 60))
        );
        timeline.setCycleCount(10);
        timeline.play();

            raspundeButton.setOnAction(actionEvent -> {
                if(finisat.get()) {
                    URL fxmlURL = null;
                    try {
                        fxmlURL = Paths.get("/Users/dimitrii/IdeaProjects/Test Grila/src/Test/SelectDomainController.fxml").toUri().toURL();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(fxmlURL);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    root.requestFocus();

                    AnchorPane scene = (AnchorPane) mainAnchorPane.getParent();

                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(300), mainAnchorPane);
                    fadeTransition.setFromValue(1.0f);
                    fadeTransition.setToValue(0.0f);
                    fadeTransition.setCycleCount(1);
                    fadeTransition.setAutoReverse(false);
                    fadeTransition.play();

                    Parent finalRoot = root;
                    fadeTransition.setOnFinished(actionEvent1 -> {
                        finalRoot.setLayoutY(75);
                        finalRoot.setOpacity(0.0f);
                        scene.getChildren().add(finalRoot);
                        FadeTransition fadeTransitionRevers = new FadeTransition(Duration.millis(300), finalRoot);
                        fadeTransitionRevers.setFromValue(0.0f);
                        fadeTransitionRevers.setToValue(1.0f);
                        fadeTransitionRevers.setCycleCount(1);
                        fadeTransitionRevers.setAutoReverse(false);
                        fadeTransitionRevers.play();
                        scene.getChildren().remove(mainAnchorPane);
                    });
                }else {
                    RadioButton selectedRadioButton = (RadioButton) answersGroup.getSelectedToggle();
                    if (selectedRadioButton != null) {
                        timeline.stop();
                        String toogleGroupValue = selectedRadioButton.getText();

                        if (toogleGroupValue.equals(nowCorrectAnswer)) {
                            System.out.println("corect");
                            correctAnswers++;
                        } else
                            System.out.println("gresit");

                        if (nowQuestion + 1 == QList.length) {
                            answerbtn1.setVisible(false);
                            answerbtn2.setVisible(false);
                            answerbtn3.setVisible(false);
                            answerbtn4.setVisible(false);
                            progresBarTimer.setVisible(false);
                            questionLabel.setText("Ati raspuns corect la " + correctAnswers + " din " + QList.length + " Intrebari! ");
                            raspundeButton.setText("Domenii");
                            finisat.set(true);
                        } else {
                            timeline.play();
                        }
                    }
                }
            });
    }


    @FXML
    void initialize() {

        readData();

        domainLabel.setText(domeniu);

        q = new Test(domeniu);

        switch(dificultate){
            case "Usor": timeMill = 35000;break;
            case "Normal": timeMill = 25000;break;
            case "Dificil": timeMill = 15000;break;
            default:timeMill = 35000;break;
        }

        start();

    }

}
