package clientapp.view.controller;

import clientapp.communication.Communication;
import clientapp.view.coordinator.MainCoordinator;
import clientapp.view.form.DogsForm;
import clientapp.view.form.component.table.DogTableModel;
import commonlib.domain.Dog;
import commonlib.domain.Person;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;

public class DogsController {

    private final DogsForm form;
    private List<Dog> dogs;
    private List<Person> persons;

    public DogsController(DogsForm form) {
        this.form = form;
        addActionListeners();
    }

    public void openForm() {
        form.setVisible(true);
        prepareView();
    }

    private void prepareView() {
        populateTableDogs();
        fillComboPerson();
    }

    public void populateTableDogs() {
        try {
            dogs = Communication.getInstance().getAllDogs();
            DogTableModel model = new DogTableModel(dogs);
            form.getTblDogs().setModel(model);
            TableRowSorter sorter = new TableRowSorter(model);
            form.getTblDogs().setRowSorter(sorter);
        } catch (Exception ex) {
            System.out.println("Exception while getting dogs from database:\n" + ex.getMessage());
        }
    }

    private void fillComboPerson() {
        try {
            form.getCmbPerson().removeAllItems();
            form.getCmbPerson().setEnabled(false);
            form.getCmbPerson().addItem(null);
            persons = Communication.getInstance().getAllPersons();
            for (Person p : persons) {
                form.getCmbPerson().addItem(p);
            }
            form.getCmbPerson().setSelectedItem(null);
            form.getCmbPerson().setEnabled(true);

        } catch (Exception ex) {
            System.out.println("Exception while getting persons from database:\n" + ex.getMessage());
        }
    }

    private void addActionListeners() {
        form.btnSearchActionListener((ActionEvent e) -> {
            Person selectedPerson = (Person) form.getCmbPerson().getSelectedItem();
            if (selectedPerson != null) {
                try {
                    Dog dog = new Dog();
                    dog.setPerson(selectedPerson);
                    dogs = Communication.getInstance().findDogs(dog);
                    if (dogs.isEmpty()) {
                        JOptionPane.showMessageDialog(form,
                                "This owner doesn't currently have any dogs.",
                                "Owner has got no dogs currently",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    DogTableModel model = new DogTableModel(dogs);
                    form.getTblDogs().setModel(model);
                    TableRowSorter sorter = new TableRowSorter(model);
                    form.getTblDogs().setRowSorter(sorter);
                } catch (Exception ex) {
                    System.out.println("Exception occured while finding dogs in database:\n" + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(form, "Select an owner to search by!", "Owner Not Selected", JOptionPane.WARNING_MESSAGE);
            }
        });

        form.btnResetActionListener((ActionEvent e) -> {
            try {
                dogs = Communication.getInstance().getAllDogs();
                DogTableModel model = new DogTableModel(dogs);
                form.getTblDogs().setModel(model);
                TableRowSorter sorter = new TableRowSorter(model);
                form.getTblDogs().setRowSorter(sorter);
            } catch (Exception ex) {
                System.out.println("Exception occured while finding dogs in database:\n" + ex.getMessage());
            }
        });

        form.btnRemoveDogActionListener((ActionEvent e) -> {
            int row = form.getTblDogs().getSelectedRow();
            if (row != -1) {
                Long id = (Long) form.getTblDogs().getValueAt(row, 0);
                Dog d = new Dog();
                d.setDogID(id);
                try {
                    Communication.getInstance().removeDog(d);
                } catch (Exception ex) {
                    System.out.println("Exception occured while removing dog:\n" + ex.getMessage());
                    JOptionPane.showMessageDialog(form, "System was unable to remove dog with id " + d.getDogID());
                    return;
                }
                JOptionPane.showMessageDialog(form, "System has successfully removed dog with id " + d.getDogID());
                populateTableDogs();
            } else {
                JOptionPane.showMessageDialog(form, "You must select a dog to be removed.", "Select a dog", JOptionPane.WARNING_MESSAGE);
            }
        });

        form.btnAddDogActionListener((ActionEvent e) -> {
            MainCoordinator.getInstance().openAddDogForm(this);
        });

        form.btnEditDogActionListener((ActionEvent e) -> {
            // TO BE IMPL..
        });
    }
}
