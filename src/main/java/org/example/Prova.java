package org.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.example.db.UserDB;
import org.example.db.UserReport;
import org.example.entities.User;

import javax.swing.*;

public class Prova {
    private UserDB db;
    private Screen screen;

    public Prova() {
        this.db = new UserDB();
        screen = new Screen();
    }

    public void execute() {

        try {
            String[] bloodType = {"A", "B", "AB", "O"};
            String[] rhFactors = {"+", "-"};
            String[] courses = {"Direito", "Ciência da Computação", "Sistemas De Informação", "Medicina", "Psicologia", "Nutrição"};

            screen.addField("Id");
            screen.addField("Nome");
            screen.addField("Endereço");
            screen.addFormattedField("Telefone", "(##) #####-####");
            screen.addFormattedField("CPF", "###.###.###-##");
            screen.addBox("Tipo sanguíneo", bloodType);
            screen.addBox("Fator RH", rhFactors);
            screen.addBox("Curso", courses);
            screen.addField("Contato de Emergência");
            screen.addFormattedField("Telefone Emergência", "(##) #####-####");
            screen.addField("Altura");
            screen.addField("Peso");
            screen.addField("IMC");

            screen.addButton("Calcular IMC", calcBmi());
            screen.addButton("Cadastrar", save());
            screen.addButton("Alterar", update());
            screen.addButton("Remover", delete());
            screen.addButton("Listagem", getUsersListener());
            screen.addButton("Relatorio", report());

            screen.addTextArea("Listagem", db.list());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        screen.render();
    }

    public ActionListener save() {
        return e -> {
            JTextField fullName = (JTextField) screen.findField("Nome").field();
            JTextField address = (JTextField) screen.findField("Endereço").field();
            JTextField phone = (JTextField) screen.findField("Telefone").field();
            JTextField cpf = (JTextField) screen.findField("CPF").field();
            JComboBox bloodType = (JComboBox) screen.findField("Tipo sanguíneo").field();
            JComboBox rhFactor = (JComboBox) screen.findField("Fator RH").field();
            JComboBox course = (JComboBox) screen.findField("Curso").field();
            JTextField emergencyContact = (JTextField) screen.findField("Contato de Emergência").field();
            JTextField emergencyPhone = (JTextField) screen.findField("Telefone Emergência").field();
            JTextField height = (JTextField) screen.findField("Altura").field();
            JTextField weight = (JTextField) screen.findField("Peso").field();

            User user = new User(
                fullName.getText(),
                address.getText(),
                phone.getText(),
                cpf.getText(),
                (String) bloodType.getSelectedItem(),
                (String) rhFactor.getSelectedItem(),
                (String) course.getSelectedItem(),
                emergencyContact.getText(),
                emergencyPhone.getText(),
                Double.parseDouble(height.getText()),
                Double.parseDouble(weight.getText())
            );


            db.insert(user);

            getUsers();
        };
    }

    public ActionListener update() {
        return e -> {
            JTextField id = (JTextField) screen.findField("Id").field();
            JTextField fullName = (JTextField) screen.findField("Nome").field();
            JTextField address = (JTextField) screen.findField("Endereço").field();
            JTextField phone = (JTextField) screen.findField("Telefone").field();
            JTextField cpf = (JTextField) screen.findField("CPF").field();
            JComboBox bloodType = (JComboBox) screen.findField("Tipo sanguíneo").field();
            JComboBox rhFactor = (JComboBox) screen.findField("Fator RH").field();
            JComboBox course = (JComboBox) screen.findField("Curso").field();
            JTextField emergencyContact = (JTextField) screen.findField("Contato de Emergência").field();
            JTextField emergencyPhone = (JTextField) screen.findField("Telefone Emergência").field();
            JTextField height = (JTextField) screen.findField("Altura").field();
            JTextField weight = (JTextField) screen.findField("Peso").field();

            User user = new User(
                fullName.getText(),
                address.getText(),
                phone.getText(),
                cpf.getText(),
                (String) bloodType.getSelectedItem(),
                (String) rhFactor.getSelectedItem(),
                (String) course.getSelectedItem(),
                emergencyContact.getText(),
                emergencyPhone.getText(),
                Double.parseDouble(height.getText()),
                Double.parseDouble(weight.getText())
            );


            db.update(Integer.parseInt(id.getText()), user);
            getUsers();
        };
    }

    public ActionListener calcBmi() {
        return e -> {
            JTextField height = (JTextField) screen.findField("Altura").field();
            JTextField weight = (JTextField) screen.findField("Peso").field();
            JTextField bmi = (JTextField) screen.findField("IMC").field();

            Double total = Double.parseDouble(weight.getText()) / (Double.parseDouble(height.getText()) * Double.parseDouble(height.getText()));

            bmi.setText(String.valueOf(total));

            String message = "";
            if (total >= 18.5 && total <= 25) {
                message = "Peso ideal";
            }

            if (total > 25) {
                message = "Você está acima do peso ideal!";
            }

            if (total < 18.5) {
                message = "Você está abaixo do peso ideal";
            }

            JOptionPane.showMessageDialog(null, message);
        };
    }

    public ActionListener getUsersListener() {
        return e -> {
            getUsers();
        };
    }

    public ActionListener delete() {
        return e -> {
            JTextField id = (JTextField) screen.findField("Id").field();

            db.delete(Integer.parseInt(id.getText()));
            getUsers();
        };
    }

    public ActionListener report() {
        return e -> {
            JScrollPane area = (JScrollPane) screen.findField("Listagem").field();

            UserReport report = db.report();

            System.out.println(report);
            String users = String.format("""
                - O maior peso cadastrado: %.2f kg, nome da pessoa: %s e Tipo Sanguíneo|Fator RH: %s%s
                - O menor peso cadastrado: %.2f kg, nome da pessoa: %s e Tipo Sanguíneo|Fator RH: %s%s
                - A média de todos os pesos cadastrados: %.2f kg
                - A maior altura cadastrada: %.2f m, nome da pessoa: %s e o curso: %s
                - A menor altura cadastrada: %.2f m, nome da pessoa: %s e o curso: %s
                - A média de todas as alturas cadastradas: %.2f m
                - A média de todos os IMC cadastrados: %.2f
                - O maior IMC cadastrado: %.2f, nome da pessoa: %s
                - O menor IMC cadastrado: %.2f, nome da pessoa: %s
            """,
                report.maxWeight, report.maxWeightName, report.maxWeightBlood, report.maxWeightRhFactor,
                report.minWeight, report.minWeightName, report.minWeightBlood, report.minWeightRhFactor,
                report.avgWeight,
                report.maxHeight, report.maxHeightName, report.maxHeightCourse,
                report.minHeight, report.minHeightName, report.minHeightCourse,
                report.avgHeight,
                report.avgBMI,
                report.maxBMI, report.maxBMIName,
                report.minBMI, report.minBMIName
            );

            System.out.println(users);
            if (area.getViewport().getView() instanceof JTextArea textArea) {
                textArea.setText(users);
            }
        };
    }

    public void getUsers() {
        JScrollPane area = (JScrollPane) screen.findField("Listagem").field();

        String users = this.db.list();

        if (area.getViewport().getView() instanceof JTextArea textArea) {
            textArea.setText(users);
        }
    }
}
