package org.example.entities;

public class User {

    private String fullName;
    private String address;
    private String phone;
    private String cpf;
    private String bloodType;
    private String rhFactor;
    private String course;
    private String emergencyContact;
    private String emergencyPhone;
    private double height;
    private double weight;
    private double bmi;

    public User(String fullName, String address, String phone, String cpf, String bloodType, String rhFactor,
                String course, String emergencyContact, String emergencyPhone, double height, double weight) {
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.cpf = cpf;
        this.bloodType = bloodType;
        this.rhFactor = rhFactor;
        this.course = course;
        this.emergencyContact = emergencyContact;
        this.emergencyPhone = emergencyPhone;
        this.height = height;
        this.weight = weight;
        recalculateBmi();
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getRhFactor() {
        return rhFactor;
    }

    public void setRhFactor(String rhFactor) {
        this.rhFactor = rhFactor;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContactName(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhoneNumber(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
        recalculateBmi();
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
        recalculateBmi();
    }

    public double getBmi() {
        return bmi;
    }

    private void recalculateBmi() {
        if (height > 0) {
            this.bmi = weight / (height * height);
        } else {
            this.bmi = 0;
        }
    }

    @Override
    public String toString() {
        return "User {" +
                "\n  Full Name: " + fullName +
                "\n  Address: " + address +
                "\n  Phone: " + phone +
                "\n  CPF: " + cpf +
                "\n  Blood Type: " + bloodType +
                "\n  RH Factor: " + rhFactor +
                "\n  Course: " + course +
                "\n  Emergency Contact: " + emergencyContact +
                "\n  Emergency Phone: " + emergencyPhone +
                "\n  Height: " + height +
                "\n  Weight: " + weight +
                "\n  BMI: " + String.format("%.2f", bmi) +
                "\n}";
    }
}
