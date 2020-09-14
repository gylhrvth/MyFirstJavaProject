package edu.dc.vorarlberg;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Vector;

public class MainWindow extends JFrame {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable table1;

    public MainWindow() {
        setContentPane(contentPane);
        setSize(400, 200);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = 7;
        for (int column = 1; column <= columnCount; column++) {
            String dayName = DayOfWeek.of(column).getDisplayName(TextStyle.SHORT, Locale.getDefault());
            columnNames.add(dayName);
        }

        // data of the table
        LocalDate startDay = LocalDate.now();
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();

        Vector<Object> vector = new Vector<Object>();
        int i = 1;
        for (; i<startDay.withDayOfMonth(1).getDayOfWeek().getValue(); ++i)
        {
            vector.add("  ");
        }
        for (int j=1; j <= startDay.lengthOfMonth(); ++j)
        {
            if ((i % 7 == 0) || (i % 7 == 6)) {
                vector.add("WE");
            } else {
                vector.add(String.format("%2d", j));
            }
            ++i;
            if (i % 7 == 1)
            {
                data.add(vector);
                vector = new Vector<Object>();
            }
        }
        if (i % 7 != 1) {
            for (; i % 7 != 1; ++i) vector.add("  ");
            data.add(vector);
        }

        table1.setModel(new DefaultTableModel(data, columnNames));

    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
