package ru.sberbank.service.dto;

public class TransferDto {
	private Long IdCardByTo;
	private Long transferSum;

	public TransferDto() {
	}

	public TransferDto(Long idCardByTo, Long transferSum) {
		IdCardByTo = idCardByTo;
		this.transferSum = transferSum;
	}

	public Long getIdCardByTo() {
		return IdCardByTo;
	}

	public Long getTransferSum() {
		return transferSum;
	}
}
