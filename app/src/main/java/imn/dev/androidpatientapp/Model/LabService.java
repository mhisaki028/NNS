package imn.dev.androidpatientapp.Model;

public class LabService {

    String service_id;
    String service_name;
    String service_desc;
    int service_price;


    public LabService(){

    }

    public LabService(String service_id, String service_name, String service_desc, int service_price) {
        this.service_id = service_id;
        this.service_name = service_name;
        this.service_price = service_price;
        this.service_desc = service_desc;
    }

    public String getServiceId() {
        return service_id;
    }

    public String getServiceName() {
        return service_name;
    }

    public int getServicePrice() {
        return service_price;
    }

    public String getService_desc() {
        return service_desc;
    }
}
