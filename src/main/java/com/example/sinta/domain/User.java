package com.example.sinta.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

/*
You should not use @lombok.Data due performance issue
Instead, use annotations provided below
 */
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@DynamicInsert
@DynamicUpdate
public class User implements Extractable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String nomorKtp;

    private String nama;

    private String noTelp;

    @JsonIgnore
    private String password;

    private Verifikasi verified;

    @Enumerated(EnumType.STRING)
    private final Role role = Role.USER;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<PemesananDalamNegeri> pemesananDalamNegeri;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<PemesananLuarNegeri> pemesananLuarNegeri;
}
