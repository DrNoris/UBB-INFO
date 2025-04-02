package org.example.recap1.Service;

import java.util.ArrayList;

public abstract class Observable {
    private ArrayList<Observer> subs = new ArrayList<Observer>();

    public void subscribe(Observer o){
        subs.add(o);
    }

    public void delete(Observer o){
        subs.remove(o);
    }

    public void notif(){
        for (Observer o : subs){
            o.update();
        }
    }
}
