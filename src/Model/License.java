package Model;

public class License {
    private String licenseID;
    private String category;
    private String citizenshipNumber;

    public License(String licenseID, String category, String citizenshipNumber) {
        this.licenseID = licenseID;
        this.category = category;
        this.citizenshipNumber = citizenshipNumber;
    }

    public String getLicenseID() { return licenseID; }
    public String getCategory() { return category; }
    public String getCitizenshipNumber() { return citizenshipNumber; }

    @Override
    public String toString() {
        return "LicenseID: " + licenseID + ", Category: " + category;
    }
}
