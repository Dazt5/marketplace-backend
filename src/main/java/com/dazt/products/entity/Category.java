package com.dazt.products.entity;

import java.math.BigInteger;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Category.
 *
 * @author David Alvarez.
 * @version 1.0.0, 12-11-2022
 */
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity(name = "category")
public class Category {

    /** id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private BigInteger id;

    /** categoryCode. */
    @Column(unique = true)
    private String categoryCode;

    /** name. */
    @Column(length = 100)
    private String name;

    /** description. */
    @Column
    private String description;

    /** updateTime. */
    @UpdateTimestamp
    @Column(nullable = false, name = "update_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime updateTime;

    /** createTime. */
    @CreationTimestamp
    @Column(nullable = false, name = "create_time", columnDefinition = "TIMESTAMP WITH TIME ZONE", updatable = false)
    private LocalDateTime createTime;


}