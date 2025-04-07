package org.example.db;

public class UserReport {
    public float maxWeight;
    public String maxWeightName;
    public String maxWeightBlood;
    public String maxWeightRhFactor;

    public float minWeight;
    public String minWeightName;

    public String getMaxWeightRhFactor() {
        return maxWeightRhFactor;
    }

    public void setMaxWeightRhFactor(String maxWeightRhFactor) {
        this.maxWeightRhFactor = maxWeightRhFactor;
    }

    public String getMinWeightRhFactor() {
        return minWeightRhFactor;
    }

    public void setMinWeightRhFactor(String minWeightRhFactor) {
        this.minWeightRhFactor = minWeightRhFactor;
    }

    public String minWeightBlood;
    public String minWeightRhFactor;

    public float avgWeight;

    public float maxHeight;
    public String maxHeightName;
    public String maxHeightCourse;

    public float minHeight;
    public String minHeightName;
    public String minHeightCourse;

    public float avgHeight;

    public float avgBMI;
    public float maxBMI;
    public String maxBMIName;
    public float minBMI;

    public float getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(float maxWeight) {
        this.maxWeight = maxWeight;
    }

    public String getMaxWeightName() {
        return maxWeightName;
    }

    public void setMaxWeightName(String maxWeightName) {
        this.maxWeightName = maxWeightName;
    }

    public String getMaxWeightBlood() {
        return maxWeightBlood;
    }

    public void setMaxWeightBlood(String maxWeightBlood) {
        this.maxWeightBlood = maxWeightBlood;
    }

    public float getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(float minWeight) {
        this.minWeight = minWeight;
    }

    public String getMinWeightName() {
        return minWeightName;
    }

    public void setMinWeightName(String minWeightName) {
        this.minWeightName = minWeightName;
    }

    public String getMinWeightBlood() {
        return minWeightBlood;
    }

    public void setMinWeightBlood(String minWeightBlood) {
        this.minWeightBlood = minWeightBlood;
    }

    public float getAvgWeight() {
        return avgWeight;
    }

    public void setAvgWeight(float avgWeight) {
        this.avgWeight = avgWeight;
    }

    public float getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(float maxHeight) {
        this.maxHeight = maxHeight;
    }

    public String getMaxHeightName() {
        return maxHeightName;
    }

    public void setMaxHeightName(String maxHeightName) {
        this.maxHeightName = maxHeightName;
    }

    public String getMaxHeightCourse() {
        return maxHeightCourse;
    }

    public void setMaxHeightCourse(String maxHeightCourse) {
        this.maxHeightCourse = maxHeightCourse;
    }

    public float getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(float minHeight) {
        this.minHeight = minHeight;
    }

    public String getMinHeightName() {
        return minHeightName;
    }

    public void setMinHeightName(String minHeightName) {
        this.minHeightName = minHeightName;
    }

    public String getMinHeightCourse() {
        return minHeightCourse;
    }

    public void setMinHeightCourse(String minHeightCourse) {
        this.minHeightCourse = minHeightCourse;
    }

    public float getAvgHeight() {
        return avgHeight;
    }

    public void setAvgHeight(float avgHeight) {
        this.avgHeight = avgHeight;
    }

    public float getAvgBMI() {
        return avgBMI;
    }

    public void setAvgBMI(float avgBMI) {
        this.avgBMI = avgBMI;
    }

    public float getMaxBMI() {
        return maxBMI;
    }

    public void setMaxBMI(float maxBMI) {
        this.maxBMI = maxBMI;
    }

    public String getMaxBMIName() {
        return maxBMIName;
    }

    public void setMaxBMIName(String maxBMIName) {
        this.maxBMIName = maxBMIName;
    }

    public float getMinBMI() {
        return minBMI;
    }

    public void setMinBMI(float minBMI) {
        this.minBMI = minBMI;
    }

    public String getMinBMIName() {
        return minBMIName;
    }

    public void setMinBMIName(String minBMIName) {
        this.minBMIName = minBMIName;
    }

    public String minBMIName;

    public UserReport(
        float maxWeight, String maxWeightName, String maxWeightBlood, String maxWeightRhFactor,
        float minWeight, String minWeightName, String minWeightBlood, String minWeightRhFactor,
        float avgWeight,
        float maxHeight, String maxHeightName, String maxHeightCourse,
        float minHeight, String minHeightName, String minHeightCourse,
        float avgHeight,
        float avgBMI, float maxBMI, String maxBMIName,
        float minBMI, String minBMIName
    ) {
        this.maxWeight = maxWeight;
        this.maxWeightName = maxWeightName;
        this.maxWeightBlood = maxWeightBlood;
        this.maxWeightRhFactor = maxWeightRhFactor;

        this.minWeight = minWeight;
        this.minWeightName = minWeightName;
        this.minWeightBlood = minWeightBlood;
        this.minWeightRhFactor = minWeightRhFactor;

        this.avgWeight = avgWeight;

        this.maxHeight = maxHeight;
        this.maxHeightName = maxHeightName;
        this.maxHeightCourse = maxHeightCourse;

        this.minHeight = minHeight;
        this.minHeightName = minHeightName;
        this.minHeightCourse = minHeightCourse;

        this.avgHeight = avgHeight;

        this.avgBMI = avgBMI;
        this.maxBMI = maxBMI;
        this.maxBMIName = maxBMIName;

        this.minBMI = minBMI;
        this.minBMIName = minBMIName;
    }

    @Override
    public String toString() {
        return String.format("""
        - O maior peso cadastrado: %.2f kg, nome da pessoa: %s e Tipo Sanguíneo|Fator RH: %s
        - O menor peso cadastrado: %.2f kg, nome da pessoa: %s e Tipo Sanguíneo|Fator RH: %s
        - A média de todos os pesos cadastrados: %.2f kg
        - A maior altura cadastrada: %.2f m, nome da pessoa: %s e o curso: %s
        - A menor altura cadastrada: %.2f m, nome da pessoa: %s e o curso: %s
        - A média de todas as alturas cadastradas: %.2f m
        - A média de todos os IMC cadastrados: %.2f
        - O maior IMC cadastrado: %.2f, nome da pessoa: %s
        - O menor IMC cadastrado: %.2f, nome da pessoa: %s
    """,
                maxWeight, maxWeightName, maxWeightBlood,
                minWeight, minWeightName, minWeightBlood,
                avgWeight,
                maxHeight, maxHeightName, maxHeightCourse,
                minHeight, minHeightName, minHeightCourse,
                avgHeight,
                avgBMI,
                maxBMI, maxBMIName,
                minBMI, minBMIName
        );
    }
}
