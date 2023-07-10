package clientapp.view.controller;


import clientapp.communication.Communication;
import clientapp.view.form.AddDogForm;
import commonlib.domain.Breed;
import commonlib.domain.Dog;
import commonlib.domain.Person;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JOptionPane;

public class AddDogController {
    
    private final AddDogForm form;
    private final DogsController parentController;
    private List<Person> persons;
    private List<Breed> breeds;
    private Long generatedID;
    private Dog dog;
    
    public AddDogController(AddDogForm form, DogsController parentController) {
        this.form = form;
        this.parentController = parentController;
        addActionListeners();
    }

    private void addActionListeners() {
        form.btnSaveActionListener((ActionEvent e) -> {
            dog = new Dog();
            if (validateFields()) {
                try {
                    Person p = new Person();
                    p.setPersonID(8l);
                    Breed b = new Breed();
                    b.setBreedID(25l);
                    generatedID = Communication.getInstance().saveDog(dog);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(form, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                dog.setDogID(generatedID);
                JOptionPane.showMessageDialog(form, "Saved dog with ID: " + dog.getDogID(), "Success", JOptionPane.INFORMATION_MESSAGE);
                parentController.populateTableDogs(null);
                form.dispose();
            } else {
                dog = null;
                JOptionPane.showMessageDialog(form, "System was unable to save dog with given parameters", "Failure", JOptionPane.WARNING_MESSAGE);
            }
            System.out.println(dog);
        });
    }

    public void openForm() {
        form.setVisible(true);
        form.setLocationRelativeTo(null);
        fillComboPerson();
        fillComboBreed();
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
            System.out.println("Exception while getting persons. " + ex.getMessage());
        }
    }

    private void fillComboBreed() {
        try {
            form.getCmbBreed().removeAllItems();
            form.getCmbBreed().setEnabled(false);
            form.getCmbBreed().addItem(null);
            breeds= Communication.getInstance().getAllBreeds();
            for (Breed b : breeds) {
                form.getCmbBreed().addItem(b);
            }
            form.getCmbBreed().setSelectedItem(null);
            form.getCmbBreed().setEnabled(true);

        } catch (Exception ex) {
            System.out.println("Exception while getting breeds. " + ex.getMessage());
        }
    }

    private boolean validateFields() {
        if(form.getTxtName().getText().isBlank() || form.getTxtName().getText().length() < 2)
            return false;
        if(form.getCmbPerson().getSelectedItem() == null)
            return false;
        if(form.getCmbBreed().getSelectedItem() == null)
            return false;
        dog.setName(form.getTxtName().getText().trim());
        dog.setPerson((Person) form.getCmbPerson().getSelectedItem());
        dog.setBreed((Breed) form.getCmbBreed().getSelectedItem());
        return true;
    }
    
}
