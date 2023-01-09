package com.server.diary.repository.userRepository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.server.diary.common.baseEntity.UserDateEntity;
import com.server.diary.repository.diaryRepository.Diary;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString(exclude = {"diaries"})
@JsonIgnoreProperties(value = {"diaries"}, allowGetters = false)
public class User extends UserDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, name = "user_no")
    private Long userNo;
    @Column(name = "user_id", length = 50)
    private String userId;
    @Column(name = "password", length = 500)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Diary> diaries = new ArrayList<>();

}
