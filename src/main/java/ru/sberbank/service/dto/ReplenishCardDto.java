package ru.sberbank.service.dto;

public class ReplenishCardDto {
	private Long increaseSumBy;

	public ReplenishCardDto(Long increaseSumBy) {
		this.increaseSumBy = increaseSumBy;
	}

	public Long getIncreaseSumBy() {
		return increaseSumBy;
	}

	public void setIncreaseSumBy(Long increaseSumBy) {
		this.increaseSumBy = increaseSumBy;
	}
}
