package clientapp.view.controller;

import clientapp.communication.Communication;
import clientapp.view.coordinator.MainCoordinator;
import clientapp.view.form.SalonsForm;
import clientapp.view.form.component.table.SalonTableModel;
import commonlib.domain.City;
import commonlib.domain.Salon;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;


public class SalonsController {

    private final SalonsForm form;
    private List<Salon> salons;
    
    public SalonsController(SalonsForm form) {
        this.form = form;
        addActionListeners();
    }

    public void openForm() {
        form.setVisible(true);
        populateTableSalons(null);
    }

    private void addActionListeners() {
        form.btnSearchActionListener((ActionEvent e) -> {
            String filter = form.getTxtSearch().getText().trim().toLowerCase();
            Salon s = new Salon();
            s.setAddress(filter);
            try {
                salons = Communication.getInstance().findSalons(s);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(form, "Error while getting salons from database.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            if (salons.isEmpty()) {
                populateTableSalons(null);
                JOptionPane.showMessageDialog(form, "No salons found with that address.", "No salons found!", JOptionPane.INFORMATION_MESSAGE);
                form.getTxtSearch().setText("");
            } else {
                populateTableSalons(salons);
                JOptionPane.showMessageDialog(form, "System has found salons with given parameters.", "Salons found!", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        form.btnResetActionListener((ActionEvent e) -> {
            populateTableSalons(null);
        });
        
        form.btnAddActionListener((ActionEvent e) -> {
            MainCoordinator.getInstance().openAddsalonForm(this);
        });
        
       form.btnEditActionListener((ActionEvent e) -> {
            int row = form.getTblSalons().getSelectedRow();
            if (row != -1) {
                Long id = (Long) form.getTblSalons().getValueAt(row, 0);
                MainCoordinator.getInstance().openEditSalonForm(id, this);
            } else {
                JOptionPane.showMessageDialog(form, "You must select a salon to edit.", "Select an owner", JOptionPane.INFORMATION_MESSAGE);

            }
        });
        
        form.btnRemoveActionListener((ActionEvent e) -> {
            int row = form.getTblSalons().getSelectedRow();
            if (row != -1) {
                SalonTableModel atm = (SalonTableModel) (form.getTblSalons().getModel());
                Salon s = atm.getSalon(row);
                try {
                    Communication.getInstance().removeSalon(s);
                } catch (Exception ex) {
                    Logger.getLogger(AppointmentsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(form, "System has successfully deleted salon with id " + s.getSalonID());
                populateTableSalons(null);
            } else {
                JOptionPane.showMessageDialog(form, "You must select a salon to delete.", "Select a salon", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public void populateTableSalons(List<Salon> salons) {
        SalonTableModel model;
        try {
            if (salons == null){
                this.salons = Communication.getInstance().getAllSalons();
                model = new SalonTableModel(this.salons);
            } else {
                model = new SalonTableModel(salons);
            }
            form.getTblSalons().setModel(model);
            TableRowSorter sorter = new TableRowSorter(model);
            form.getTblSalons().setRowSorter(sorter);
        } catch (Exception ex) {
            System.out.println("Exception while getting salons from database:\n" + ex.getMessage());
        }
    }
}
