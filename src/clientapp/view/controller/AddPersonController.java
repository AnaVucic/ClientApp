package clientapp.view.controller;

import clientapp.communication.Communication;
import clientapp.view.form.AddPersonForm;
import commonlib.domain.Person;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class AddPersonController {

    private final AddPersonForm form;
    private final PersonsController parentController;
    private Long generatedID;
    private Person person;

    public AddPersonController(AddPersonForm addPersonForm, PersonsController parentController) {
        this.form = addPersonForm;
        this.parentController = parentController;
        addActionListeners();
    }

    public void openForm() {
        form.setVisible(true);
        form.setLocationRelativeTo(null);
    }

    private void addActionListeners() {

        form.btnSaveActionListener((ActionEvent e) -> {
            person = new Person();
            if (validatePerson()) {
                try {
                    generatePerson();
                    person.setPersonID(Long.MIN_VALUE);
                    generatedID = Communication.getInstance().savePerson(person);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(form, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                person.setPersonID(generatedID);
                JOptionPane.showMessageDialog(form, "Saved an owner with ID: " + person.getPersonID(), "Success", JOptionPane.INFORMATION_MESSAGE);
                parentController.populateTablePersons(null);
                form.dispose();
            } else {
                person = null;
                JOptionPane.showMessageDialog(form, "System was unable to save owner with given parameters", "Failure", JOptionPane.WARNING_MESSAGE);
            }
            System.out.println(person);
        });
    }

    private boolean validatePerson() {
        if(form.getTxtFirstname().getText().isBlank() || form.getTxtFirstname().getText().length() < 2)
            return false;
        if(form.getTxtLastname().getText().isBlank() || form.getTxtLastname().getText().length() < 2)
            return false;
        if(form.getTxtContact().getText().isBlank() || form.getTxtContact().getText().length() < 11)
            return false;
        return true;
    }

    private void generatePerson() {
        person.setFisrtname(form.getTxtFirstname().getText().trim());
        person.setLastname(form.getTxtLastname().getText().trim());
        person.setContactNumber(form.getTxtContact().getText().trim());
    }

}
