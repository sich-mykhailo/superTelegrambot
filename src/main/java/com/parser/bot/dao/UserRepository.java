package com.parser.bot.dao;

import com.parser.bot.entity.BotUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRepository extends JpaRepository<BotUser, Long> {
    BotUser findByChatId(String id);

    boolean existsUserByEmail(String email);

    BotUser findAllByAssesKey(UUID key);
}
