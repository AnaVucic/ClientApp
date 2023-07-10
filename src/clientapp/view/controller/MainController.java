package clientapp.view.controller;

import clientapp.communication.Communication;
import clientapp.view.coordinator.MainCoordinator;
import clientapp.view.form.MainForm;
import commonlib.domain.User;
import java.awt.event.ActionEvent;
import clientapp.view.constants.Constant;
import javax.swing.JOptionPane;

public class MainController {

    private final MainForm mainForm;

    public MainController(MainForm mainForm) {
        this.mainForm = mainForm;
        addActionListeners();
    }

    public void openForm() {
        User user = (User) MainCoordinator.getInstance().getParam(Constant.LOGGED_IN_USER);
        mainForm.setLocationRelativeTo(null);
        mainForm.setVisible(true);
        mainForm.getLblCurrentUser().setText(user.getUsername());
    }

    public MainForm getMainForm() {
        return mainForm;
    }

    private void addActionListeners() {
        mainForm.mniAppointmentsActionListener((ActionEvent e) -> {
            MainCoordinator.getInstance().openAppointmentsForm();
        });

        mainForm.mniDogsActionListener((ActionEvent e) -> {
            MainCoordinator.getInstance().openDogsForm();
        });

        mainForm.mniPersonsActionListener((ActionEvent) -> {
            MainCoordinator.getInstance().openPersonsForm();
        });
        
        mainForm.mniSalonsActionListener((ActionEvent) -> {
            MainCoordinator.getInstance().openSalonsForm();
        });
        
        mainForm.btnLogOutActionListener((ActionEvent) -> {
            try {
                    int response = JOptionPane.showConfirmDialog(mainForm, "Are you sure you want to log out?");
                    if (response == JOptionPane.YES_OPTION) {
                        boolean ok = Communication.getInstance().logout((User) MainCoordinator.getInstance().getParam(Constant.LOGGED_IN_USER));
                        if (ok) {
                            JOptionPane.showMessageDialog(mainForm, "Logout successful!", "Message", JOptionPane.INFORMATION_MESSAGE);
                            mainForm.dispose();
                        }

                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainForm, "Logout unsuccessful!", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
        });
        
        
        
        

    }

}
