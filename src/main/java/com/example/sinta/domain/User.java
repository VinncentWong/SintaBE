package com.example.sinta.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

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
public class User implements Extractable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String nomorKtp;

    private String nama;

    private String noTelp;

    private String password;

    private Verifikasi verified;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}
