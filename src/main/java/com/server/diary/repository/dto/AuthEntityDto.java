package com.server.diary.repository.dto;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public
class AuthEntityDto implements Serializable {
    private String refreshToken;


}