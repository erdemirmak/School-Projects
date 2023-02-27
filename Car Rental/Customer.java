    public class Customer {
    private int customer_id;
        private String name;
        private String surname;
        private int count=0;
        String[] customers;
        public  Customer (int number) {
            customers =new String[1];
        }
        public  Customer (String name,String surname,int customer_id) {
            this.name=name;
            this.surname=surname;
            this.customer_id=customer_id;
        }

        public int getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(int customer_id) {
            this.customer_id = customer_id;
            System.out.print(customer_id+".");
            customer_id++;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getSurname() {
            return surname;
        }
        public void setSurname(String surname) {
            this.surname = surname;
        }


    }


