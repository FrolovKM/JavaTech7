package com.epam.konstantin_frolov.java.lesson7.task1.models;

public class Employee {
    private String firstName;
    private String lastName;
    private Integer salary;

    public Employee(String firstName, String lastName, Integer salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "[Subject] Employee: \nFirst name: "+
                this.firstName+"\n"+"Last name: "+ this.lastName+"\n"+
                "Salary: "+ this.salary+"\n";
    }
}