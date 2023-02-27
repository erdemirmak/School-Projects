public class Company {
    String[] offices;
    String[] customers;
    private int office_counter=0;
    private int customer_counter;
    public  Company (int number) {
        offices=new String[1];
        customers=new String[1];
    }

    public void addOffice(String office_id,String address,String city) {
        offices[office_counter]=office_id+";"+address +";"+city;
        office_counter++;
    }
    public void printAllOffices() {
        for(String office : offices){
            System.out.println(office);}
        }
    public void deleteOffice(int q) {
        offices[q]=null;
    }

    public void addCustomers(String a ,String b) {

        customers[customer_counter]=a+";"+b;
        customer_counter++;
    }
    public void printAllCustomers() {

        for(String customer : customers){
            System.out.println(customer);}
    }
}
