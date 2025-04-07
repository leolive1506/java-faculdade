package org.example;

import javax.swing.*;

public class Field {
    private JLabel label;
    private JComponent field;
    private FieldType fieldType;

    public Field(JLabel label, JComponent field, FieldType fieldType) {
        this.label = label;
        this.field = field;
        this.fieldType = fieldType;
    }

    public Field(JLabel label) {
        this.label = label;
        this.field = null;
        this.fieldType = FieldType.TEXT;
    }

    public Field(JComponent area) {
        this.label = null;
        this.field = area;
        this.fieldType = FieldType.TEXT_AREA;
    }

    public JLabel label() {
        return this.label;
    }

    public JComponent field() {
        return this.field;
    }

    public FieldType type() {
        return this.fieldType;
    }

}
