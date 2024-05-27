package org.example.calculator.application;

import javafx.scene.control.Button;

public class NumberButton extends Button {
    int number;

    public NumberButton(int number) {
        super(String.valueOf(number));
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public char getCharNumber() {
        return getStringNumber().charAt(0);
    }


    public String getStringNumber() {
        return String.valueOf(number);
    }

}

