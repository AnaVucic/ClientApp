package clientapp.view.controller;

import clientapp.communication.Communication;
import clientapp.view.form.AddPersonForm;
import commonlib.domain.Person;
import java.awt.event.ActionEvent;
import java.util.regex.Pattern;
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
            try {
                validatePerson();
                generatePerson();
                person.setPersonID(Long.MIN_VALUE);
                generatedID = Communication.getInstance().savePerson(person);
                person.setPersonID(generatedID);
                JOptionPane.showMessageDialog(form, "Saved an owner with ID: " + person.getPersonID(), "Success", JOptionPane.INFORMATION_MESSAGE);
                parentController.populateTablePersons(null);
                form.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(form, "System was unable to save an owner with given parameters\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void validatePerson() throws Exception {
        String error = "";

        if (form.getTxtFirstname().getText().isEmpty() || form.getTxtFirstname().getText().length() < 2) {
            error += "Firstname must be at least 2 characters long!\n";
        }
        if (form.getTxtLastname().getText().isEmpty() || form.getTxtLastname().getText().length() < 2) {
            error += "Lastname must be at least 2 characters long!\n";
        }
        if (form.getTxtContact().getText().isEmpty() | form.getTxtContact().getText().length() < 11 
                || !Pattern.matches("\\d{3} \\d{3} \\d{3}", form.getTxtContact().getText())) {
            error += "Contact number must be in fomrat 000 000 000!\n";
        }

        if (!error.isEmpty()) {
            throw new Exception(error);
        }
    }

    private void generatePerson() {
        person.setFisrtname(form.getTxtFirstname().getText().trim());
        person.setLastname(form.getTxtLastname().getText().trim());
        person.setContactNumber(form.getTxtContact().getText().trim());
    }

}
