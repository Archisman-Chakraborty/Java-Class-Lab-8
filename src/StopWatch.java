import java.awt.*;
import java.awt.event.*;

class StopWatch extends Frame implements ActionListener, Runnable {

    Label timeLabel;
    Button start, stop, reset;

    int ms = 0, seconds = 0, minutes = 0, hours = 0;
    boolean running = false;
    Thread t;

    StopWatch() {
        setLayout(new FlowLayout());

        timeLabel = new Label("00:00:00:000");
        start = new Button("Start");
        stop = new Button("Stop");
        reset = new Button("Reset");

        add(timeLabel);
        add(start);
        add(stop);
        add(reset);

        start.addActionListener(this);
        stop.addActionListener(this);
        reset.addActionListener(this);

        setSize(300, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            running = true;
            t = new Thread(this);
            t.start();
        } else if (e.getSource() == stop) {
            running = false;
        } else if (e.getSource() == reset) {
            running = false;
            ms = seconds = minutes = hours = 0;
            timeLabel.setText("00:00:00:000");
        }
    }

    public void run() {
        try {
            while (running) {
                Thread.sleep(10);

                ms += 10;

                if (ms == 1000) {
                    ms = 0;
                    seconds++;
                }

                if (seconds == 60) {
                    seconds = 0;
                    minutes++;
                }

                if (minutes == 60) {
                    minutes = 0;
                    hours++;
                }

                timeLabel.setText(
                        String.format("%02d:%02d:%02d:%03d",
                                hours, minutes, seconds, ms)
                );
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new StopWatch();
    }
}