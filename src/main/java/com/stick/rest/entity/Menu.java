package com.stick.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@Table(name = "menu")
public class Menu extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = true)
    private Integer parentMenuId;

    @Column(nullable = true)
    private String target;

    @Column(nullable = false)
    private String url;

    @Column(nullable = true)
    private Boolean isUse;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.parentMenuId = this.parentMenuId == null
                ? 0 : this.parentMenuId;

        this.target = this.target == null
                ? "_blank" : this.target;

        this.isUse = this.isUse == null || this.isUse;
    }
}
