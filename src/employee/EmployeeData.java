package employee;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EmployeeData {

    private final IntegerProperty sn;
    private final StringProperty name;
    private final StringProperty tel;
    private final StringProperty password;
    private final StringProperty address;


    public EmployeeData(int sn, String name, String tel, String password, String address) {
        this.sn = new SimpleIntegerProperty(sn);
        this.name =  new SimpleStringProperty(name);
        this.tel =  new SimpleStringProperty(tel);
        this.password =  new SimpleStringProperty(password);
        this.address =  new SimpleStringProperty(address);
    }

    public int getSn() {
        return sn.get();
    }

    public IntegerProperty snProperty() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn.set(sn);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getTel() {
        return tel.get();
    }

    public StringProperty telProperty() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel.set(tel);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }
}
