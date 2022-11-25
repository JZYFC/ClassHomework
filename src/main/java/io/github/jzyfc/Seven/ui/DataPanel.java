package io.github.jzyfc.Seven.ui;

import io.github.jzyfc.Seven.data.Displayable;
import io.github.jzyfc.Seven.data.Modifiable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Display data in a table and provide some buttons
 *
 * @param <T> type will be displayed
 */
public class DataPanel<T extends Displayable & Modifiable> extends JPanel {

    private JFrame mainFrame;

    private Supplier<T> emptyConstructor;

    private final ListTableModel<T> tableModel;

    private final JTable table;

    public DataPanel(JFrame frame, String dataType, ArrayList<T> list, Supplier<T> emptyConstructor) {
        this.mainFrame = frame;
        this.emptyConstructor = emptyConstructor;
        this.tableModel = new ListTableModel<>(list);
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

        JTable table = new JTable(tableModel);
        this.table = table;
        table.setFillsViewportHeight(true);
        JScrollPane tablePane = new JScrollPane(table);
        this.add(tablePane);
    }

    private void addData() {
        T obj = emptyConstructor.get();
        DataModifyDialog<T> dialog = new DataModifyDialog<>(obj.getDataHeader(), mainFrame, obj);
        Optional<T> ret = dialog.fetchData();
        if (ret.isPresent()) {
            obj = ret.get();
            this.tableModel.add(obj);
        }
    }

    private void modifyData() {
        if (table.getSelectedRows().length == 0) {
            JOptionPane.showMessageDialog(this, "没有选择数据",
                    "警告", JOptionPane.PLAIN_MESSAGE);
        } else if (table.getSelectedRows().length != 1) {
            JOptionPane.showMessageDialog(this, "选择了太多数据",
                    "警告", JOptionPane.PLAIN_MESSAGE);
        } else {
            int i = table.getSelectedRow();
            T obj = this.tableModel.get(i);
            DataModifyDialog<T> dialog = new DataModifyDialog<>(obj.getDataHeader(), mainFrame, obj);
            Optional<T> ret = dialog.fetchData();
            if (ret.isPresent()) {
                obj = ret.get();
                this.tableModel.replace(i, obj);
            }
        }
    }

    private void deleteData() {
        if (table.getSelectedRows().length == 0) {
            JOptionPane.showMessageDialog(this, "没有选择数据",
                    "警告", JOptionPane.PLAIN_MESSAGE);
        } else {
            int confirmation = JOptionPane.showConfirmDialog(this, "是否删除选择数据", "确认删除",
                    JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (confirmation == JOptionPane.YES_OPTION) {
                this.tableModel.delete(table.getSelectedRows());
            }
        }
    }
}
