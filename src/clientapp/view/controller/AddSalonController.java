package clientapp.view.controller;

import clientapp.communication.Communication;
import clientapp.view.form.AddSalonForm;
import commonlib.domain.City;
import commonlib.domain.Salon;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AddSalonController {
    
    private final AddSalonForm form;
    private final SalonsController parentController;
    private Long generatedID;
    private Salon salon;
    private List<City> cities = new ArrayList<>();

    public AddSalonController(SalonsController parentController, AddSalonForm form) {
        this.form = form;
        this.parentController = parentController;
        addActionListeners();
    }

    public void openForm() {
        form.setVisible(true);
        form.setLocationRelativeTo(null);
        fillComboCity();
    }

    private void addActionListeners() {
        form.btnSaveActionListener((ActionEvent e) -> {
            salon = new Salon();
            if (validateSalon()) {
                try {
                    generateSalon();
                    salon.setSalonID(Long.MIN_VALUE);
                    generatedID = Communication.getInstance().saveSalon(salon);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(form, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                salon.setSalonID(generatedID);
                JOptionPane.showMessageDialog(form, "Saved a salon with ID: " + salon.getSalonID(), "Success", JOptionPane.INFORMATION_MESSAGE);
                parentController.populateTableSalons(null);
                form.dispose();
            } else {
                salon = null;
                JOptionPane.showMessageDialog(form, "System was unable to save salon with given parameters", "Failure", JOptionPane.WARNING_MESSAGE);
            }
            System.out.println(salon);
        });
    }

    private void fillComboCity() {
        try {
            form.getCmbCity().removeAllItems();
            form.getCmbCity().setEnabled(false);
            form.getCmbCity().addItem(null);
            cities = Communication.getInstance().getAllCities();
            for (City c : cities) {
                form.getCmbCity().addItem(c);
            }
            form.getCmbCity().setSelectedItem(null);
            form.getCmbCity().setEnabled(true);

        } catch (Exception ex) {
            System.out.println("Exception while getting cities. " + ex.getMessage());
        }
    }

    private boolean validateSalon() {
        if(form.getTxtStreet().getText().isBlank() || form.getTxtStreet().getText().length() < 5)
            return false;
        if(form.getTxtNumber().getText().isBlank())
            return false;
        if(form.getCmbCity().getSelectedItem() == null)
            return false;
        return true;
    }

    private void generateSalon() {
        salon.setAddress(form.getTxtStreet().getText() + " " + form.getTxtNumber().getText());
        salon.setCity((City) form.getCmbCity().getSelectedItem());
    }
    
}
