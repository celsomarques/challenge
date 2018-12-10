package br.com.atech.challenge.airport;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(of = "code")
@NoArgsConstructor
@RequiredArgsConstructor
public class Airport {

	@Id
	@NonNull
	private String code;

	@NonNull
	private String city;
}