package coding.insight.cleanuiloginregister;

public class StatusModel {

    String Problem,ProblemDescription,DoctorName,HospitalName,DoctorType,Time,Date;

    StatusModel(){}

    public StatusModel(String problem, String problemDescription, String doctorName, String hospitalName, String doctorType, String time, String date) {
        Problem = problem;
        ProblemDescription = problemDescription;
        DoctorName = doctorName;
        HospitalName = hospitalName;
        DoctorType = doctorType;
        Time = time;
        Date = date;
    }


    public String getProblem() {
        return Problem;
    }

    public void setProblem(String problem) {
        Problem = problem;
    }

    public String getProblemDescription() {
        return ProblemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        ProblemDescription = problemDescription;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String hospitalName) {
        HospitalName = hospitalName;
    }

    public String getDoctorType() {
        return DoctorType;
    }

    public void setDoctorType(String doctorType) {
        DoctorType = doctorType;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}