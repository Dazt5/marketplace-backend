package com.dazt.products.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * Product.
 *
 * @author David Alvarez.
 * @version 1.0.0, 20-09-2022
 */
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Product implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 6922576685008410979L;

    /** id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private BigInteger id;

    /** name. */
    @Column(length = 50, nullable = false)
    private String name;

    /** description. */
    @Column(length = 200, nullable = false)
    private String description;

    /** stock. */
    @Column(nullable = false)
    private Integer stock;

    /** price. */
    @Column(nullable = false)
    private BigDecimal price;

    /** updateTime. */
    @UpdateTimestamp
    @Column(nullable = false, name = "update_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime updateTime;

    /** createTime. */
    @CreationTimestamp
    @Column(nullable = false, name = "create_time", columnDefinition = "TIMESTAMP WITH TIME ZONE", updatable = false)
    private LocalDateTime createTime;

}