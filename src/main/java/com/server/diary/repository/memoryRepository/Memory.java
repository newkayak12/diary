package com.server.diary.repository.memoryRepository;

import com.querydsl.core.annotations.QueryProjection;
import com.server.diary.common.enums.Category;
import com.server.diary.repository.photoRepository.Photo;
import com.server.diary.repository.userRepository.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@ToString
public class Memory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memoryNo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userNo")
    private User user;

    @OneToOne
    @JoinColumn(name = "firstPhotoNo")
    private Photo firstPhoto;

    @OneToOne
    @JoinColumn(name = "secondPhotoNo")
    private Photo secondPhoto;

    @OneToOne
    @JoinColumn(name = "thirdPhotoNo")
    private Photo thirdPhoto;

    @Column
    private String contents;

    @Column
    private LocalDate regDate;

    @Column
    private String address;

    @Column
    @Enumerated(EnumType.STRING)
    private Category category;

    @PostPersist
    public void save(){
        if(Objects.isNull(this.regDate))  this.regDate = LocalDate.now();
    }
}
