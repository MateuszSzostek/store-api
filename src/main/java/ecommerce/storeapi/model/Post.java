package ecommerce.storeapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_post")
public class Post {

    @Id
    private String id;
    private String title;
    private String url;
    private String picture;
    @Column(length = 65536)
    private String content;
    private String createdAt;
    private String author;
}
