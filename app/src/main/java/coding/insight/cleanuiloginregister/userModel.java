package coding.insight.cleanuiloginregister;

public class userModel {

    String Email, ID, Name, Number, Password, type;

    userModel(){

    }

    public userModel(String email, String ID, String name, String number, String password, String type) {
        Email = email;
        this.ID = ID;
        Name = name;
        Number = number;
        Password = password;
        this.type = type;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
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

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
