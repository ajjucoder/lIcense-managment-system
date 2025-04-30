package Model;

public class Application {
    private String applicationID;
    private String citizenshipNumber;
    private String requestedCategory;
    private String status;

    public Application(String applicationID, String citizenshipNumber, String requestedCategory, String status) {
        this.applicationID = applicationID;
        this.citizenshipNumber = citizenshipNumber;
        this.requestedCategory = requestedCategory;
        this.status = status;
    }

    public String getApplicationID() { return applicationID; }
    public String getCitizenshipNumber() { return citizenshipNumber; }
    public String getRequestedCategory() { return requestedCategory; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "ApplicationID: " + applicationID + ", Citizenship: " + citizenshipNumber +
                ", Requested Category: " + requestedCategory + ", Status: " + status;
    }
}
