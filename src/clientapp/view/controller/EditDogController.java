package clientapp.view.controller;

import clientapp.communication.Communication;
import clientapp.view.form.EditDogForm;
import commonlib.domain.Breed;
import commonlib.domain.Dog;
import commonlib.domain.Person;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JOptionPane;

public class EditDogController {

    private final EditDogForm form;
    private final DogsController parentController;
    private Dog dog;
    private List<Person> persons;
    private List<Breed> breeds;

    public EditDogController(EditDogForm form) {
        this.form = form;
        parentController = form.getParentController();
        addActionListeners();
    }

    public void openForm() {
        form.setVisible(true);
        form.setLocationRelativeTo(null);
        try {
            prepareID();
            prepareForm();
            JOptionPane.showMessageDialog(form, "System has loaded the dog!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(form, "Error while getting the owner\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            form.dispose();
        }

    }

    private void addActionListeners() {
        form.btnSaveActionListener((ActionEvent e) -> {
            try {
                validateDog();
                generateDog();
                Communication.getInstance().editDog(dog);
                parentController.populateTableDogs(null);
                JOptionPane.showMessageDialog(form, "System has changed dog with ID: " + dog.getDogID() + "!", "Message", JOptionPane.INFORMATION_MESSAGE);
                form.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(form, "System has not changed the dog\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                form.dispose();
            }
        });
    }

    private void validateDog() throws Exception {
        String error = "";

        if (form.getCmbPerson().getSelectedItem() == null) {
            error += "Owner must be selected!\n";
        }

        if (form.getCmbBreed().getSelectedItem() == null) {
            error += "Breed must be selected!\n";
        }

        if (form.getTxtName().getText().isEmpty()) {
            error += "Name must be filled!\n";
        }

        if (!error.isEmpty()) {
            throw new Exception(error);
        }
    }

    private void generateDog() {
        dog.setName(form.getTxtName().getText());
        dog.setPerson((Person) form.getCmbPerson().getSelectedItem());
        dog.setBreed((Breed) form.getCmbBreed().getSelectedItem());
    }

    private void prepareID() throws Exception {
        Long id = form.getId();
        Dog d = new Dog();
        d.setDogID(id);
        dog = (Dog) Communication.getInstance().findDogs(d).get(0);
        form.getTxtDogId().setText(dog.getDogID().toString());
    }

    private void prepareForm() throws Exception {
        form.getTxtName().setText(dog.getName());
        fillComboOwner();
        fillComboBreed();
    }

    private void fillComboOwner() throws Exception {
        form.getCmbPerson().removeAllItems();
        form.getCmbPerson().setEnabled(false);
        form.getCmbPerson().addItem(null);
        persons = Communication.getInstance().getAllPersons();
        for (Person p : persons) {
            form.getCmbPerson().addItem(p);
        }
        form.getCmbPerson().setSelectedItem(dog.getPerson());
        form.getCmbPerson().setEnabled(true);

    }

    private void fillComboBreed() throws Exception {
        form.getCmbBreed().removeAllItems();
        form.getCmbBreed().setEnabled(false);
        form.getCmbBreed().addItem(null);
        breeds = Communication.getInstance().getAllBreeds();
        for (Breed b : breeds) {
            form.getCmbBreed().addItem(b);
        }
        form.getCmbBreed().setSelectedItem(dog.getBreed());
        form.getCmbBreed().setEnabled(true);

    }

}
