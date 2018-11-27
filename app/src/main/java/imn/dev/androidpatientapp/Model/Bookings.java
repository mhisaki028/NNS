package imn.dev.androidpatientapp.Model;

public class Bookings {

    String patient_name, lab_name, service_name;

    public Bookings(){

    }

    public Bookings(String patient_name, String lab_name, String service_name) {
        this.patient_name = patient_name;
        this.lab_name = lab_name;
        this.service_name = service_name;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getLab_name() {
        return lab_name;
    }

    public void setLab_name(String lab_name) {
        this.lab_name = lab_name;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }
}
