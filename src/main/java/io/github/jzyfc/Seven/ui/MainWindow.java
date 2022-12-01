package io.github.jzyfc.Seven.ui;

import io.github.jzyfc.Seven.data.*;
import io.github.jzyfc.Seven.serialization.DataFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MainWindow extends JFrame {

    private final String FILE_BASE_ROOT = "data/";

    private final DataFile<Teacher> teacherDataFile
            = new DataFile<>(new File(FILE_BASE_ROOT + "teacher.data"), Teacher::new);

    private final DataFile<Student> studentDataFile
            = new DataFile<>(new File(FILE_BASE_ROOT + "student.data"), Student::new);

    private final DataFile<Course> courseDataFile
            = new DataFile<>(new File(FILE_BASE_ROOT + "course.data"), Course::new);

    private final DataFile<Schedule> scheduleDataFile
            = new DataFile<>(new File(FILE_BASE_ROOT + "schedule.data"), Schedule::new);

    private final DataFile<ElectiveCourse> electiveDataFile
            = new DataFile<>(new File(FILE_BASE_ROOT + "electiveCourse.data"), ElectiveCourse::new);

    private ArrayList<Teacher> teacherList;
    private ArrayList<Student> studentList;
    private ArrayList<Course> courseList;
    private ArrayList<Schedule> scheduleList;
    private ArrayList<ElectiveCourse> electiveList;

    public MainWindow() throws HeadlessException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setSize(800, 600);

        this.setTitle("管理系统");

        loadFile();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveFile();
            }
        });

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("教师信息", new DataPanel<>(this, "教师", teacherList, Teacher::new));
        tabbedPane.add("学生信息", new DataPanel<>(this, "学生", studentList, Student::new));
        tabbedPane.add("课程信息", new DataPanel<>(this, "课程", courseList, Course::new));
        tabbedPane.add("排课信息", new DataPanel<>(this, "排课", scheduleList, Schedule::new));
        tabbedPane.add("选课信息", new DataPanel<>(this, "选课", electiveList, ElectiveCourse::new));

        this.add(tabbedPane);

        JMenuBar menuBar = new JMenuBar();
        JMenu queryMenu = new JMenu("查询");
        menuBar.add(queryMenu);
        JMenuItem studentElectiveQueryMenuItem = new JMenuItem("查询学生选课");
        studentElectiveQueryMenuItem.addActionListener(e -> {
            System.out.println("Clicked");

            JDialog queryDialog = new JDialog(this, true);
            queryDialog.setTitle("学生选课查询");
            queryDialog.getContentPane().setLayout(new BoxLayout(queryDialog.getContentPane(), BoxLayout.Y_AXIS));

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            JLabel label = new JLabel("学生姓名：");
            JTextField textField = new JTextField(20);
            label.setLabelFor(textField);
            panel.add(label);
            panel.add(textField);
            queryDialog.add(panel);

            JButton button = new JButton("查询");
            button.addActionListener(buttonEvent -> {
                String name = textField.getText();
                Student student = null;
                for (Student s : studentList) {
                    if (s.getName().equals(name)) {
                        student = s;
                    }
                }
                if (student == null) {
                    JOptionPane.showMessageDialog(this, "没有找到学生",
                            "警告", JOptionPane.PLAIN_MESSAGE);
                } else {
                    String studentID = student.getStudentID();
                    List<String> classList = electiveList.stream()
                            .filter(electiveCourse -> electiveCourse.getStudentID().equals(studentID))
                            .map(ElectiveCourse::getScheduleID)
                            .toList();
                    Vector<String> header = new Vector<>();
                    header.add("课程名");
                    header.add("教师");
                    header.add("上课地点");
                    Vector<Vector<String>> v = new Vector<>();
                    classList.forEach(scheduleID -> {
                        scheduleList.forEach(schedule -> {
                            if (schedule.getScheduleID().equals(scheduleID)) {
                                String classroom = schedule.getClassroom();
                                String courseID = schedule.getCourseID();
                                String teacherID = schedule.getTeacherID();
                                String courseName = null;
                                String teacherName = null;
                                for (Course course : courseList) {
                                    if (course.getCourseID().equals(courseID)) {
                                        courseName = course.getCourseName();
                                    }
                                }
                                for (Teacher teacher : teacherList) {
                                    if (teacher.getTeacherID().equals(teacherID)) {
                                        teacherName = teacher.getName();
                                    }
                                }
                                Vector<String> tmp = new Vector<>();
                                tmp.add(courseName);
                                tmp.add(teacherName);
                                tmp.add(classroom);
                                v.add(tmp);
                            }
                        });
                    });
                    JTable table = new JTable(v, header);
                    System.out.println(table.getRowCount());
                    JScrollPane scrollPane = new JScrollPane(table);
                    table.setFillsViewportHeight(true);
                    queryDialog.add(scrollPane);
                    queryDialog.pack();
                    queryDialog.setLocationRelativeTo(this);
                }
            });
            queryDialog.add(button);

            queryDialog.pack();
            queryDialog.setLocationRelativeTo(this);
            queryDialog.setVisible(true);
        });
        queryMenu.add(studentElectiveQueryMenuItem);
        this.setJMenuBar(menuBar);

        this.setVisible(true);
    }

    private void loadFile() {
//        teacherList = new ArrayList<>(List.of(
//                new Teacher("1", "张三", "男", 18, "教授"),
//                new Teacher("2", "李四", "男", 18, "教授"),
//                new Teacher("3", "王五", "男", 18, "教授")
//        ));
//        studentList = new ArrayList<>(List.of(
//                new Student("1", "吴五", "男", 18, "Software")
//        ));
//        courseList = new ArrayList<>(List.of(
//                new Course("1", "编译原理", 96),
//                new Course("2", "编译原", 96),
//                new Course("3", "编译", 96)
//        ));
//        scheduleList = new ArrayList<>(List.of(
//                new Schedule("1", "1", "1", "103"),
//                new Schedule("2", "2", "2", "104"),
//                new Schedule("3", "3", "3", "105")
//        ));
//        electiveList = new ArrayList<>(List.of(
//                new ElectiveCourse("1", "1", "1"),
//                new ElectiveCourse("2", "1", "2"),
//                new ElectiveCourse("3", "1", "3")
//        ));
        teacherList = new ArrayList<>(teacherDataFile.readFile());
        studentList = new ArrayList<>(studentDataFile.readFile());
        courseList = new ArrayList<>(courseDataFile.readFile());
        scheduleList = new ArrayList<>(scheduleDataFile.readFile());
        electiveList = new ArrayList<>(electiveDataFile.readFile());
    }

    private void saveFile() {
        System.out.println("Saving files");
        JDialog dialog = new JDialog(this, false);
        JLabel label = new JLabel("保存文件中");
        dialog.add(label);
        dialog.setLocationRelativeTo(this);
        dialog.setSize(label.getMaximumSize());
        dialog.setVisible(true);
        teacherDataFile.writeFile(teacherList);
        studentDataFile.writeFile(studentList);
        courseDataFile.writeFile(courseList);
        scheduleDataFile.writeFile(scheduleList);
        electiveDataFile.writeFile(electiveList);
        dialog.dispose();
        this.dispose();
        System.exit(0);
    }
}
