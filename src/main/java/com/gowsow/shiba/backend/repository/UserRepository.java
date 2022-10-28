package com.gowsow.shiba.backend.repository;

import com.gowsow.shiba.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity> {


    UserEntity findByEmail(String email);

    UserEntity findByEmailAndPassword(String email, String password);
}
