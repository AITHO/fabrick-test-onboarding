package it.aitho.fabrickonboarding.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class TransactionTypeEntity {
    @Id
    private String enumeration;
    @Column(name="valueDb")
    private String value;

    @PrePersist
    private void ensureId(){
        if (this.getEnumeration() == null || this.getEnumeration().isBlank()) {
            this.setEnumeration(UUID.randomUUID().toString());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TransactionTypeEntity that = (TransactionTypeEntity) o;
        return enumeration != null && Objects.equals(enumeration, that.enumeration);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
