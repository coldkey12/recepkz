package kz.don.recepkz.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "COMMENTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "TEXT", nullable = false)
    private String text;

    @Column(name = "RECIPE_ID", nullable = false)
    private Long recipeId;

    private LocalDateTime postDate;

    @PrePersist
    public void prePersist(){
        postDate = LocalDateTime.now();
    }
}
