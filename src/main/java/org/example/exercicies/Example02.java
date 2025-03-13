package org.example.exercicies;

import org.example.Field;
import org.example.Screen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class Example02 {
    private Screen screen;

    public void execute() {
        screen = new Screen();

        try {
            String[] users = {"Admin", "Usuario"};

            screen.addField("Numero 1");
            screen.addField("Peso 1");
            screen.addField("Numero 2");
            screen.addField("Peso 2");

            screen.addButton("Calcular", calc());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        screen.render();
    }

    public ActionListener calc() throws RuntimeException {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField numberOne = (JTextField) screen.findField("Numero 1").field();
                JTextField weightOne = (JTextField) screen.findField("Peso 1").field();

                JTextField numberTwo = (JTextField) screen.findField("Numero 2").field();
                JTextField weightTwo = (JTextField) screen.findField("Peso 2").field();

                if (numberOne.getText().isBlank()
                        || numberTwo.getText().isBlank()
                        || weightOne.getText().isBlank()
                        || weightTwo.getText().isBlank()
                ) {
                    return;
                }

                Float average = ((Float.parseFloat(numberOne.getText()) * Float.parseFloat(weightOne.getText()))
                        + (Float.parseFloat(numberTwo.getText()) * Float.parseFloat(weightTwo.getText())))
                        / (Float.parseFloat(weightOne.getText()) + Float.parseFloat(weightTwo.getText()));

                System.out.println(average);
            }
        };
    }
}
