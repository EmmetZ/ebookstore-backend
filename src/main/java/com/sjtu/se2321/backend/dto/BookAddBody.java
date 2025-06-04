package com.sjtu.se2321.backend.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BookAddBody extends BookEditBody {
    private Long coverId;
}
