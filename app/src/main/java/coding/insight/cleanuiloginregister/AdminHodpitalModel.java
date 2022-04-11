package coding.insight.cleanuiloginregister;

public class AdminHodpitalModel {
    String Name, Number, Email, Address, City, Beds, BloodBank, Cylinders, ID, type;

    AdminHodpitalModel(){

    }

    public AdminHodpitalModel(String name, String number, String email, String address, String city, String beds, String bloodBank, String cylinders, String ID, String type) {
        Name = name;
        Number = number;
        Email = email;
        Address = address;
        City = city;
        Beds = beds;
        BloodBank = bloodBank;
        Cylinders = cylinders;
        this.ID = ID;
        this.type = type;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getBeds() {
        return Beds;
    }

    public void setBeds(String beds) {
        Beds = beds;
    }

    public String getBloodBank() {
        return BloodBank;
    }

    public void setBloodBank(String bloodBank) {
        BloodBank = bloodBank;
    }

    public String getCylinders() {
        return Cylinders;
    }

    public void setCylinders(String cylinders) {
        Cylinders = cylinders;
    }
}
