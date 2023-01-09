package com.server.diary.common.baseEntity;

import com.server.diary.common.enums.UserStatus;
import com.server.diary.common.converter.UserStatusConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@DynamicUpdate
@DynamicInsert
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class UserDateEntity {
    @ColumnDefault(value = "0")
    @Column(name = "status")
    @Convert(converter = UserStatusConverter.class)
    private UserStatus status;

    @Column(nullable = false, name = "reg_date")
    private LocalDateTime regDate;
    @Column(nullable = true, name = "last_login_date")
    private LocalDateTime lastLoginDate;
    @Column(nullable = true, name = "withdrawal_date")
    private LocalDateTime withdrawalDate;

    @PrePersist
    public void signUp(){
        this.regDate=LocalDateTime.now();
    }
    @PostPersist
    public void signIn(){
        this.lastLoginDate = LocalDateTime.now();
    }
    @PostUpdate
    public void withdrawal(){
        if(Objects.nonNull(this.status) && this.status.getStatus().equals(-1)){
            this.withdrawalDate=LocalDateTime.now();
        }
    }

}
