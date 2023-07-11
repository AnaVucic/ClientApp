package clientapp.view.controller;

import clientapp.view.form.AppointmentsForm;
import clientapp.view.form.component.table.AppointmentTableModel;
import commonlib.domain.Appointment;
import commonlib.domain.Salon;
import java.util.List;
import clientapp.communication.Communication;
import clientapp.view.coordinator.MainCoordinator;
import commonlib.domain.Dog;
import commonlib.domain.Person;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class AppointmentsController {

    private final AppointmentsForm form;
    private List<Appointment> appointments;
    private List<Salon> salons;
    private List<Dog> dogs;
    private List<Person> persons;

    public AppointmentsController(AppointmentsForm appointmentsForm) {
        this.form = appointmentsForm;
        addActionListeners();
    }
    
    public void openForm() {
        form.setVisible(true);
        populateTableAppointments(null);
        populateComboSalon();
        populateListDogs();
        populateListPersons();
    }

    private void addActionListeners() {
        form.btnAddAppointmentActionListener((ActionEvent e) -> {
            MainCoordinator.getInstance().openAddAppointmentForm(this);
        });

        form.cmbSalonPropertyChangeListener((ActionEvent e) -> {
            Salon selectedSalon = (Salon) form.getCmbSalon().getSelectedItem();
            if (selectedSalon != null) {
                TableRowSorter sorter = new TableRowSorter(form.getTblAppointments().getModel());
                form.getTblAppointments().setRowSorter(sorter);
                sorter.setRowFilter(new RowFilter() {
                    @Override
                    public boolean include(RowFilter.Entry entry) {
                        return entry.getStringValue(5).contains(selectedSalon.toString());
                    }
                });
            }
        });

        form.btnViewAllActionListener((ActionEvent e) -> {
            populateTableAppointments(null);
            form.getTxtDateFilter().setText("");
        });

        form.btnEditAppointmentActionListener((ActionEvent e) -> {
            int row = form.getTblAppointments().getSelectedRow();
            if (row != -1) {
                Long id = (Long) form.getTblAppointments().getValueAt(row, 0);
                MainCoordinator.getInstance().openEditAppointmentForm(id, this);
            } else {
                JOptionPane.showMessageDialog(form, "You must select an appointment to edit.", "Select an appointment", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        form.btnDateDisplayActionListener((ActionEvent e) -> {
            try {
                String dateString = form.getTxtDateFilter().getText();
                DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

                LocalDateTime date = LocalDateTime.parse(dateString + "T00:00:00", formatter);

                Appointment a = new Appointment();
                a.setDateTime(date);
                appointments = Communication.getInstance().findAppointments(a);
                
                if (appointments.isEmpty()) {
                    populateTableAppointments(null);
                    JOptionPane.showMessageDialog(form, "No appointments found on that date.", "No appointments found!", JOptionPane.INFORMATION_MESSAGE);
                    form.getTxtDateFilter().setText("");
                } else {
                    populateTableAppointments(appointments);
                    JOptionPane.showMessageDialog(form, "System has found appointments with given parameters.", "Appointments found!", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(form, "Unable to parse date.\n" + ex.getMessage());
            }
        });

        form.btnDeleteAppointmentActionListener((ActionEvent e) -> {
            int row = form.getTblAppointments().getSelectedRow();
            if (row != -1) {
                AppointmentTableModel atm = (AppointmentTableModel) (form.getTblAppointments().getModel());
                Appointment a = atm.getAppointment(row);
                try {
                    Communication.getInstance().removeAppointment(a);
                } catch (Exception ex) {
                    Logger.getLogger(AppointmentsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(form, "System has successfully deleted appointment with id " + a.getAppointmentID());
                populateTableAppointments(null);
            } else {
                JOptionPane.showMessageDialog(form, "You must select an appointment to delete.", "Select an appointment", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
    
    public void populateTableAppointments(List<Appointment> appointments) {
        AppointmentTableModel model;
        try {
            if(appointments == null) {
                this.appointments = Communication.getInstance().getAllAppointments();
                model = new AppointmentTableModel(this.appointments);
            } else {
                model = new AppointmentTableModel(appointments);
            }
            form.getTblAppointments().setModel(model);
            TableRowSorter sorter = new TableRowSorter(model);
            form.getTblAppointments().setRowSorter(sorter);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(form, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void populateComboSalon() {
        try {
            form.getCmbSalon().removeAllItems();
            form.getCmbSalon().setEnabled(false);
            form.getCmbSalon().addItem(null);
            salons = Communication.getInstance().getAllSalons();
            for (Salon s : salons) {
                form.getCmbSalon().addItem(s);
            }
            form.getCmbSalon().setSelectedItem(null);
            form.getCmbSalon().setEnabled(true);

        } catch (Exception ex) {
            Logger.getLogger(AppointmentsController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void populateListDogs() {
        try {
            dogs = Communication.getInstance().getAllDogs();
            System.out.println(dogs);
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


}
