package lv.venta.java_sem5.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Table(name="product_table")
@Entity
public class Product {

    @Column(name="Title")
    @NotNull
    @Size(min=3, max=150)
    @Pattern(regexp ="[A-Z]{1}[a-z\\ ]+", message = "1.burtam jabut lielam un tikai latinu burti")
    private String title;

    @Column(name="Description")
    @NotNull
    @Size(min=5, max=400)
    @Pattern(regexp ="[A-Z]{1}[a-z0-9\\ ]+", message = "1.burtam jabut lielam un tikai latinu burti")
    private String description;

    @Column(name="Price")
    @Min(0)
    @Max(1000)
    private float price;

    @Column(name="Quantity")
    @Min(0)
    @Max(1000000)
    private int quantity;

    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }


    public Product(String title, String description, float price, int quantity) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
    public Product() {}
    @Override
    public String toString() {
        return "Product [title=" + title + ", description=" + description + ", price=" + price + ", quantity="
                + quantity + ", id=" + id + "]";
    }
}
