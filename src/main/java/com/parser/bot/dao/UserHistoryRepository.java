package com.parser.bot.dao;

import com.parser.bot.entity.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
}
