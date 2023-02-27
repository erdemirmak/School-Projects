import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        int office_counter = 0, employee_counter = 0, car_counter = 0, customer_counter = 0, company_counter=0,
        carRequest_counter=0,contract_counter=0;
        try {
            File f = new File("C:\\CarProject\\src\\input-1.txt");
            Scanner scan = new Scanner(f);
             //while (scan.hasNextLine()) {
               //System.out.println(scan.nextLine());
            //}
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        File f = new File("C:\\CarProject\\src\\input-1.txt");
        Scanner scan = new Scanner(f);
        String line = "";
        while (scan.hasNext()){
            line = scan.nextLine();
            String[] keeper = (String[]) line.split(";");//split the input from ";"

            Office OF = new Office(office_counter);
            Employee EM = new Employee(employee_counter);
            Car CA = new Car(car_counter);
            Customer CU = new Customer(customer_counter);
            Company CO = new Company(company_counter);
            CarRequest CR=new CarRequest(carRequest_counter);
            Contract C=new Contract(contract_counter);

            //for office
            if (keeper[0].equals("addOffice")) {//add office

                OF.getOffice_phone(keeper[1]);
                OF.getAddress(keeper[2]);
                OF.getCity(keeper[3]);
                CO.addOffice(keeper[1], keeper[2], keeper[3]);
                OF.setOffice_id(office_counter);
                office_counter++;
               // CO.printAllOffices();
            }
                if (keeper[0].equals("listOffice")) CO.printAllOffices();//list office


            //employee
            if (keeper[0].equals("addEmployee")) {

                EM.setName(keeper[1]);
                EM.setSurname(keeper[2]);
                EM.setSex(keeper[3]);
                EM.setBirthday(keeper[4]);
                int a = Integer.parseInt(keeper[5]);//to int
                EM.setWhich_office(keeper[5]);
                OF.addEmployee(keeper[1], keeper[2], keeper[3], keeper[4], a);
                EM.setEmployee_id(employee_counter);
                employee_counter++;
                //OF.printAllEmployees(keeper[5]);
            }
            if (keeper[0].equals("listEmployee")) {

               OF.printAllEmployees(keeper[5]);
            }
            //car
            if (keeper[0].equals("addCar")) {

                CA.setBrand(keeper[1]);
                CA.setModel(keeper[2]);
                CA.setClass_(keeper[3]);
                int km = Integer.parseInt(keeper[4]);
                CA.setKm(km);
                int which_office = Integer.parseInt(keeper[5]);
                CA.setWhich_office(which_office);
                OF.addCar(keeper[1], keeper[2], keeper[3], km, which_office);
                CA.setCar_id(car_counter);
                car_counter++;
                int car_list=Integer.parseInt(keeper[5]);
                OF.printAllCars(car_list);

            }

            if (keeper[0].equals("listCar")) {
                if (keeper[1].equals("1"))
                {int car_list=Integer.parseInt("1");
                OF.printAllCars(car_list);}

            }
            //customer
            if (keeper[0].equals("addCustomer")) {
                CU.setName(keeper[1]);
                CU.setSurname(keeper[2]);
                CO.addCustomers(keeper[1], keeper[2]);
                customer_counter++;
                CU.setCustomer_id(customer_counter);
                CO.printAllCustomers();
            }
            if (keeper[0].equals("listCustomer")) {
                CO.printAllCustomers();
            }

            if (keeper[0].equals("deleteOffice")) {
                int q = Integer.parseInt(keeper[1]) - 1;
                CO.deleteOffice(q);
                CO.printAllOffices();

            }
            if (keeper[0].equals("deleteEmployee")) {

                int a = Integer.parseInt(keeper[1]);
                int b = Integer.parseInt(keeper[2]) - 1;
                OF.deleteEmployee(b);
                OF.printAllEmployees("1");
                System.out.println("********2. Ofis *********");
                OF.printAllEmployees("2");

            }
            if (keeper[0].equals("addCarRequest")){
                String[] brands={"Audi","BMW","Mercedes","Honda","Renault","Ferrari"};
                String[] models={"Q7","X3","CLA","Civic","Clio","458"};
                String[] classes={"economy","sports","luxury"};
                Random rand=new Random();
                int upperbound1=5;
                int random= rand.nextInt(upperbound1);
                int upperbound2=3;
                int random2=rand.nextInt(upperbound2);
                if (keeper[3].equals("*")){
                    keeper[3]=brands[random];
                    if (keeper[4].equals("*"))
                        keeper[4]=models[random];
                }
                else
                    CR.setBrand(keeper[3]);
                if (keeper[4].equals("*"))
                {
                    for (int i=0;i<brands.length;i++)
                        if (brands[i].equals(keeper[3]))
                        {
                            keeper[4]=models[i];
                            break;
                        }
                }
                if (keeper[5].equals("*"))
                    keeper[5]=classes[random2];
                int officeID=Integer.parseInt(keeper[1]);
                OF.setOffice_id(officeID);
                int customerID=Integer.parseInt(keeper[2]);
                CU.setCustomer_id(customerID);

                CR.setModel(keeper[4]);
                CR.setClass_(keeper[5]);
                CR.setRental_start_date(keeper[6]);
                CR.setRental_end_date(keeper[7]);
                C.AddCarRequest(officeID,customerID,keeper[3],keeper[4],keeper[5],keeper[6],keeper[7]);
                CR.setCarRequest_id(carRequest_counter);
                carRequest_counter++;
                C.ListCarRequests();


            }


        }

    }
}


