package com.hamidsolutions.services.api.users.commons.dto;

import lombok.*;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
//@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseDto<T> {
	
	private String status;
	private String code;
	private List<T> data;
	private String message;

	private Integer page;
	private Integer size;
	private Long total;
}