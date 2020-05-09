package com.example.animesite.repository;

import com.example.animesite.entity.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {

        Optional<Users> findByLogin(String login);

        Optional<Users> findByEmail(String email);

        @Modifying
        @Transactional
        @Query(value="update Users u set u.active=?1 where u.login=?2")
        void updateUser(boolean active,String username);


        @Modifying
        @Transactional
        void deleteByLogin(String login);
}
