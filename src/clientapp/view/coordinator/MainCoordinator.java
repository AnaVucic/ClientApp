package clientapp.view.coordinator;

import clientapp.view.controller.PersonsController;
import clientapp.view.controller.AddAppointmentController;
import clientapp.view.controller.AddDogController;
import clientapp.view.controller.AddPersonController;
import clientapp.view.controller.AddSalonController;
import clientapp.view.controller.AppointmentsController;
import clientapp.view.controller.DogsController;
import clientapp.view.controller.EditAppointmentController;
import clientapp.view.controller.EditDogController;
import clientapp.view.controller.EditPersonController;
import clientapp.view.controller.EditSalonController;
import clientapp.view.controller.LoginController;
import clientapp.view.controller.MainController;
import clientapp.view.controller.SalonsController;
import clientapp.view.form.AddAppointmentForm;
import clientapp.view.form.AddDogForm;
import clientapp.view.form.AddPersonForm;
import clientapp.view.form.AddSalonForm;
import clientapp.view.form.AppointmentsForm;
import clientapp.view.form.DogsForm;
import clientapp.view.form.EditAppointmentForm;
import clientapp.view.form.EditDogForm;
import clientapp.view.form.EditPersonForm;
import clientapp.view.form.EditSalonForm;
import clientapp.view.form.LoginForm;
import clientapp.view.form.MainForm;
import clientapp.view.form.PersonsForm;
import clientapp.view.form.SalonsForm;
import java.util.HashMap;
import java.util.Map;

public class MainCoordinator {

    private static MainCoordinator instance;
    
    private final MainController mainController;
    
    private final Map<String, Object> params;


    private MainCoordinator() {
        mainController = new MainController(new MainForm());
        params = new HashMap<>();
    }

    public static MainCoordinator getInstance() {
        if (instance == null) {
            instance = new MainCoordinator();
        }
        return instance;
    }

    public void addParam(String name, Object key) {
        params.put(name, key);
    }

    public Object getParam(String name) {
        return params.get(name);
    }

    // LOGIN FORM
    public void openLoginForm() {
        LoginController loginController = new LoginController(new LoginForm());
        loginController.openForm();
    }

    // MAIN FORM
    public void openMainForm() {
        mainController.openForm();
    }

    public MainController getMainController() {
        return mainController;
    }
    
    // APPOINTMENT FORM
    public void openAppointmentsForm() {
        AppointmentsController appointmentController = new AppointmentsController(new AppointmentsForm());
        appointmentController.openForm();
    }
    
    // EDIT APPOINTMENT FORM
    public void openEditAppointmentForm(Long id, AppointmentsController parentController) {
        EditAppointmentController editController = new EditAppointmentController(new EditAppointmentForm(id, parentController));
        editController.openForm();
    }
    
    // ADD APPOINTMENT FORM
    public void openAddAppointmentForm(AppointmentsController parentController) {
        AddAppointmentController addController = new AddAppointmentController(new AddAppointmentForm(), parentController);
        addController.openForm();
    }
    
    // DOGS FORM
    public void openDogsForm(){
        DogsController dogsController = new DogsController(new DogsForm());
        dogsController.openForm();
    }

    // ADD DOG FORM
    public void openAddDogForm(DogsController parentController) {
        AddDogController addController = new AddDogController(new AddDogForm(), parentController);
        addController.openForm();
    }
    
    // EDIT DOG FORM
    public void openEditDogForm(Long id, DogsController parentController) {
        EditDogController editController = new EditDogController(new EditDogForm(id, parentController));
        editController.openForm();
    }

    // PERSON FORM
    public void openPersonsForm() {
        PersonsController personController = new PersonsController(new PersonsForm());
        personController.openForm();
    }
    
    // EDIT PERSON FORM
    public void openEditPersonForm(Long id, PersonsController parentController) {
        EditPersonController editController = new EditPersonController(new EditPersonForm(id, parentController));
        editController.openForm();
    }

    // ADD PERSON FORM
    public void openAddPersonForm(PersonsController parentController) {
        AddPersonController addController = new AddPersonController(new AddPersonForm(), parentController);
        addController.openForm();
    }

    // SALONS FORM
    public void openSalonsForm() {
        SalonsController salonsController = new SalonsController(new SalonsForm());
        salonsController.openForm();
    }

    // ADD SALON FORM
    public void openAddsalonForm(SalonsController parentController) {
        AddSalonController addController = new AddSalonController(parentController, new AddSalonForm());
        addController.openForm();
    }

    // EDIT SALON FORM
    public void openEditSalonForm(Long id, SalonsController parentController) {
        EditSalonController editController = new EditSalonController(new EditSalonForm(id, parentController));
        editController.openForm();
    }
   
}
