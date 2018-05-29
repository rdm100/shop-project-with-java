package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "drinks")
public class Drink extends Product {
    private int volume;
    private double sugarContent;
    private double alcholContent;
    private int caffineContent;

    public Drink() {
    }

    public Drink(String name, double price,  Stock stock, int volume, double sugarContent, double alcoholContent, int caffeineContent) {
        super(name, price, stock);
        this.volume = volume;
        this.sugarContent = sugarContent;
        this.alcholContent = alcoholContent;
        this.caffineContent = caffeineContent;
    }

    @Column
    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Column
    public double getSugarContent() {
        return sugarContent;
    }

    public void setSugarContent(double sugarContent) {
        this.sugarContent = sugarContent;
    }

    @Column
    public double getAlcholContent() {
        return alcholContent;
    }

    public void setAlcholContent(double alcholContent) {
        this.alcholContent = alcholContent;
    }

    @Column
    public int getCaffineContent() {
        return caffineContent;
    }

    public void setCaffineContent(int caffineContent) {
        this.caffineContent = caffineContent;
    }
}
