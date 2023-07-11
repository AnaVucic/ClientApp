package clientapp.view.controller;

import clientapp.communication.Communication;
import clientapp.view.form.EditSalonForm;
import commonlib.domain.City;
import commonlib.domain.Salon;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;


public class EditSalonController {

    private final EditSalonForm form;
    private final SalonsController parentController;
    private Salon salon;
    private List<City> cities = new ArrayList<>();
    
    public EditSalonController(EditSalonForm form) {
        this.form = form;
        parentController = form.getParentController();
        addActionListeners();
        
    }

    public void openForm() {
        form.setVisible(true);
        form.setLocationRelativeTo(null);
        try {
            prepareID();
            fillComboCity();
            prepareForm();
            JOptionPane.showMessageDialog(form, "System has loaded the salon!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(form, "Error while getting the salon\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            form.dispose();
        }
        
    }

    private void addActionListeners() {
        form.btnSaveActionListener((ActionEvent e) -> {
            try {
                validateSalon();
                generateSalon();
                Communication.getInstance().editSalon(salon);
                parentController.populateTableSalons(null);
                JOptionPane.showMessageDialog(form, "System has changed salon with ID: " + salon.getSalonID()+ "!", "Message", JOptionPane.INFORMATION_MESSAGE);
                form.dispose();
            } 
            catch (Exception ex) {
                JOptionPane.showMessageDialog(form, "System has not changed the salon\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                form.dispose();
            }
        });
    }

    private void prepareID() throws Exception {
        Long id = form.getId();
        Salon s = new Salon();
        s.setSalonID(id);
        salon = (Salon) Communication.getInstance().findSalon(s);
        form.getTxtSalonId().setText(salon.getSalonID().toString());
    }

    private void prepareForm() {
        String[] streetParts = salon.getAddress().split(" ");
        String street = String.join(" ", Arrays.copyOfRange(streetParts, 0, streetParts.length-1));
        String number = streetParts[streetParts.length - 1];
        
        form.getTxtStreet().setText(street);
        form.getTxtNumber().setText(number);
        
        form.getCmbCity().setSelectedItem(salon.getCity());
        
    }

    private void fillComboCity() throws Exception {
        form.getCmbCity().removeAllItems();
        form.getCmbCity().setEnabled(false);
        form.getCmbCity().addItem(null);
        cities = Communication.getInstance().getAllCities();
        for (City s : cities) {
            form.getCmbCity().addItem(s);
        }
        form.getCmbCity().setEnabled(true);

    }

    private void validateSalon() throws Exception {
        String error = "";
        
        if (form.getTxtStreet().getText().isEmpty()) {
            error += "Street name must be filled!\n";
        }
        
        if (form.getTxtNumber().getText().isEmpty()) {
            error += "Street number must be filled!\n";
        }
        
        if (form.getCmbCity().getSelectedItem() == null) {
            error += "City must be selected!\n";
        }

        if (!error.isEmpty()) {
            throw new Exception(error);
        }
    }

    private void generateSalon() {
        String address = form.getTxtStreet().getText().trim() + " " + form.getTxtNumber().getText().trim();
        salon.setAddress(address);
        salon.setCity((City) form.getCmbCity().getSelectedItem());
    }
    
}
