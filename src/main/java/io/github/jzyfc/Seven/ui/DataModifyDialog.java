package io.github.jzyfc.Seven.ui;

import io.github.jzyfc.Seven.data.Modifiable;
import io.github.jzyfc.Seven.utils.SpringUtilities;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataModifyDialog<T extends Modifiable> extends JDialog {

    private boolean confirmed = false;

    private final ArrayList<JTextField> textFields = new ArrayList<>();

    private final T obj;

    public DataModifyDialog(List<String> name, Frame owner, T obj) {
        super(owner, true);
        this.obj = obj;
        List<String> data = obj.toStrings();
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        JPanel panel = new JPanel(new SpringLayout());
        for (int i = 0; i < name.size(); i++) {
            JLabel label = new JLabel(name.get(i), JLabel.TRAILING);
            panel.add(label);
            JTextField textField = new JTextField(data.get(i), 20);
            label.setLabelFor(textField);
            panel.add(textField);
            textFields.add(textField);
        }
        SpringUtilities.makeCompactGrid(panel, name.size(), 2, 6, 6, 6, 6);
        panel.setOpaque(true);
        this.add(panel);

        JPanel buttons = new JPanel();
        JButton okButton = new JButton("确认");
        okButton.addActionListener(e -> {
            this.confirmed = true;
            System.out.println("Confirmed");
            this.dispose();
        });
        JButton cancelButton = new JButton("取消");
        cancelButton.addActionListener(e -> {
            this.confirmed = false;
            this.dispose();
        });
        buttons.add(okButton);
        buttons.add(cancelButton);
        this.add(buttons);

        this.pack();
        this.setLocationRelativeTo(owner);
//        this.setResizable(false);
        this.setVisible(true);
    }

    public Optional<T> fetchData() {
        if (!this.confirmed) return Optional.empty();
        obj.fromStrings(textFields.stream().map(JTextComponent::getText).toList());
        return Optional.of(obj);
    }
}
