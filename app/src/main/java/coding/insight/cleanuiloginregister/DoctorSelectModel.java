package coding.insight.cleanuiloginregister;

public class DoctorSelectModel {

    String Name,DoctorTime,ID,Specification,Number,Expertise,Hospital;

    DoctorSelectModel(){}

    public DoctorSelectModel(String name, String doctorTime, String ID, String specification, String number, String expertise, String hospital) {
        Name = name;
        DoctorTime = doctorTime;
        this.ID = ID;
        Specification = specification;
        Number = number;
        Expertise = expertise;
        Hospital = hospital;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDoctorTime() {
        return DoctorTime;
    }

    public void setDoctorTime(String doctorTime) {
        DoctorTime = doctorTime;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSpecification() {
        return Specification;
    }

    public void setSpecification(String specification) {
        Specification = specification;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getExpertise() {
        return Expertise;
    }

    public void setExpertise(String expertise) {
        Expertise = expertise;
    }

    public String getHospital() {
        return Hospital;
    }

    public void setHospital(String hospital) {
        Hospital = hospital;
    }
}
