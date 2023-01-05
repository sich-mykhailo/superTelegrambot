package com.parser.bot.entity;

import com.parser.bot.service.states.ChatState;
import lombok.*;
import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Table(name = "users")
@Entity
public class BotUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String chatId;
    @Enumerated(EnumType.STRING)
    private ChatState state;
    private String email;
    private String phone;
    private Boolean admin;
    private Integer processCount;
    private Integer processLimit;
    private boolean isRegistered;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID assesKey = UUID.randomUUID();
    private String category;
}
