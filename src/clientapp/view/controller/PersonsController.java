package clientapp.view.controller;

import clientapp.communication.Communication;
import clientapp.view.coordinator.MainCoordinator;
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
        populateTablePersons(null);
    }

    public void populateTablePersons(List<Person> persons) {
        PersonTableModel model;
        try {
            if (persons == null) {
                this.persons = Communication.getInstance().getAllPersons();
                model = new PersonTableModel(this.persons);
            } else {
                model = new PersonTableModel(persons);
            }
            form.getTblPersons().setModel(model);
            TableRowSorter sorter = new TableRowSorter(model);
            form.getTblPersons().setRowSorter(sorter);
        } catch (Exception ex) {
            System.out.println("Exception while getting owners from database:\n" + ex.getMessage());
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
                    int response = JOptionPane.showConfirmDialog(form, """
                                                        Removing this owner will remove all dogs and appointments associated with them from the database.
                                                        Are you sure you want to proceed?""", "Warning!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (response == JOptionPane.OK_OPTION) {
                        Communication.getInstance().removePerson(p);
                        JOptionPane.showMessageDialog(form, "System has successfully removed owner with id " + p.getPersonID());
                        populateTablePersons(null);
                    }
                } catch (Exception ex) {
                    System.out.println("Exception occured while removing owner:\n" + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(form, "You must select an owner to be removed.", "Select an owner", JOptionPane.WARNING_MESSAGE);
            }
        });

        form.btnAddPersonActionListener((ActionEvent e) -> {
            MainCoordinator.getInstance().openAddPersonForm(this);
        });

        form.btnEditPersonActionListener((ActionEvent e) -> {
            int row = form.getTblPersons().getSelectedRow();
            if (row != -1) {
                Long id = (Long) form.getTblPersons().getValueAt(row, 0);
                MainCoordinator.getInstance().openEditPersonForm(id, this);
            } else {
                JOptionPane.showMessageDialog(form, "You must select an owner to view and edit.", "Select an owner", JOptionPane.INFORMATION_MESSAGE);

            }
        });

        form.btnSearchActionListener((ActionEvent e) -> {
            String filter = form.getTxtFilterName().getText().trim().toLowerCase();
            Person p = new Person();
            p.setFisrtname(filter);
            p.setLastname(filter);
            try {
                persons = Communication.getInstance().findPersons(p);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(form, "Error while getting owners from database.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            if (persons.isEmpty()) {
                populateTablePersons(null);
                JOptionPane.showMessageDialog(form, "No owners found with that first or last name.", "No owners found!", JOptionPane.INFORMATION_MESSAGE);
                form.getTxtFilterName().setText("");
            } else {
                populateTablePersons(persons);
                JOptionPane.showMessageDialog(form, "System has found owners with given parameters.", "Owners found!", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        form.btnResetActionListener((ActionEvent e) -> {
            populateTablePersons(null);
            form.getTxtFilterName().setText("");
        });
    }

}
