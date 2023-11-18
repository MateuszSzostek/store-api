package ecommerce.storeapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_product")
public class Product {

    @Id
    private String id;
    private String name;
    private String linkToPictures;
    private String tooBigForAParcelLocker;
    private String height;
    private String volume;
    @Column(length = 2048)
    @ElementCollection
    private List<String> pictures;
    private String weight;
    private String deliveryTime;
    private String nextDelivery;
    private String amountInBox;
    private String suggestedRetailPrice;
    @Column(length = 65536)
    private String shortDescription;
    @Column(length = 65536)
    private String description;
    private String category;
    private String discountGroup;
    private String linkToInstruction;
    private String state;
    private String vat;
    private String providerPrice;
    private String price;
    private String code;
    private String length;
    private String barcode;
    @ManyToMany
    private Set<Product> substituteProducts;
    @ManyToMany
    private Set<Product> linkedProducts;
 }
