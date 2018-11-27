package imn.dev.androidpatientapp.Model;

public class Results {

    private String url, result_name, uploaded_at, uploaded_by;

    public Results(){

    }

    public Results(String url, String result_name, String uploaded_at, String uploaded_by) {
        this.url = url;
        this.result_name = result_name;
        this.uploaded_at = uploaded_at;
        this.uploaded_by = uploaded_by;
    }

    public String getUrl() {
        return url;
    }

    public String getResult_name(){
        return result_name;
    }

    public String getUploaded_at(){
        return uploaded_at;
    }

    public String getUploaded_by(){
        return uploaded_by;
    }

}
