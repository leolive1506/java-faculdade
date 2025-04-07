package org.example;

public enum FieldType {
    TEXT("Text"),
    TEXT_AREA("Text_Area"),
    INPUT("Input"),
    BUTTON("Button"),
    BOX("Button");

    private final String label;

    FieldType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
