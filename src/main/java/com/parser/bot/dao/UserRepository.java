package com.parser.bot.dao;

import com.parser.bot.entity.BotUser;
import com.parser.bot.entity.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<BotUser, Long> {
    Optional<BotUser> findByChatId(String id);

    boolean existsUserByEmail(String email);

    BotUser findAllByAssesKey(UUID key);
}
