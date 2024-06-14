package com.server.springStudy.converter;

import com.server.springStudy.web.dto.TempResponseDTO;

public class TempConverter {

    public static TempResponseDTO.TempTestDTO toTempTestDTO(){
        return TempResponseDTO.TempTestDTO.builder()
                .testString("This is Test")
                .build();
    }
}
