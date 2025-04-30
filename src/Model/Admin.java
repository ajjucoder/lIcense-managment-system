package Model;

import java.util.List;

public class Admin extends User {
    public Admin(String username, String password) {

        super(username, "non", "non", "admin", 0, password, "Model.Admin");
    }

    public String getUsername() {
        return super.getUsername();
    }

    public void generateReport(List<Application> applications, List<License> licenses) {
        System.out.println("\n--- License Management Report ---");
        int pending = 0, approved = 0, rejected = 0;
        for (Application app : applications) {
            switch (app.getStatus().toLowerCase()) {
                case "pending": pending++; break;
                case "approved": approved++; break;
                case "rejected": rejected++; break;
            }
        }
        System.out.println("Total Applications: " + applications.size());
        System.out.println("Pending: " + pending);
        System.out.println("Approved: " + approved);
        System.out.println("Rejected: " + rejected);
        System.out.println("Total Issued Licenses: " + licenses.size());
    }
}
