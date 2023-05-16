package com.parser.bot.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Table(name = "user_details")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    BotUser userId;
    String nameOfOrder;
    LocalDate createdAt;

    public UserHistory() {
    }

    public UserHistory(String url) {
        nameOfOrder = url;
    }
}
