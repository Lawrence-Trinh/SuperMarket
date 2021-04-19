package view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
//Simple class to update the time and date
//Integrate this class with the GUI

class ClockPanel {
    private final JLabel time = new JLabel();
    private final SimpleDateFormat sdf  = new SimpleDateFormat("hh:mm");
    private int currentSecond;
    private Calendar calendar;

//    public static void main( String [] args ) {
//        JFrame frame = new JFrame();
//        Clock clock = new Clock();
//        frame.add( clock.time );
//        frame.pack();
//        frame.setVisible( true );
//        clock.start();
//    }
    private void reset(){
        calendar = Calendar.getInstance();
        currentSecond = calendar.get(Calendar.SECOND);
    }
    public void start(){
        reset();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate( new TimerTask(){
            @Override
			public void run(){
                if( currentSecond == 60 ) {
                    reset();
                }
                getTime().setText( String.format("%s:%02d", sdf.format(calendar.getTime()), currentSecond ));
                currentSecond++;
            }
        }, 0, 1000 );
    }
	public JLabel getTime() {
		return time;
	}
}