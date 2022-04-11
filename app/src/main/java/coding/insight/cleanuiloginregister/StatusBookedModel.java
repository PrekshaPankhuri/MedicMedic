package coding.insight.cleanuiloginregister;

public class StatusBookedModel {

    String HospitalName,DoctorName,Status,Problem,ProblemDescription,Date,Time,DoctorType;

    StatusBookedModel(){}

    public StatusBookedModel(String hospitalName, String doctorName, String status, String problem, String problemDescription, String date, String time, String doctorType) {
        HospitalName = hospitalName;
        DoctorName = doctorName;
        Status = status;
        Problem = problem;
        ProblemDescription = problemDescription;
        Date = date;
        Time = time;
        DoctorType = doctorType;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String hospitalName) {
        HospitalName = hospitalName;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
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

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDoctorType() {
        return DoctorType;
    }

    public void setDoctorType(String doctorType) {
        DoctorType = doctorType;
    }
}
