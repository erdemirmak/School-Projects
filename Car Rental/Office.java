public class Office {
    private int office_id;
    private String office_phone;
    private String address;
    private String city;
    int employee_counter =0;
    String[] employees;
    String[][] cars;
    private int car_counter =0;

    public  Office (int number1) {
        employees=new String[1];cars=new String[10][10];
    }
    public  Office (int office_id, String office_phone,String address,String city) {
        this.address =address;
        this.city=city;
        this.office_phone =office_phone;
        this.office_id=office_id;
    }

    public int getOffice_id(int office_id) {
        return office_id;
    }

    public void setOffice_id(int office_id) {
        this.office_id = office_id;
        System.out.print(office_id+1+".");
        office_id++;
    }

    public String getOffice_phone(String office_phone) {
        return office_phone;
    }
    public void setOffice_phone(String office_phone) {
        this.office_phone = office_phone;
    }
    public String getAddress(String address) {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity(String city) {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }


    public void addEmployee(String name,String surname, String gender , String birthdate,int office_id) {

        employees[employee_counter]=name+";"+surname+";"+gender+";"+birthdate+";"+office_id;

        employee_counter++;

    }
    public void printAllEmployees(String office_id) {
        for (String employee : employees)
            System.out.println(employee);


    }
    public void deleteEmployee(int i){

        employees[i]=null;
    }
    public void addCar(String brand,String model,String class_,int km,int office_id) {
        cars[office_id-1][car_counter]=brand+";"+model+";"+class_+";"+km+";"+office_id;

        car_counter +=1;

    }
    public void printAllCars(int office_id) {
        for(int i=0;i<car_counter;i++) {
            System.out.println(cars[office_id-1][i]);
        }
    }
}
