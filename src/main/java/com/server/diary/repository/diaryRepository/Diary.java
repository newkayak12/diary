package com.server.diary.repository.diaryRepository;

import com.server.diary.repository.userRepository.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryNo;
    @ManyToOne
    @JoinColumn(name = "userNo")
    private User user;
    @Column(length = 1000)
    private String contents;
    @Column(nullable = false)
    private LocalDate regDate;
}
