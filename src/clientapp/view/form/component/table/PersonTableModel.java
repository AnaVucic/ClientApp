package clientapp.view.form.component.table;

import commonlib.domain.Person;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PersonTableModel extends AbstractTableModel {

    private final String[] columnNames = {"ID", "Firstname", "Lastname", "Contact number", "Number of appointments"};

    private final List<Person> persons;

    public PersonTableModel(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public int getRowCount() {
        if (persons == null) {
            return 0;
        }
        return persons.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Person p = persons.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> p.getPersonID();
            case 1 -> p.getFirstname();
            case 2 -> p.getLastname();
            case 3 -> p.getContactNumber();
            case 4 -> p.getAppointment_number();
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
            case 4 -> int.class;
            default -> String.class;
        };
    }
    
    public Person getPerson (int rowIndex){
        return persons.get(rowIndex);
    }
    
}
