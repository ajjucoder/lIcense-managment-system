package Model;

public class User {
    private String name;
    private String address;
    private String phone;
    private String citizenshipNumber;
    private int age;
    private String password;
    private String role;

    public User(String name, String address, String phone, String citizenshipNumber, int age, String password, String role) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.citizenshipNumber = citizenshipNumber;
        this.age = age;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return name;
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getCitizenshipNumber() { return citizenshipNumber; }
    public int getAge() { return age; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
}
