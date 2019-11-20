package com.saojudas.adocao.v1.repository;

import com.saojudas.adocao.v1.domain.LoginV1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepositoryV1 extends JpaRepository<LoginV1, Integer> {

    Optional<LoginV1> findByIdLogin(Integer idLogin);
    LoginV1 save(LoginV1 login);
    void deleteByIdLogin(Integer idLogin);
}
