package com.parser.bot.service.user;

import com.parser.bot.entity.UserHistory;
public interface UserHistoryService {

    UserHistory create(Long userId, String url);
}
