package br.com.atech.challenge.flight;

import lombok.Getter;

@Getter
public enum Status {

	ACTIVE,
	CANCELED,
	LANDED,
	SCHEDULED;
}