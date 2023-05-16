package com.parser.bot.service.user;

import com.parser.bot.dao.UserHistoryRepository;
import com.parser.bot.entity.BotUser;
import com.parser.bot.entity.UserHistory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserHistoryServiceImpl implements UserHistoryService {

    UserHistoryRepository repository;
    UserService botUserService;

    @Override
    public UserHistory create(Long userId, String url) {
        UserHistory newUserHistory = new UserHistory();
        newUserHistory.setNameOfOrder(url);
        BotUser user = botUserService.getById(userId);
        newUserHistory.setUserId(user);
        newUserHistory.setCreatedAt(LocalDate.now());
        return repository.save(newUserHistory);
    }
}
