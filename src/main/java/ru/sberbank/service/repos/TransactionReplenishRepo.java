package ru.sberbank.service.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.service.entity.transaction.impl.TransactionReplenishImpl;

public interface TransactionReplenishRepo extends JpaRepository<TransactionReplenishImpl, Long> {
}
