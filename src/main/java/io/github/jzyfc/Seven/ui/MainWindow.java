package io.github.jzyfc.Seven.ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() throws HeadlessException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setSize(800, 600);

//        this.setLayout(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("教师信息", new DataPanel("教师"));
        tabbedPane.add("学生信息", new DataPanel("学生"));
        tabbedPane.add("课程信息", new DataPanel("课程"));
        tabbedPane.add("排课", new JLabel("排课", SwingConstants.CENTER));
        tabbedPane.add("选课", new JLabel("选课", SwingConstants.CENTER));

        this.add(tabbedPane);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        var window = new MainWindow();
    }
}
