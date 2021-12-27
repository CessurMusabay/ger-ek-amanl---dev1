import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm {
    private JPanel jpanel;
    private JTextField textField1;
    private JButton GETButton;
    private JButton PUTButton;
    private JButton startReadButton;
    private JButton stopWriteButton;
    private JButton stopReadButton;
    private JButton startWriteButton;
    private BoundedBuffer rb;
    private ReadersWriters rw;


    public MainForm() {
        rb = new BoundedBuffer(3);
        rw = new ReadersWriters();
        GETButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    try {
                        JOptionPane.showMessageDialog(null, "GET " + rb.get());
                        System.out.println(rb.get());
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }).start();
            }
        });


        PUTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = 0;
                try {
                    value = Integer.parseInt(textField1.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, value + "Invalid Value");
                    return;
                }
                int finalValue = value;
                new Thread(() -> {
                    try {
                        rb.put(finalValue);

                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }).start();
            }
        });


        startReadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    try {
                        rw.StartRead();
                        JOptionPane.showMessageDialog(null, "Started Reading");

                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }).start();
            }
        });


        stopReadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    try {
                        rw.StopRead();
                        JOptionPane.showMessageDialog(null, "Stopped Reading");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }).start();
            }
        });


        startWriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    try {
                        rw.StartWrite();
                        JOptionPane.showMessageDialog(null, "Stared Writing");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }).start();
            }
        });


        stopWriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    try {
                        rw.StopWrite();
                        JOptionPane.showMessageDialog(null, "Stopped Weiting");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }).start();
            }
        });
    }


    public static void main(String[] args) {

        JFrame frame = new JFrame("Homework1");
        frame.setContentPane(new MainForm().jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
