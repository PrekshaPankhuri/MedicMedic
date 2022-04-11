package coding.insight.cleanuiloginregister;

public class BookingModel {

    String ID,Name,Address,City,Number;

    BookingModel(){}

    public BookingModel(String ID, String name, String address, String city, String number) {
        this.ID = ID;
        Name = name;
        Address = address;
        City = city;
        Number = number;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
