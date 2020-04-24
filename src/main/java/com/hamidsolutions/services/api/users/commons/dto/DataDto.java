package com.hamidsolutions.services.api.users.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class DataDto<T> {
    List<T> dataItems = new ArrayList<>();
}
