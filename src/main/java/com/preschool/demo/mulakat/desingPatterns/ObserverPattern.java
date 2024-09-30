package com.preschool.demo.mulakat.desingPatterns;

import java.util.ArrayList;
import java.util.List;


// Gözlemleyici arayüzü
interface Observer {
    void update(String message);
}

// Gözlemlenebilir sınıf
class Subject {
    private List<Observer> observers = new ArrayList<>();
    private String state;

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void setState(String state) {
        this.state = state;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(state);
        }
    }
}

// Gözlemleyici sınıfı
class ConcreteObserver implements Observer {
    private String observerState;

    @Override
    public void update(String message) {
        observerState = message;
        System.out.println("Yeni durum: " + observerState);
    }
}

// Kullanım
public class ObserverPattern {
    public static void main(String[] args) {
        Subject subject = new Subject();
        ConcreteObserver observer1 = new ConcreteObserver();
        ConcreteObserver observer2 = new ConcreteObserver();

        subject.attach(observer1);
        subject.attach(observer2);

        subject.setState("Yeni durum 1");
        subject.setState("Yeni durum 2");
    }
}

