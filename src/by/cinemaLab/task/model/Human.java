package by.cinemaLab.task.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


import java.time.LocalDate;


public class Human {
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleIntegerProperty age = new SimpleIntegerProperty(0);
    private SimpleStringProperty birthday = new SimpleStringProperty(LocalDate.now().toString());

    public Human(){

    }

    public Human(String name, int age, LocalDate birthday) {
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.birthday = new SimpleStringProperty(birthday.toString());
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getAge() {
        return age.get();
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public String getBirthday() {
        return birthday.get();
    }

    public void setBirthday(String birthday) {
        this.birthday.set(birthday);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleIntegerProperty ageProperty() {
        return age;
    }

    public SimpleStringProperty birthdayProperty() {
        return birthday;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", Birthday=" + birthday +
                '}';
    }
}
