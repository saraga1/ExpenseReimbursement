package dev.araga.entities;

public class Employee {

    private int employeeId;
    private String fname;
    private String lname;
    private String username;
    private String password;
    private int m_id;

    public Employee() {
    }

    public Employee(int employeeId, String fname, String lname, String username, String password, int m_id) {
        this.employeeId = employeeId;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
        this.m_id = m_id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", m_id=" + m_id +
                '}';
    }
}
