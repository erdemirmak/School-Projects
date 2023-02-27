public class Car {
    private int car_id;
        private String Brand;
        private String Model;
        private String Class_;
        private int km;
        private int which_office;
        String[] cars;

        public Car(int number){
        cars=new String[1];
    }
        public Car(String Brand,String Model,String Class_,int km,int which_office) {
            this.Brand=Brand;
            this.Class_ =Class_;
            this.Model=Model;
            this.km=km;
            this.which_office =which_office;


        }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
        System.out.print(car_id+1+".");
        car_id++;
    }

    public String getBrand() {
            return Brand;
        }

        public void setBrand(String brand) {
            Brand = brand;
        }

        public String getModel() {
            return Model;
        }

        public void setModel(String model) {
            Model = model;
        }

        public String getClass_() {
            return Class_;
        }

        public void setClass_(String class_) {
            Class_ = class_;
        }

        public int getKm() {
            return km;
        }

        public void setKm(int km) {
            this.km = km;
        }

        public int getWhich_office() {
            return which_office;
        }

        public void setWhich_office(int which_office) {
            this.which_office = which_office;
        }

}


