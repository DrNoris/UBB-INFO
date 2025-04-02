package org.example.practic.Service;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import org.example.practic.Controller.DriverView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class Observable {
    private ArrayList<Observer> subs = new ArrayList<>();

    public void subscribe(Observer o){
        subs.add(o);
    }

    public void delete(Observer o){
        subs.remove(o);
    }

    public void notifyAboutNewOrder(int orderId) {
        List<Observer> eligibleDrivers = new ArrayList<>();

        // Filter eligible drivers (those who have no in-progress order)
        for (Observer observer : subs) {
            if (observer instanceof DriverView && !((DriverView) observer).hasInProgressOrder()) {
                eligibleDrivers.add(observer);
            }
        }

        // Sort eligible drivers by the time of their last finished order (oldest first)
        eligibleDrivers.sort(new Comparator<Observer>() {
            @Override
            public int compare(Observer o1, Observer o2) {
                DriverView driverView1 = (DriverView) o1;
                DriverView driverView2 = (DriverView) o2;

                Timestamp lastFinishedOrderDate1 = driverView1.getLastFinishedOrderDate();
                Timestamp lastFinishedOrderDate2 = driverView2.getLastFinishedOrderDate();

                // If both drivers have no finished order, treat them equally
                if (lastFinishedOrderDate1 == null && lastFinishedOrderDate2 == null) {
                    return 0;
                }

                // If one driver has no finished order (null), give priority to the other driver
                if (lastFinishedOrderDate1 == null) {
                    return -1;
                }
                if (lastFinishedOrderDate2 == null) {
                    return 1;
                }

                // Compare the finished dates: the driver with the oldest finished order should come first
                return lastFinishedOrderDate1.compareTo(lastFinishedOrderDate2);
            }
        });

        // Start notifying the first eligible driver (after sorting)
        while (!eligibleDrivers.isEmpty()) {
            DriverView driverView = (DriverView) eligibleDrivers.get(0);
            driverView.update(orderId);

            eligibleDrivers.remove(0);

            // Set a timer to handle timeout after 5 seconds
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(event -> {
                if (!driverView.hasInProgressOrder()) {
                    driverView.removeOrderNotification();
                    System.out.println("Driver did not accept the order in time.");

                    // Move on to the next driver if any
                    if (!eligibleDrivers.isEmpty()) {
                        DriverView nextDriverView = (DriverView) eligibleDrivers.get(0);
                        nextDriverView.update(orderId);
                    }
                }
            });

            pause.play();

            // Only notify one driver at a time (first in sorted list)
            break;
        }
    }


}
