package clientapp.view.controller;

import clientapp.communication.Communication;
import clientapp.view.form.EditPersonForm;
import commonlib.domain.Person;
import java.awt.event.ActionEvent;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class EditPersonController {

    private final EditPersonForm form;
    private final PersonsController parentController;
    private Person person;

    public EditPersonController(EditPersonForm editPersonForm) {
        this.form = editPersonForm;
        parentController = form.getParentController();
        addActionListeners();
    }

    public void openForm() {
        form.setVisible(true);
        form.setLocationRelativeTo(null);
        try {
            prepareID();
            prepareForm();
            JOptionPane.showMessageDialog(form, "System has loaded the owner!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(form, "Error while getting the owner\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            form.dispose();
        }

    }

    private void addActionListeners() {
        form.btnSaveActionListener((ActionEvent e) -> {
            try {
                validatePerson();
                generatePerson();
                Communication.getInstance().editPerson(person);
                parentController.populateTablePersons(null);
                JOptionPane.showMessageDialog(form, "System has changed owner with ID: " + person.getPersonID() + "!", "Message", JOptionPane.INFORMATION_MESSAGE);
                form.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(form, "System has not changed the owner\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                form.dispose();
            }
        });
    }

    private void prepareID() throws Exception {
        Long id = form.getId();
        Person p = new Person();
        p.setPersonID(id);
        person = (Person) Communication.getInstance().findPerson(p);
        form.getTxtPersonId().setText(person.getPersonID().toString());
    }

    private void prepareForm() {
        form.getTxtFisrname().setText(person.getFirstname());
        form.getTxtLastname().setText(person.getLastname());
        form.getTxtContactNumber().setText(person.getContactNumber());
        form.getTxtNumberOfAppointments().setText(Integer.toString(person.getAppointment_number()));
    }

    private void validatePerson() throws Exception {
        String error = "";

        if (form.getTxtFisrname().getText().isEmpty() || form.getTxtFisrname().getText().length() < 2) {
            error += "Firstname must be at least 2 characters long!\n";
        }
        if (form.getTxtLastname().getText().isEmpty() || form.getTxtLastname().getText().length() < 2) {
            error += "Lastname must be at least 2 characters long!\n";
        }
        if (form.getTxtContactNumber().getText().isEmpty() | form.getTxtContactNumber().getText().length() < 11 
                || !Pattern.matches("\\d{3} \\d{3} \\d{3}", form.getTxtContactNumber().getText())) {
            error += "Contact number must be in fomrat 000 000 000!\n";
        }

        if (!error.isEmpty()) {
            throw new Exception(error);
        }
    }

    private void generatePerson() {
        person.setFisrtname(form.getTxtFisrname().getText());
        person.setLastname(form.getTxtLastname().getText());
        person.setContactNumber(form.getTxtContactNumber().getText());
        person.setAppointment_number(Integer.parseInt(form.getTxtNumberOfAppointments().getText()));
    }

}
