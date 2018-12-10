package imn.dev.androidpatientapp.Model;

public class Reviews {

    private String patient_name, txtBody, txtDay, txtTime, rating, lab, stars;

    public Reviews(){

    }

    public Reviews(String patient_name, String txtBody, String txtDay, String txtTime, String rating, String lab, String stars) {
        this.patient_name = patient_name;
        this.txtBody = txtBody;
        this.txtDay = txtDay;
        this.txtTime = txtTime;
        this.rating = rating;
        this.lab = lab;
        this.stars = stars;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public String getTxtBody() {
        return txtBody;
    }

    public String getTxtDay() {
        return txtDay;
    }

    public String getTxtTime() {
        return txtTime;
    }

    public String getRating() {
        return rating;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getLab() {
        return lab;
    }

    public void setTxtBody(String txtBody) {
        this.txtBody = txtBody;
    }

    public void setTxtDay(String txtDay) {
        this.txtDay = txtDay;
    }

    public void setTxtTime(String txtTime) {
        this.txtTime = txtTime;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }
}
