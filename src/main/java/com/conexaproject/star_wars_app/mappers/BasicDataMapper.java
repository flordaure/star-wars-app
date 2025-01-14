package com.conexaproject.star_wars_app.mappers;

import com.conexaproject.star_wars_app.dto.BasicData;
import com.conexaproject.star_wars_app.model.BasicInformation;

public class BasicDataMapper {

    public static BasicInformation getBasicData(BasicData basicData){
        return BasicInformation.builder()
                .name(basicData.getName())
                .uid(basicData.getUid())
                .url(basicData.getUrl())
                .build();
    }
}
