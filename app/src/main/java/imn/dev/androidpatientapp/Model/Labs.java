package imn.dev.androidpatientapp.Model;


public class Labs {
    int lab_id;
    String lab_name, lab_desc, lab_loc, lab_sched, lab_about;

    public Labs() {

    }


    public Labs(int lab_id, String lab_name, String lab_desc, String lab_loc, String lab_sched, String lab_about) {
        this.lab_id = lab_id;
        this.lab_name = lab_name;
        this.lab_desc = lab_desc;
        this.lab_loc = lab_loc;
        this.lab_sched = lab_sched;
        this.lab_about = lab_about;
    }

    public int getLab_id() {
        return lab_id;
    }

    public String getLab_name() {
        return lab_name;
    }

    public String getLab_desc() {
        return lab_desc;
    }

    public String getLab_loc() {
        return lab_loc;
    }

    public String getLab_sched() {
        return lab_sched;
    }

    public String getLab_about() { return lab_about; }

}
