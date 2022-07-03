package uz.sudev.clickapp.entity.template;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.sudev.clickapp.entity.User;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractMainEntity {
    @Column(nullable = false,updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(nullable = false,updatable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;
    @JoinColumn(updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;
    @ManyToOne(fetch = FetchType.LAZY)
    private User updatedBy;
}
