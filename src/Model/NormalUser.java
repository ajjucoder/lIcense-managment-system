package Model;

import java.util.List;

public class NormalUser extends User {
    public NormalUser(String name, String address, String phone, String citizenshipNumber, int age, String password) {
        super(name, address, phone, citizenshipNumber, age, password, "Normal");
    }

    public void checkStatus(List<Application> applications) {
        System.out.println("\n--- Your Applications ---");
        boolean found = false;
        for (Application app : applications) {
            if (app.getCitizenshipNumber().equals(this.getCitizenshipNumber())) {
                System.out.println(app);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No applications found.");
        }
    }
}
