package moisturesensor;

import java.util.Random; //to generate random numbers
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.io.Serializable;
public class MoistureSensor implements PropertyChangeListener, Serializable {

    private static final long serialVersionUID = 123456789L;
    public PropertyChangeSupport changes1 = new PropertyChangeSupport(this);
    public PropertyChangeSupport changes2 = new PropertyChangeSupport(this);

    static boolean decreasing, olddecreasing;
    static int currentHumidity;
    static int oldCurrentHumidity;

    public MoistureSensor() {
        changes1.addPropertyChangeListener(this);
        changes2.addPropertyChangeListener(this);
    }

    // add a property change listener
    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes1.addPropertyChangeListener(l);
        changes2.addPropertyChangeListener(l);
    }

    // remove
    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes1.removePropertyChangeListener(l);
        changes2.removePropertyChangeListener(l);
    }

    public void setDec(boolean d) {
        decreasing = d;
    }

    public boolean getDec() {
        return decreasing;
    }

    public int getNum() {
        Random rand = new Random();
        int n = rand.nextInt(10); // Generate Int number between 0:10
        return n;
    }

    public int getHumidity() {
        return currentHumidity;
    }

    public void setHumidity() { // random value for 'decreasing'
        Random rand = new Random(); // create object from the class Random
        currentHumidity = rand.nextInt(100); // Generate Int number between 0:100
    }

    public void start() {
        Thread sensorThread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    setHumidity();
                    oldCurrentHumidity = currentHumidity;

                    olddecreasing = decreasing;
                    setDec(Math.random() < 0.5);
                    decreasing = getDec();
                    changes2.firePropertyChange("decreasing", olddecreasing, decreasing);

                    int num = getNum();

                    if (!decreasing) {
                        if ((currentHumidity - num) >= 0) {
                            currentHumidity = currentHumidity - num;
                        }
                    } else {
                        if ((currentHumidity + num) <= 100) {
                            currentHumidity = currentHumidity + num;
                        }
                    }

                    changes1.firePropertyChange("currentHumidity", oldCurrentHumidity, currentHumidity);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                // Handle or log the exception if required
            }
        });

        sensorThread.start();
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        // Handle property change event if needed
    }
}