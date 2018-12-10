package imn.dev.androidpatientapp.Model;

public class Bookings {

    String date, lab_name, service_name, lab_image, time;

    public Bookings(){

    }

    public Bookings(String date, String lab_name, String service_name, String lab_image, String time) {
        this.date = date;
        this.lab_name = lab_name;
        this.service_name = service_name;
        this.lab_image = lab_image;
        this.time = time;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getLab_image() {
        return lab_image;
    }

    public void setLab_image(String lab_image) {
        this.lab_image = lab_image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
