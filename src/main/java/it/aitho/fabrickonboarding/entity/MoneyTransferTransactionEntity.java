package it.aitho.fabrickonboarding.entity;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class MoneyTransferTransactionEntity {

    @Id
    private String transactionId;

    private String operationId;
    private LocalDate accountingDate;
    private LocalDate valueDate;
    @ManyToOne
    private TransactionTypeEntity type;
    private double amount;
    private String currency;
    private String description;

    @PrePersist
    private void ensureId(){
        if (this.getTransactionId() == null || this.getTransactionId().isBlank()) {
            this.setTransactionId(UUID.randomUUID().toString());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MoneyTransferTransactionEntity that = (MoneyTransferTransactionEntity) o;
        return transactionId != null && Objects.equals(transactionId, that.transactionId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
