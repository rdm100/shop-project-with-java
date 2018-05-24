package models;

import javax.persistence.*;

@Entity
@Table(name = "till")
public class Till {

    private int id;
    private double cash;

    public Till(double cash) {
        this.cash = cash;
    }
    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column
    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }
}
