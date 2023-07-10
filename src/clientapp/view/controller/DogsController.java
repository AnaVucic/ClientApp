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
        populateTableDogs(null);
        populateListPersons();
    }

   

    public void populateTableDogs(List<Dog> dogs) {
        DogTableModel model;
        try {
            if (dogs == null) {
                this.dogs = Communication.getInstance().getAllDogs();
                model = new DogTableModel(this.dogs);
            }  else {
                model = new DogTableModel(dogs);
            }
            form.getTblDogs().setModel(model);
            TableRowSorter sorter = new TableRowSorter(model);
            form.getTblDogs().setRowSorter(sorter);
        } catch (Exception ex) {
            System.out.println("Exception while getting dogs from database:\n" + ex.getMessage());
        }
    }

    private void populateListPersons() {
        try {
            persons = Communication.getInstance().getAllPersons();
            System.out.println(persons);
        } catch (Exception ex) {
            System.out.println("Exception while getting persons from database:\n" + ex.getMessage());
        }
    }

    private void addActionListeners() {
        form.btnSearchActionListener((ActionEvent e) -> {
            String filter = form.getTxtSearch().getText().trim().toLowerCase();
            Dog d = new Dog();
            d.setName(filter);
            try{
                dogs = Communication.getInstance().findDogs(d);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(form, "Error while getting dogs from database.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            if(dogs.isEmpty()){
                populateTableDogs(null);
                JOptionPane.showMessageDialog(form, "No dogs found with that name.", "No dogs found!", JOptionPane.INFORMATION_MESSAGE);
                form.getTxtSearch().setText("");
            } else {
                populateTableDogs(dogs);
                JOptionPane.showMessageDialog(form, "System has found dogs with given parameters.", "Dogs found!", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        form.btnResetActionListener((ActionEvent e) -> {
            populateTableDogs(null);
            form.getTxtSearch().setText("");
        });

        form.btnRemoveDogActionListener((ActionEvent e) -> {
            int row = form.getTblDogs().getSelectedRow();
            if (row != -1) {  
                Long id = (Long) form.getTblDogs().getValueAt(row, 0);
                Dog d = new Dog();
                d.setDogID(id);
                int response = JOptionPane.showConfirmDialog(form, "Are you sure you want to remove dog with id " +  id +"?");
                    if (response == JOptionPane.YES_OPTION) {
                        try {
                            Communication.getInstance().removeDog(d);
                            JOptionPane.showMessageDialog(form, "System has successfully removed dog with id " + d.getDogID());
                            populateTableDogs(null);
                        } catch (Exception ex) {
                            System.out.println("Exception occured while removing dog:\n" + ex.getMessage());
                            JOptionPane.showMessageDialog(form, "System was unable to remove dog with id " + d.getDogID());
                        }
                        
                    }
            } else {
                JOptionPane.showMessageDialog(form, "You must select a dog to be removed.", "Select a dog", JOptionPane.WARNING_MESSAGE);
            }
        });

        form.btnAddDogActionListener((ActionEvent e) -> {
            MainCoordinator.getInstance().openAddDogForm(this);
        });

        form.btnEditDogActionListener((ActionEvent e) -> {
            int row = form.getTblDogs().getSelectedRow();
            if (row != -1) {
                Long id = (Long) form.getTblDogs().getValueAt(row, 0);
                MainCoordinator.getInstance().openEditDogForm(id, this);
            } else {
                JOptionPane.showMessageDialog(form, "You must select a dog to edit.", "Select an appointment", JOptionPane.INFORMATION_MESSAGE);

            }
        });
    }
}
