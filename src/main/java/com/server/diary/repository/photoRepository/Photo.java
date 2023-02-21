package com.server.diary.repository.photoRepository;

import javax.persistence.*;

@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photoNo;

    @Column
    private String photoUrl;
}
