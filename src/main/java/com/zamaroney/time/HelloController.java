package com.zamaroney.time;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class HelloController {
    private int remainingTime = 0;
    private Timeline timeline;

    @FXML
    public Label alert;
    @FXML
    public Label timer;
    @FXML
    public TextField enterMinutes;
    @FXML
    public Button btnSetTime;
    @FXML
    public Button btnStart;
    @FXML
    public Button btnStop;
    @FXML
    public Button btnReset;


    @FXML
    public void setTime() {
        alert.setText("Timer Set");
        remainingTime = Integer.parseInt(enterMinutes.getText())*60;
        timer.setText(formatTime(remainingTime));
    }

    @FXML
    public void startTimer() {
        if (remainingTime > 0) {
            alert.setText("Timer Running");
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                remainingTime--;
                timer.setText(formatTime(remainingTime));

                if (remainingTime <= 0) {
                    timeline.stop();
                    alert.setText("Time's up!");
//                playAlarm();
                }
            }));
            timeline.setCycleCount(remainingTime);
            timeline.play();
        }
    }

    @FXML
    public void stopTimer() {
        alert.setText("Timer Stopped");
        if (timeline != null) {
            timeline.stop();
        }
    }

    @FXML
    public void resetTimer(ActionEvent actionEvent) {
        stopTimer();
        alert.setText("Timer Reset");
        remainingTime = 0; // Reset time
        timer.setText(formatTime(remainingTime));
    }

    @FXML
    public String formatTime(int seconds) {
        int minutes = seconds / 60;
        int secs = seconds % 60;

        return String.format("%02d:%02d", minutes, secs);
    }
}