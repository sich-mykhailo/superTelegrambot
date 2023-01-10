package com.parser.bot.entity;

import com.parser.bot.service.states.ChatState;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
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
    Integer processCount;
    Integer processLimit;
    boolean isRegistered;
    UUID assesKey = UUID.randomUUID();
    String category;
}
