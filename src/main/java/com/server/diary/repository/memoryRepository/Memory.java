package com.server.diary.repository.memoryRepository;

import com.server.diary.common.enums.Category;
import com.server.diary.repository.photoRepository.Photo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Memory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memoryNo;

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
    private LocalDateTime regDate;

    @Column
    private String address;

    @Column
    @Enumerated(EnumType.STRING)
    private Category category;

    @PostPersist
    public void save(){
        this.regDate = LocalDateTime.now();
    }
}
