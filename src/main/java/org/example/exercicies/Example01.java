package org.example.exercicies;

import org.example.Screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Example01 {
    public static void execute() {
        Screen screen = new Screen();

        try {
            String[] users = {"Admin", "Usuario"};

            screen.addField("Nome");
            screen.addFormattedField("CPF", "###.###.###-##");
            screen.addBox("Tipo de usuario", users);
            screen.addButton("Salvar", save());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        screen.render();
    }

    public static ActionListener save() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Chegooo");
            }
        };
    }
}
