package coding.insight.cleanuiloginregister;

public class FinalModel {

    String DoctorName,Problem,ProblemDescription,Date,Time,DoctorType;

    FinalModel(){}

    public FinalModel(String doctorName, String problem, String problemDescription, String date, String time, String doctorType) {
        DoctorName = doctorName;
        Problem = problem;
        ProblemDescription = problemDescription;
        Date = date;
        Time = time;
        DoctorType = doctorType;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
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
