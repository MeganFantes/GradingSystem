import Model.Statistics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

class Popup_StudentInfo {
    private JFrame f;
    private ButtonGroup buttonGroupStudent = new ButtonGroup();
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jRadioButton1;
    //private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;

    public Popup_StudentInfo(ArrayList<String> s) {
        f = new JFrame("Student Information");
        buttonGroupStudent = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        //jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();


        jLabel1.setText("View student information");

        buttonGroupStudent.add(jRadioButton1);
        jRadioButton1.setText(s.get(0));


        jRadioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

//        buttonGroupStudent.add(jRadioButton2);
//        jRadioButton2.setText("Last name");

        buttonGroupStudent.add(jRadioButton3);
        jRadioButton3.setText(s.get(1));

        buttonGroupStudent.add(jRadioButton4);
        jRadioButton4.setText(s.get(2));

        jButton1.setText("Done");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(f.getContentPane());
        f.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(42, 42, 42)
                                                .addComponent(jLabel1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(24, 24, 24)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        //.addComponent(jRadioButton2)
                                                        .addComponent(jRadioButton1)
                                                        .addComponent(jRadioButton3)
                                                        .addComponent(jRadioButton4)
                                                        .addComponent(jButton1))))
                                .addContainerGap(199, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addGap(27, 27, 27)
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                //.addComponent(jRadioButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton4)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addContainerGap(52, Short.MAX_VALUE))
        );

        f.pack();

        f.setSize(200,200);
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //System.exit(0);
                f.dispose();
            }
        });
        // make the JFrame appear in the middle of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation(dim.width/2-f.getSize().width/2, dim.height/2-f.getSize().height/2);
    }
}

class Popup_Average {

