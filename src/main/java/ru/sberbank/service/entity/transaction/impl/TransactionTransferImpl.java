package ru.sberbank.service.entity.transaction.impl;

import ru.sberbank.service.entity.Card;
import ru.sberbank.service.entity.transaction.Transaction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction_transfer")
public class TransactionTransferImpl implements Transaction {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "date")
	private final Date date;

	@Column(name = "id_card_from", unique = true)
	private Long idCardFrom;

	@Column(name = "id_card_to", unique = true)
	private Long idCardTo;

	@Column(name = "transfer_amount")
	private Long transferAmount;

	@Column(name = "balance_from_card_after_transfer")
	private Long balanceFromCardAfterTransfer;

	// TODO: 27.04.2020 не знаю можно ли как-то запросить баланс карты, на которую мы делаем перевод не имея к ней
	// TODO: доступа, тк мы не являемся владельцем карты на которую переводим средства
//	@Column(name = "balance_to_card_after_transfer")
//	private Long balanceToCardAfterTransfer;

	@ManyToOne(fetch = FetchType.EAGER) //todo: мы получаем сразу при этой транзакции информацию о владельце карты
	@JoinColumn(name = "card_id")
	private Card card;

	public TransactionTransferImpl() {
		this.date = new Date();
	}

	public TransactionTransferImpl(Long idCardFrom, Long idCardTo, Long transferAmount, Long balanceFromCardAfterTransfer, Card card) {
		this();
		this.idCardFrom = idCardFrom;
		this.idCardTo = idCardTo;
		this.transferAmount = transferAmount;
		this.balanceFromCardAfterTransfer = balanceFromCardAfterTransfer;
		this.card = card;
	}

	public Long getId() {
		return id;
	}

	public Long getIdCardFrom() {
		return idCardFrom;
	}

	public Long getIdCardTo() {
		return idCardTo;
	}

	public Long getTransferAmount() {
		return transferAmount;
	}

	public Long getBalanceFromCardAfterTransfer() {
		return balanceFromCardAfterTransfer;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Date getDate() {
		return date;
	}
}
