package com.parser.bot.entity;

import com.parser.bot.service.states.ChatState;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Table(name = "users")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BotUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String chatId;
    @Enumerated(EnumType.STRING)
    ChatState state;
    String email;
    String phone;
    Boolean admin;
    Integer limitPerMonth;
    boolean isRegistered;
    UUID assesKey;
    String category;
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    List<UserHistory> userHistory;
}
