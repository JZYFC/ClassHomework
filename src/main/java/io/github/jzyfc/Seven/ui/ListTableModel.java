package io.github.jzyfc.Seven.ui;

import io.github.jzyfc.Seven.data.Displayable;
import io.github.jzyfc.Seven.data.Modifiable;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ListTableModel<T extends Displayable & Modifiable> extends AbstractTableModel {
    private final ArrayList<T> list;

    private final int columnCount;

    private final List<String> columnHeader;

    public ListTableModel(ArrayList<T> list) throws IllegalArgumentException {
        this.list = list;

        if (this.list.size() == 0) throw new IllegalArgumentException("List为空");
        this.columnCount = this.list.get(0).getDataCount();
        this.columnHeader = this.list.get(0).getDataHeader();
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public String getColumnName(int column) {
        return this.columnHeader.get(column);
    }

    @Override
    public int getColumnCount() {
        return this.columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return list.get(rowIndex).getDataRepresentation().get(columnIndex);
    }

    public void delete(int[] index) {
        for (int i = index.length - 1; i >= 0; --i) {
            this.list.remove(index[i]);
        }
        this.fireTableDataChanged();
    }

    public void add(T obj) {
        this.list.add(obj);
        this.fireTableDataChanged();
    }

    public T get(int i) {
        return this.list.get(i);
    }

    public void replace(int i, T obj) {
        this.list.set(i, obj);
    }
}
