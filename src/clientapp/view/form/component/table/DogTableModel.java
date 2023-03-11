/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientapp.view.form.component.table;

import commonlib.domain.Dog;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ANA
 */
public class DogTableModel extends AbstractTableModel {
    
    private final String[] columnNames = {"ID", "Owner","Breed", "Name"};
    
    private final List<Dog> dogs;

    public DogTableModel(List<Dog> dogs) {
        this.dogs = dogs;
    }
    
    

    @Override
    public int getRowCount() {
        if (dogs == null) {
            return 0;
        }
        return dogs.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Dog d = dogs.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> d.getDogID();
            case 1 -> d.getPerson().getFirstname() + " " + d.getPerson().getLastname();
            case 2 -> d.getBreed().getName();
            case 3 -> d.getName();
            default -> "n/a";
        };
    }
    
    @Override
    public String getColumnName(int column) {
        if (column > columnNames.length) {
            return "n/a";
        }
        return columnNames[column];
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Long.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            default:
                return String.class;
        }
    }
    
    public Dog getDog (int rowIndex){
        return dogs.get(rowIndex);
    }
    
}
