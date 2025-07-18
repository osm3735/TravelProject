package com.example.TravelProject.Repository;

import com.example.TravelProject.entity.UserAccount.SocialAccount;
import com.example.TravelProject.entity.UserAccount.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialAccountRepository extends JpaRepository<SocialAccount, Integer> {
    Optional<SocialAccount> findByUserAndProvider(User user, String provider);
}

