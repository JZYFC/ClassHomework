package io.github.jzyfc.Seven.ui;

import javax.swing.*;
import java.awt.*;

public class DataPanel extends JPanel {

    private final String dataType;

    public DataPanel(String dataType) {
        this.dataType = dataType;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("添加" + dataType);
        addButton.addActionListener(e -> addData());
        JButton modifyButton = new JButton("修改选中" + dataType);
        modifyButton.addActionListener(e -> modifyData());
        JButton deleteButton = new JButton("删除" + dataType);
        deleteButton.addActionListener(e -> deleteData());

        Dimension size = new Dimension(buttonPanel.getMaximumSize().width, addButton.getMinimumSize().height);
        buttonPanel.setMaximumSize(size);

        buttonPanel.add(addButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(deleteButton);
        this.add(buttonPanel);

        JScrollPane tablePane = new JScrollPane();
        tablePane.add(new Button("Placeholder4"));
        this.add(tablePane);
    }

    private void addData() {
        System.out.println("Add button clicked");
    }

    private void modifyData() {

    }

    private void deleteData() {

    }
}
