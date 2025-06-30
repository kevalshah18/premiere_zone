package com.pl.premiere_zone.player.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="prem_stats",indexes = {
        @Index(name="idx_team",columnList = "team"),
        @Index(name="idx_position", columnList = "pos"),
        @Index(name="idx_nation",columnList = "nation"),
        @Index(name="idx_player_name",columnList = "player")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name ="Player",nullable = false,unique = true)
    @NotBlank(message = "Player name cannot be blank")
    @Size(min=2,max=100,message = "Player name must be between 2 and 100 characters")
    private String player;

    @Column(name="nation",nullable = false)
    @NotBlank(message = "Player name cannot be blank")
    @Size(max=50)
    private String nation;

    @Column(name = "pos", nullable = false)
    @NotBlank(message = "Position cannot be blank")
    @Pattern(regexp = "^(GK|DF|MF|FW|DF,MF|MF,FW|MF,DF)$",
            message = "Position must be valid football position")
    private String pos;

    @Column(name = "age")
    @Min(value = 16, message = "Age must be at least 16")
    @Max(value = 50, message = "Age cannot exceed 50")
    private Integer age;

    @Column(name = "mp")
    @Min(value = 0, message = "Matches played cannot be negative")
    private Integer mp;

    @Column(name = "starts")
    @Min(value = 0, message = "Starts cannot be negative")
    private Integer starts;

    @Column(name = "min")
    @DecimalMin(value = "0.0", message = "Minutes played cannot be negative")
    private Double min;

    @Column(name = "gls")
    @DecimalMin(value = "0.0", message = "Goals cannot be negative")
    private Double gls;

    @Column(name = "ast")
    @DecimalMin(value = "0.0", message = "Assists cannot be negative")
    private Double ast;

    @Column(name = "pk")
    @DecimalMin(value = "0.0", message = "Penalty kicks cannot be negative")
    private Double pk;

    @Column(name = "crdY")
    @Min(value = 0, message = "Yellow cards cannot be negative")
    private Integer crdY;

    @Column(name = "crdR")
    @Min(value = 0, message = "Red cards cannot be negative")
    private Integer crdR;

    @Column(name = "xG")
    @DecimalMin(value = "0.0", message = "Expected goals cannot be negative")
    private Double xG;

    @Column(name = "xAG")
    @DecimalMin(value = "0.0", message = "Expected assists cannot be negative")
    private Double xAG;

    @Column(name = "team", nullable = false)
    @NotBlank(message = "Team cannot be blank")
    @Size(max = 50)
    private String team;

    @Column(name = "season")
    @Pattern(regexp = "^\\d{4}-\\d{2}$", message = "Season must be in format YYYY-YY (e.g., 2024-25)")
    @Builder.Default
    private String season = "2024-25";

    @Column(name = "is_active")
    @Builder.Default
    private boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Version
    @Column(name = "version", nullable = false)
    private Long version = 0L;

    public double getGoalsPerMatch() {
        return mp != null && mp > 0 ? (gls != null ? gls / mp : 0.0) : 0.0;
    }

    public double getAssistsPerMatch() {
        return mp != null && mp > 0 ? (ast != null ? ast / mp : 0.0) : 0.0;
    }

    public double getMinutesPerMatch() {
        return mp != null && mp > 0 ? (min != null ? min / mp : 0.0) : 0.0;
    }

    public boolean isRegularStarter() {
        return mp != null && starts != null && mp > 0 &&
                (starts.doubleValue() / mp) >= 0.7;
    }

    public double getGoalContribution() {
        return (gls != null ? gls : 0.0) + (ast != null ? ast : 0.0);
    }

    public boolean isPerformingAboveExpected() {
        return gls != null && xG != null && gls > xG;
    }
}
