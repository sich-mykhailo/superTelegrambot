package com.parser.bot.service.user;

import com.parser.bot.dao.UserRepository;
import com.parser.bot.entity.BotUser;
import com.parser.bot.service.states.ChatState;
import com.parser.exceptions.EntityNotFoundException;
import com.parser.util.OlxUrls;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public BotUser findByChatId(String id) {
        return userRepository.findByChatId(id);
    }

    public void addUser(BotUser botUser) {
        botUser.setAdmin(userRepository.count() == 0);
        botUser.setAssesKey(UUID.randomUUID());
        botUser.setLimitPerMonth(10);
        botUser.setState(ChatState.START);
        botUser.setCategory(OlxUrls.AAL_CATEGORY);
        userRepository.save(botUser);
    }

    public void update(BotUser botUser) {
        userRepository.save(botUser);
    }

    @Override
    public boolean isExist(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public BotUser findByKey(String key) {
        return userRepository.findAllByAssesKey(UUID.fromString(key));
    }

    @Override
    public void save(BotUser botUser) {
        userRepository.save(botUser);
    }

    @Override
    public BotUser getById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new EntityNotFoundException("User not found"));
    }
}
