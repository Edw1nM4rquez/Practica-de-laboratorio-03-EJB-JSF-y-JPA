package ec.edu.ups.rest;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbNumberFormat;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;

public class Person {

	private int id;
	
	@JsonbProperty("person-name")
	private String name;
	
	@JsonbProperty(nillable= true)
	private String mail;
	
	@JsonbTransient
	private int age;
	
	@JsonbDateFormat("dd-MM-yyyy")
	private LocalDate registeredDate;
	
	private BigDecimal salary;
	
	@JsonbNumberFormat(locale ="en_US", value = "#0.0")
	public BigDecimal getSalary() {
		return salary;
	}
	
	public Person() {
		
	}
	
	public static Person valueOf(String s) {
		Person u = new Person();
		try {
			String id=s.substring(0,s.indexOf(","));
			u.setId(Integer.valueOf(id));
			u.setName(s.substring(s.indexOf(",") + 1 ));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	public Person(int id, String name, String mail, int age, LocalDate registeredDate, BigDecimal salary) {
		super();
		this.id = id;
		this.name = name;
		this.mail = mail;
		this.age = age;
		this.registeredDate = registeredDate;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public LocalDate getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(LocalDate registeredDate) {
		this.registeredDate = registeredDate;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", name=" + name + ", mail=" + mail + ", age=" + age + ", registeredDate="
				+ registeredDate + ", salary=" + salary + "]";
	}
	
	
	
}
