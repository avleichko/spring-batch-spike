package com.adidas.services.inventory.inventoryWorker.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Entity
@XmlRootElement(name = "student")
public class Student {

    @javax.persistence.Id
    @GeneratedValue
    private Long Id;
    private String emailAddress;
    private String name;
    private String purchasedPackage;

    public Student(String emailAddress, String name, String purchasedPackage) {
        this.emailAddress = emailAddress;
        this.name = name;
        this.purchasedPackage = purchasedPackage;
    }

    public Student() {
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurchasedPackage() {
        return purchasedPackage;
    }

    public void setPurchasedPackage(String purchasedPackage) {
        this.purchasedPackage = purchasedPackage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student that = (Student) o;
        return Objects.equals(emailAddress, that.emailAddress) &&
                Objects.equals(name, that.name) &&
                Objects.equals(purchasedPackage, that.purchasedPackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress, name, purchasedPackage);
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "emailAddress='" + emailAddress + '\'' +
                ", name='" + name + '\'' +
                ", purchasedPackage='" + purchasedPackage + '\'' +
                '}';
    }
}
