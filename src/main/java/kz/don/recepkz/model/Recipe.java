package kz.don.recepkz.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "RECIPES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "DISH_NAME", nullable = false)
    private String dishName;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT", length = 5000, nullable = false)
    private String description;

    @Column(name = "COOK_TIME_MINUTES", nullable = false)
    private Integer cookTimeMinutes;

    @Column(name = "INSTRUCTIONS", columnDefinition = "TEXT", length = 5000, nullable = false)
    private String instructions;

    private LocalDateTime postDate;

    @Column(name = "CATEGORY", nullable = false)
    private String category;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @PrePersist
    private void prePersist(){
        postDate = LocalDateTime.now();
    }

}
