package clientapp.view.controller;

import clientapp.communication.Communication;
import clientapp.view.form.PersonsForm;
import clientapp.view.form.component.table.PersonTableModel;
import commonlib.domain.Person;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;

public class PersonsController {
    
    private final PersonsForm form;
    private List<Person> persons;

    public PersonsController(PersonsForm form) {
        this.form = form;
        addActionListeners();
    }

    public void openForm() {
        form.setVisible(true);
        populateTablePersons();
    }

    private void populateTablePersons() {
        try {
            persons = Communication.getInstance().getAllPersons();
            PersonTableModel model = new PersonTableModel(persons);
            form.getTblPersons().setModel(model);
            TableRowSorter sorter = new TableRowSorter(model);
            form.getTblPersons().setRowSorter(sorter);
        } catch (Exception ex) {
            System.out.println("Exception while getting persons from database:\n" + ex.getMessage());
        }
    }
    
    private void addActionListeners() {
        
        form.btnRemovePersonActionListener((ActionEvent e) -> {
            int row = form.getTblPersons().getSelectedRow();
            if (row != -1) {
                Long id = (Long) form.getTblPersons().getValueAt(row, 0);
                Person p = new Person();
                p.setPersonID(id);
                try {
                    int option = JOptionPane.showConfirmDialog(form, """
                                                        Removing this person will remove all dogs and appointments associated with them from the database.
                                                        Are you sure you want to proceed?""", "Warning!",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                    if(option == JOptionPane.OK_OPTION)
                        Communication.getInstance().removePerson(p);
                    else
                        return;
                } catch (Exception ex) {
                    System.out.println("Exception occured while removing person:\n" + ex.getMessage());
                }
                JOptionPane.showMessageDialog(form, "System has successfully removed person with id " + p.getPersonID());
                populateTablePersons();
            } else {
                JOptionPane.showMessageDialog(form, "You must select a dog to be removed.", "Select a dog", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        form.btnAddPersonActionListener((ActionEvent e) -> {
            
        });
        
        form.btnEditPersonActionListener((ActionEvent e) -> {
            
        });
        
        form.btnSearchActionListener((ActionEvent e) -> {
            
        });
        
        form.btnResetActionListener((ActionEvent e) -> {
            
        });
        
    }
    
}
