public class Contract {
    private int contract_id;
    private int customer_id;
    private int car_id;
    private int rental_start_date;
    private int rental_end_date;
    int carRequest_counter=0;
    String[] contracts;
    String[] carRequests;
    public Contract(int number){contracts=new String[1];carRequests=new String[1];}
    public void AddCarRequest(int office_id,int customer_id,String brand, String model,String class_,String start_date,
                              String end_date){
        carRequests[carRequest_counter]=office_id+";"+customer_id+";"+brand+";"+model+";"+class_+";"+start_date+";"+
                end_date;
        carRequest_counter++;

    }
    public void AddContract(){

    }
    public void ListCarRequests(){
        for (String request : carRequests)
            System.out.println(request);

    }
}
