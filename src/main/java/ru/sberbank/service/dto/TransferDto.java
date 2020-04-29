package ru.sberbank.service.dto;

public class TransferDto {
	private Long idCardByTo;
	private Long transferSum;

	public TransferDto() {
	}

	public TransferDto(Long idCardByTo, Long transferSum) {
		this.idCardByTo = idCardByTo;
		this.transferSum = transferSum;
	}

	public Long getIdCardByTo() {
		return idCardByTo;
	}

	public Long getTransferSum() {
		return transferSum;
	}
}
