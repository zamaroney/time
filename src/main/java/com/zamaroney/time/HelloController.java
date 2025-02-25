package com.zamaroney.time;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

import java.util.Objects;

public class HelloController {
    private int remainingTime = 0;
    private Timeline timeline;
    private AudioClip alarmSound;
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
        remainingTime = Integer.parseInt(enterMinutes.getText());
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
                    playAlarm();
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
            stopAlarm();
        }
    }

    @FXML
    public void resetTimer() {
        stopTimer();
        alert.setText("Timer Reset");
        stopAlarm();
        remainingTime = 0; // Reset time
        timer.setText(formatTime(remainingTime));
    }

    @FXML
    public String formatTime(int seconds) {
        int minutes = seconds / 60;
        int hours = minutes / 60;
        minutes = minutes % 60;
        int secs = seconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }

    private void playAlarm() {
        try {
            String soundPath = Objects.requireNonNull(getClass().getResource("/com/zamaroney/time/sounds/alarm.wav")).toExternalForm();
            if (soundPath == null) {
                System.out.println("ERROR: Sound file not found!");
                return;
            }
            alarmSound = new AudioClip(soundPath);
            alarmSound.play();
        }
        catch (Exception e) {
            System.out.println("Error playing sound: " + e.getMessage());
        }

    }

    private void stopAlarm() {
        if (alarmSound != null) {
            alarmSound.stop(); // Stop the sound if it's playing
        }
    }
}