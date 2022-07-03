package uz.sudev.clickapp.entity.template;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;


@Data
@MappedSuperclass
public abstract class AbstractUUIDEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @Type(type = "org.hibernate.PostgresUUIDType")
    @GenericGenerator(name = "uuid2",strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
}
