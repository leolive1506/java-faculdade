package org.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.example.db.UserDB;
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

            screen.addButton("Calcular IMC", save());
            screen.addButton("Cadastrar", save());
            screen.addButton("Alterar", update());
            screen.addButton("Listagem", getUsersListener());
            screen.addButton("Relatorio", save());

            screen.addTextArea("Listagem", getUsers());
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
            JTextField bmi = (JTextField) screen.findField("IMC").field();

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
            JTextField bmi = (JTextField) screen.findField("IMC").field();

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
        };
    }

    public ActionListener getUsersListener() {
        return e -> {
            String users = this.getUsers();
            System.out.println(users);
        };
    }

    public String getUsers() {
        return this.db.list();
    }
}
