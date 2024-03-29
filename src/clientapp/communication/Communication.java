package clientapp.communication;

import commonlib.domain.Appointment;
import commonlib.domain.AppointmentService;
import commonlib.domain.Breed;
import commonlib.domain.City;
import commonlib.domain.Dog;
import commonlib.domain.Person;
import commonlib.domain.Salon;
import commonlib.domain.Service;
import commonlib.domain.User;
import java.net.Socket;
import java.util.List;
import transfer.Operation;
import transfer.Receiver;
import transfer.Request;
import transfer.Response;
import transfer.Sender;

public class Communication {

    private final Sender sender;
    private final Receiver receiver;
    private static Communication instance;

    private Communication() throws Exception {
        Socket socket = new Socket("localhost", 9000);
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    // GET INSTANCE
    public static Communication getInstance() throws Exception {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    // LOGIN
    public User login(String username, String password) throws Exception {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Request request = new Request(Operation.LOGIN, user);
        sender.send(request);
        System.out.println("sent");
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (User) response.getResult();
        } else {
            throw response.getException();
        }
    }

    // LOGOUT
    public boolean logout(User user) throws Exception {
        Request request = new Request(Operation.LOGOUT, user);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (boolean) response.getResult();
        } else {
            throw response.getException();
        }
    }

    // GET ALL APPOINTMENTS
    public List<Appointment> getAllAppointments() throws Exception {
        Request request = new Request(Operation.GET_ALL_APPOINTMENTS, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Appointment>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    // SAVE APPOINTMENT
    public Long saveAppointment(Appointment a) throws Exception {
        Request request = new Request(Operation.SAVE_APPOINTMENT, a);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            Long index = (Long) response.getResult();
            return index;
        } else {
            throw response.getException();
        }
    }

    // GET SERVICE
    public Service getService(String name) throws Exception {
        Request request = new Request(Operation.FIND_SERVICES, name);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            Service s = (Service) response.getResult();
            return s;
        } else {
            throw response.getException();
        }
    }

