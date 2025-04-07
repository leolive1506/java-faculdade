package org.example;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Screen extends JFrame {
    private int width = 400;

    private ArrayList<Field> fields = new ArrayList<>();

    public Screen(int width) {
        this.width = width;
    }

    public Screen() {
    }

    public Field findField(String label) {
        return fields.stream()
                .filter(field -> {
                    if (field.type() == FieldType.TEXT_AREA) {
                        if (field.field().getBorder() instanceof javax.swing.border.TitledBorder border) {
                            return Objects.equals(border.getTitle(), label);
                        }
                    }

                    if (field.label() == null) {
                        return false;
                    }

                    return Objects.equals(field.label().getText(), label);
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Field not found"));
    }

    public void addField(String label) {
        Field newField = new Field(
            new JLabel(label),
            new JTextField(),
            FieldType.INPUT
        );

        this.fields.add(newField);
    }

    public void addText(String label) {
        JLabel jLabel = new JLabel(label);

        Field newLabel = new Field(jLabel);

        this.fields.add(newLabel);
    }

    public void addTextArea(String label, String text) {
        JTextArea area = new JTextArea(text);
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(area);

        scrollPane.setBorder(BorderFactory.createTitledBorder(label));

        this.fields.add(new Field(scrollPane));
    }


    public void addFormattedField(String label, String formatter) throws ParseException {
        Field newField = new Field(
            new JLabel(label),
            new JFormattedTextField(new MaskFormatter(formatter)),
            FieldType.INPUT
        );

        this.fields.add(newField);
    }

    public void addBox(String label, String[] options) throws ParseException {
        Field newField = new Field(
            new JLabel(label),
            new JComboBox<>(options),
            FieldType.BOX
        );

        this.fields.add(newField);
    }

    public void addButton(String label, ActionListener action) {
        JButton jButton = new JButton(label);

        jButton.addActionListener(action);

        Field button = new Field(null, jButton, org.example.FieldType.BUTTON);

        this.fields.add(button);
    }

    public void render() {
        setLayout(null);
        AtomicInteger positionY = new AtomicInteger(10);
        AtomicInteger height = new AtomicInteger(10);

        this.fields.forEach(element -> {
            if (element.label() != null && element.type() != FieldType.TEXT) {
                element.label().setBounds(10, positionY.get(), 100, 25);
                getContentPane().add(element.label());
            }

            if (element.label() != null && element.type() == FieldType.TEXT) {
                element.label().setBounds(10, positionY.get(), this.width, 25);
                getContentPane().add(element.label());
            }

            if (element.field() != null) {
                element.field().setBounds(this.width / 2, positionY.get(), 200, 25);
                getContentPane().add(element.field());
            }

            if (element.field() != null && element.type() == FieldType.TEXT_AREA) {
                element.field().setBounds(10, positionY.get(), width, 100);
                getContentPane().add(element.field());
            }


            positionY.addAndGet(30);
            height.addAndGet(positionY.get());
        });

        setSize(this.width, (fields.size() * 30) + 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        repaint();
        revalidate();
        setVisible(true);
    }
}
