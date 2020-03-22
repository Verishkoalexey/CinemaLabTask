package by.cinemaLab.task.service.impl;

import by.cinemaLab.task.model.Human;
import by.cinemaLab.task.service.HumanService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;



public class HumanServiceImpl implements HumanService {

    private ObservableList<Human> humanList = FXCollections.observableArrayList();

    @Override
    public void add(Human human) {
        humanList.add(human);
    }

    @Override
    public void update(Human human) {

    }

    @Override
    public void delete(Human human) {
        humanList.remove(human);
    }

    public ObservableList<Human> getHumanList(){
        return humanList;
    }

    public void print(){
        int number = 0;
        System.out.println();
        for (Human human :
                humanList) {
            number++;
            System.out.println(number+") Name - "+human.getName()+"; Age - "+human.getAge()+"; Birthday - " +human.getBirthday());
        }
    }
    public void fillTestData(){
        humanList.add(new Human("Alex", 26, LocalDate.of(1986,06,26)));
        humanList.add(new Human("Bob", 22, LocalDate.of(1979,03,9)));
        humanList.add(new Human("John", 31, LocalDate.of(1988,07,5)));
        humanList.add(new Human("Lion", 43, LocalDate.of(1983,11,13)));
        humanList.add(new Human("Brad", 35, LocalDate.of(1985,12,11)));
    }
}
