package coding.insight.cleanuiloginregister;

public class DoctorModel {
    String ID , Name , Hospital , Specification , Expertise, DoctorTime ;

    public DoctorModel(){

    }
    public DoctorModel(String ID, String name, String hospital, String specification, String expertise, String doctorTime) {

        this.ID = ID;
        Name = name;
        Hospital = hospital;
        Specification = specification;
        Expertise = expertise;
        DoctorTime = doctorTime;

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

    public String getHospital() {
        return Hospital;
    }

    public void setHospital(String hospital) {
        Hospital = hospital;
    }

    public String getSpecification() {
        return Specification;
    }

    public void setSpecification(String specification) {
        Specification = specification;
    }

    public String getExpertise() {
        return Expertise;
    }

    public void setExpertise(String expertise) {
        Expertise = expertise;
    }

    public String getDoctorTime() {
        return DoctorTime;
    }

    public void setDoctorTime(String doctorTime) {
        DoctorTime = doctorTime;
    }
}
