package com.example.sinta.app.user.repository;

import com.example.sinta.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query(
            nativeQuery = true,
            value = """
                    UPDATE users
                    SET verified=:verifikasi
                    WHERE id=:id
                    """
    )
    @Modifying
    void updateVerifikasiUser(@Param("id") Long id, @Param("verifikasi") int verifikasi);

    @Query(
                value = """
                SELECT * FROM users WHERE email=:email LIMIT 1
                """,
                nativeQuery = true
    )
    Optional<User> findUserByEmail(@Param("email") String email);

    @Query(
            nativeQuery = true,
            value = """
                    SELECT * FROM users WHERE no_telp=:noTelp LIMIT 1
                    """
    )
    Optional<User> findUserByNoTelp(@Param("noTelp") String noTelp);
}
