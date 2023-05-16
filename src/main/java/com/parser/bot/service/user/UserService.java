package com.parser.bot.service.user;

import com.parser.bot.entity.BotUser;

public interface UserService {
    BotUser findByChatId(String id);

    void addUser(BotUser botUser);

    boolean isExist(String email);

    void update(BotUser botUser);

    BotUser findByKey(String key);

    void save(BotUser botUser);

    BotUser getById(Long userId);
}
