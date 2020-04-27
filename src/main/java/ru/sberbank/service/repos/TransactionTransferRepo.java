package ru.sberbank.service.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.service.entity.transaction.impl.TransactionTransferImpl;

public interface TransactionTransferRepo extends JpaRepository<TransactionTransferImpl, Long> {
}
