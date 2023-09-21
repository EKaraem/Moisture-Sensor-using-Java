package irrigationdashboard;


import java.beans.PropertyChangeEvent;
import moisturesensor.MoistureSensor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class irrigationdashboard {
    public static void main(String[] args) {
        MoistureSensor m = new MoistureSensor();
        DashboardFrame frame = new DashboardFrame(m);

    //    Controller controller = new Controller();

        m.addPropertyChangeListener((PropertyChangeEvent e) -> {
            System.out.println("Value Changed: " + e.getPropertyName() + " was " + e.getOldValue() + " and is now " + e.getNewValue());
          //  controller.test((int) e.getNewValue());
            frame.propertyChange(e);
        });

        frame.getBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
         
                    m.start();
           
            }
        });

        frame.setVisible(true);
    }
}