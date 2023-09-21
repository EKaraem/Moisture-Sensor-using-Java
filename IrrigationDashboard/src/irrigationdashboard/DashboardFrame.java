package irrigationdashboard;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import moisturesensor.MoistureSensor;
import java.io.Serializable;


public class DashboardFrame extends JFrame implements Serializable, PropertyChangeListener {
    private JLabel l1, l2;
    private JLabel statusLabel;
    private JButton btn;
    private MoistureSensor m;
    private Thread sensorThread;

    public DashboardFrame(MoistureSensor moistureSensor) {
        m = moistureSensor;
        setTitle("Dashboard");
        setSize(400, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        l1 = new JLabel("Current Humidity");
        l2 = new JLabel("Decreasing");
        statusLabel = new JLabel("Irrigation: Off");
        btn = new JButton("Start");

        l1.setBounds(160, 20, 500, 100);
        l2.setBounds(160, 40, 200, 200);
        statusLabel.setBounds(160, 260, 200, 40);
        btn.setBounds(150, 220, 70, 40);

        add(btn);
        add(l1);
        add(l2);
        add(statusLabel);
    }

    public JButton getBtn() {
        return btn;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("currentHumidity")) {
            int humidity = (int) evt.getNewValue();
            l2.setText("Current Humidity: " + humidity);
            if (humidity >= 30 && humidity <= 90) {
                statusLabel.setText("Irrigation: On");
            } else {
                statusLabel.setText("Irrigation: Off");
            }
        } else if (evt.getPropertyName().equals("decreasing")) {
            boolean decreasing = (boolean) evt.getNewValue();
            // Update any relevant GUI components related to the "decreasing" property
        }
    }
}