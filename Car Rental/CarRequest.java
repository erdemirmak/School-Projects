import java.util.Random;

public class CarRequest {
    private int CarRequest_id;
    private String model;
    private String brand;
    private String class_;
    private String rental_start_date;
    private String rental_end_date;
    String[] carRequests;
    public CarRequest(int number){
    carRequests=new String[1];
    }
    public CarRequest(int CarRequest_id,String model, String brand, String rental_start_date, String rental_end_date,
                      String class_){
        this.CarRequest_id=CarRequest_id;
        this.model=model;
        this.rental_start_date=rental_start_date;
        this.rental_end_date=rental_end_date;
        this.class_=class_;
        this.brand = brand;
    }

    public String getClass_() {
        return class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public int getCarRequest_id() {
        return CarRequest_id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getRental_start_date() {
        return rental_start_date;
    }

    public String getRental_end_date() {
        return rental_end_date;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setCarRequest_id(int carRequest_id) {
        this.CarRequest_id = carRequest_id;
        System.out.print(carRequest_id+1+".CarRequest ");
        carRequest_id++;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setRental_end_date(String rental_end_date) {
        this.rental_end_date = rental_end_date;
    }

    public void setRental_start_date(String rental_start_date) {
        this.rental_start_date = rental_start_date;
    }

}
