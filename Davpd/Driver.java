import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Driver {
    public static void main(String args[]){
        JFrame jFrame = new JFrame("DAVPD");
        JPanel contestJPanel = new Contest(1080, 720);
        jFrame.addWindowListener(new WindowAdapter() {
                public void windowClosed(WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(1080, 720);
        jFrame.add(contestJPanel);
        jFrame.setLayout(null);
        jFrame.setResizable(true);
        jFrame.setVisible(true);
        jFrame.setTitle("DAVPD");
    }
}