    // GET ALL SALONS
    public List<Salon> getAllSalons() throws Exception {
        Request request = new Request(Operation.GET_ALL_SALONS, null);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            List<Salon> s = (List<Salon>) response.getResult();
            return s;
        } else {
            throw response.getException();
        }
    }

    // GET ALL DOGS
    public List<Dog> getAllDogs() throws Exception {
        Request request = new Request(Operation.GET_ALL_DOGS, null);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            List<Dog> d = (List<Dog>) response.getResult();
            return d;
        } else {
            throw response.getException();
        }
    }

    // GET ALL SERVICES
    public List<Service> getAllServices() throws Exception {
        Request request = new Request(Operation.GET_ALL_SERVICES, null);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            List<Service> s = (List<Service>) response.getResult();
            return s;
        } else {
            throw response.getException();
        }
    }
    
    // FIND APPOINTMENTS
    public List<Appointment> findAppointments(Appointment appointment) throws Exception {
        Request request = new Request(Operation.FIND_APPOINTMENTS, appointment);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Appointment>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    // GET ALL PERSONS
    public List<Person> getAllPersons() throws Exception {
        Request request = new Request(Operation.GET_ALL_PERSONS, null);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            List<Person> p = (List<Person>) response.getResult();
            return p;
        } else {
            throw response.getException();
        }
    }
    
    //UPDATE APPOINTMENT
    public void editAppointment(Appointment appointment) throws Exception {
        Request request = new Request(Operation.EDIT_APPOINTMENT, appointment);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException() == null){
            
        } else {
            throw response.getException();
        }
    }

    // SAVE APPOINTMENT-SERVICE
    public void saveAppointmentService(AppointmentService as) throws Exception {
        Request request = new Request(Operation.SAVE_APPOINTMENT_SERVICE, as);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
        } else {
            throw response.getException();
        }
    }

    // DELETE APPOINTMENT-SERVICE
    public void removeAppointmentService(AppointmentService as) throws Exception {
        Request request = new Request(Operation.DELETE_APPOINTMENT_SERVICE, as);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
        } else {
            throw response.getException();
        }
    }
    
    // DELETE APPOINTMENT
    public void removeAppointment(Appointment a) throws Exception {
        Request request = new Request(Operation.DELETE_APPOINTMENT, a);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
        } else {
            throw response.getException();
        }
    }
    
    // FIND DOGS
    public List<Dog> findDogs(Dog dog) throws Exception {
        Request request = new Request(Operation.FIND_DOGS, dog);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Dog>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    // REMOVE DOG
    public void removeDog(Dog d) throws Exception {
        Request request = new Request(Operation.DELETE_DOG, d);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
        } else {
            throw response.getException();
        }
    }

    // GET ALL BREEDS
    public List<Breed> getAllBreeds() throws Exception {
        Request request = new Request(Operation.GET_ALL_BREEDS, null);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            List<Breed> b = (List<Breed>) response.getResult();
            return b;
        } else {
            throw response.getException();
        }
    }

    // SAVE DOG
    public Long saveDog(Dog d) throws Exception {
        Request request = new Request(Operation.SAVE_DOG, d);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            Long index = (Long) response.getResult();
            return index;
        } else {
            throw response.getException();
        }
    }

    // REMOVE PERSON
    public void removePerson(Person p) throws Exception {
        Request request = new Request(Operation.DELETE_PERSON, p);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
        } else {
            throw response.getException();
        }
    }

    // UPDATE DOG
    public void editDog(Dog dog) throws Exception {
        Request request = new Request(Operation.EDIT_DOG, dog);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException() == null){
            
        } else {
            throw response.getException();
        }
    }
    
    // FIND PERSONS
    public List<Person> findPersons(Person person) throws Exception {
        Request request = new Request(Operation.FIND_PERSONS, person);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Person>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    // UPDATE PERSON
    public void editPerson(Person person) throws Exception {
        Request request = new Request(Operation.EDIT_PERSON, person);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException() == null){
            
        } else {
            throw response.getException();
        }
    }

    // SAVE PERSON
    public Long savePerson(Person person) throws Exception {
        Request request = new Request(Operation.SAVE_PERSON, person);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            Long index = (Long) response.getResult();
            return index;
        } else {
            throw response.getException();
        }

    }

    // FIND SALONS
    public List<Salon> findSalons(Salon salon) throws Exception {
        Request request = new Request(Operation.FIND_SALONS, salon);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Salon>) response.getResult();
        } else {
            throw response.getException();
        }
    }
    
    // ADD SALON
    public Long saveSalon(Salon salon) throws Exception {
        Request request = new Request(Operation.SAVE_SALON, salon);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            Long index = (Long) response.getResult();
            return index;
        } else {
            throw response.getException();
        }
    }

    // GET ALL CITIES
    public List<City> getAllCities() throws Exception {
        Request request = new Request(Operation.GET_ALL_CITIES, null);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            List<City> c = (List<City>) response.getResult();
            return c;
        } else {
            throw response.getException();
        }
    }
    
    // REMOVE SALON
    public void removeSalon(Salon s) throws Exception {
        Request request = new Request(Operation.DELETE_SALON, s);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
        } else {
            throw response.getException();
        }
    }
    
    // UPDATE SALON
    public void editSalon(Salon salon) throws Exception {

        Request request = new Request(Operation.EDIT_SALON, salon);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {

        } else {
            throw response.getException();

        }
    }
    
    // FIND ONE APPOINTMENT 
    public Appointment findAppointment(Appointment a) throws Exception {
        Request request = new Request(Operation.FIND_APPOINTMENT, a);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            return (Appointment) response.getResult();
        } else {
            throw response.getException();

        }
    } 
    
    // FIND ONE DOG
    public Dog findDog(Dog d) throws Exception {
        Request request = new Request(Operation.FIND_DOG, d);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            return (Dog) response.getResult();
        } else {
            throw response.getException();

        }
    } 
    
    // FIND ONE SALON
    public Salon findSalon(Salon s) throws Exception {
        Request request = new Request(Operation.FIND_SALON, s);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            return (Salon) response.getResult();
        } else {
            throw response.getException();

        }
    } 
    
    // FIND ONE PERSON
    public Person findPerson(Person p) throws Exception {
        Request request = new Request(Operation.FIND_PERSON, p);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            return (Person) response.getResult();
        } else {
            throw response.getException();

        }
    }
    
}