    JButton callingButton;
    static JTextField t1;
    static JTextField t2;
    static JTextField t3;
    static JTextField t4;
    static JFrame f;
    static JButton b;
    static JLabel L1;
    static JLabel L2;
    static JLabel L3;
    static JLabel L4;
    static JLabel L5;
    static JLabel L6;
    static JLabel L7;
    static JLabel L8;
    public Popup_Average(Statistics statistics) {
        HashMap<String, Float> statMap = statistics.computeStatistics();
        f = new JFrame("Average Statistics");
        L1 = new JLabel("Average");
        L2 = new JLabel("Minimum");
        L3 = new JLabel("Maximum");
        L4 = new JLabel("Standard Deviation");
        b = new JButton("Done");
        //AL_AssignmentAverage te = new AL_AssignmentAverage(b);
        //b.addActionListener(te);
        L5 = new JLabel(String.valueOf(statMap.get("avg")));
        L6 = new JLabel(String.valueOf(statMap.get("min")));
        L7 = new JLabel(String.valueOf(statMap.get("max")));
        L8 = new JLabel(String.valueOf(statMap.get("stddev")));
        JPanel p = new JPanel(new GridLayout(8,1));
        b.setPreferredSize(new Dimension(25, 25));
        p.add(L1);
        p.add(L5);
        p.add(L2);
        p.add(L6);
        p.add(L3);
        p.add(L7);
        p.add(L4);
        p.add(L8);
        p.add(b);

        f.setSize(300, 300);

        f.setLayout(new BorderLayout());
        f.getContentPane().add(p, BorderLayout.NORTH);
        f.setVisible(true);
        // make the JFrame appear in the middle of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation(dim.width/2-f.getSize().width/2, dim.height/2-f.getSize().height/2);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    }
//    public static void main(String args[]) {
//        Popup_Average p = new Popup_Average();
//    }
}

class Popup_GradingOption {
    JButton callingButton;
    static JButton jButton;
    static JRadioButton jRadioButton1;
    static JRadioButton jRadioButton2;
    static JRadioButton jRadioButton3;
    static JLabel L1;
    static JLabel L2;
    static JLabel L3;
    static ButtonGroup G1;
    static JFrame f;
    static JTextField t;
    public Popup_GradingOption() {
        jRadioButton1 = new JRadioButton();
        jRadioButton2 = new JRadioButton();
        jRadioButton3 = new JRadioButton();
        //L1 = new JLabel();
        jButton = new JButton("Done");
        G1 = new ButtonGroup();
        f = new JFrame("Grading Options");
        jRadioButton1.setText("Percentage");
        jRadioButton2.setText("Point deduction");
        jRadioButton3.setText("Raw score");
        G1.add(jRadioButton1);
        G1.add(jRadioButton2);
        G1.add(jRadioButton3);
        // button won't resize
        jButton.setPreferredSize(new Dimension(10,10));
        JPanel p = new JPanel(new GridLayout(4,2));
        p.add(jRadioButton1);
        p.add(jRadioButton2);
        p.add(jRadioButton3);
        p.add(jButton);
        f.add(p);
        f.setSize(300, 300);
        f.setLayout(new BorderLayout());
        f.getContentPane().add(p, BorderLayout.NORTH);
        f.setVisible(true);
        // make the JFrame appear in the middle of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation(dim.width/2-f.getSize().width/2, dim.height/2-f.getSize().height/2);
    }
    public static void main(String args[]) {
        Popup_GradingOption p = new Popup_GradingOption();
    }
}

class Popup_AddColumn {
    static JButton jButton;
    static JFrame f;
    static JLabel L1;
    static JTextField t1;
    public Popup_AddColumn(){
        JButton callingButton;
        jButton = new JButton("Done");
        f = new JFrame("Add Column");
        L1 = new JLabel("Name");
        t1 = new JTextField(16);
        JPanel p = new JPanel(new GridLayout(2,1));
        p.add(L1);
        p.add(t1);
        p.add(jButton);
        f.add(p);
        f.setSize(300, 300);
        f.setLayout(new BorderLayout());
        f.getContentPane().add(p, BorderLayout.NORTH);
        f.setVisible(true);
        // make the JFrame appear in the middle of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation(dim.width/2-f.getSize().width/2, dim.height/2-f.getSize().height/2);
    }
    public static void main(String args[]) {
        Popup_AddColumn p = new Popup_AddColumn();
    }
}

class Popup_Student {
    JButton callingButton;
    static JFrame f;
    static JButton b;
    static JLabel L1;
    static JLabel L2;
    static JLabel L3;
    static JLabel L4;
    static JLabel L5;
    static JLabel L6;
    static JLabel L7;
    static JLabel L8;
    public Popup_Student(){
        f = new JFrame("Student Info");
        L1 = new JLabel("Name");
        L2 = new JLabel("Email");
        L3 = new JLabel("StudentID");
        b = new JButton("Done");
        L4 = new JLabel("");
        L5 = new JLabel("");
        L6 = new JLabel("");
        //JPanel p = new JPanel(new GridLayout(4,2));
        b.setPreferredSize(new Dimension(10,25));
        JPanel p = new JPanel(new GridLayout(6,2));
        p.add(L1);
        p.add(L4);
        p.add(L2);
        p.add(L5);
        p.add(L3);
        p.add(L6);
        p.add(b);
        f.add(p);
        f.setSize(300, 300);
        f.setLayout(new BorderLayout());
        f.getContentPane().add(p, BorderLayout.NORTH);
        f.setVisible(true);
        // make the JFrame appear in the middle of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation(dim.width/2-f.getSize().width/2, dim.height/2-f.getSize().height/2);
    }
    public static void main(String args[]) {
        Popup_Student p = new Popup_Student();
    }
}

class Popup_Total {
    static JButton jButton;
    static JFrame f;
    static JLabel L1;
    static JTextField t1;
    public Popup_Total(){
        JButton callingButton;
        jButton = new JButton("Done");
        f = new JFrame("Total points");
        L1 = new JLabel("Total points");
        t1 = new JTextField(16);
        JPanel p = new JPanel(new GridLayout(2,1));
        p.add(L1);
        p.add(t1);
        p.add(jButton);
        f.add(p);
        f.setSize(300, 300);
        f.setLayout(new BorderLayout());
        f.getContentPane().add(p, BorderLayout.NORTH);
        f.setVisible(true);
        // make the JFrame appear in the middle of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation(dim.width/2-f.getSize().width/2, dim.height/2-f.getSize().height/2);
    }
    public static void main(String args[]) {
        Popup_Total p = new Popup_Total();
    }
}
