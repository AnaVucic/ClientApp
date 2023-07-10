package clientapp.view.form.component.table;

import commonlib.domain.Salon;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class SalonTableModel extends AbstractTableModel {

    private final String[] columnNames = {"ID","City Zip Code", "City Name", "Address", };

    private final List<Salon> salons;

    public SalonTableModel(List<Salon> salons) {
        this.salons = salons;
    }

    @Override
    public int getRowCount() {
        if (salons == null) {
            return 0;
        }
        return salons.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Salon s = salons.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> s.getSalonID();
            case 1 -> s.getCity().getZipCode();
            case 2 -> s.getCity().getName();
            case 3 -> s.getAddress();
            
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
        return switch (columnIndex) {
            case 0 -> Long.class;
            case 1 -> String.class;
            case 2 -> String.class;
            case 3 -> String.class;
            default -> String.class;
        };
    }
    
    public Salon getSalon (int rowIndex){
        return salons.get(rowIndex);
    }
    
}
