public class Employee {
    private int employee_id;
    private String name;
    private String surname;
    private String sex;
    private String birthday;
    private String which_office;

    private int count =0;
    String[] employees;
    public Employee(int number){
        employees=new String[1];
    }


    public  Employee (String name,String surname,String sex,String birthday,String which_office,int employee_id) {
        this.name=name;
        this.surname=surname;
        this.sex=sex;
        this.birthday=birthday;
        this.which_office =which_office;
        this.employee_id=employee_id;
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
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getWhich_office() {
        return which_office;
    }
    public void setWhich_office(String which_office) {
        this.which_office = which_office;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
        System.out.print(employee_id+1+".");
        employee_id++;
    }
}
