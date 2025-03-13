package org.example.exercicies;

import org.example.Screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Votes {
    private Screen screen;

    public void execute() {
        int width = 600;

        screen = new Screen(width);

        try {
            screen.addText("Candidatos");
            screen.addText("1 - Joaquim Santos");
            screen.addText("2 - Karina Constas");
            screen.addText("3 - Voto em Branco");

            screen.addField("Voto");
            screen.addButton("Votar", vote());
            screen.addButton("Apuracao dos votos", voteCounting());

            screen.addText("Total de votos: --");
            screen.addText("Total em branco: --");
            screen.addText("Total em candidato 1: --");
            screen.addText("Total em candidato 2: --");
            screen.addText("Total em candidato 3: --");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        screen.render();
    }

    public ActionListener vote() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Vote");
            }
        };
    }

    public ActionListener voteCounting() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Couting");
            }
        };
    }
}
