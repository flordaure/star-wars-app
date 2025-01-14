package com.conexaproject.star_wars_app.mappers;

import com.conexaproject.star_wars_app.dto.people.PeopleResult;
import com.conexaproject.star_wars_app.model.characters.Character;

public class CharacterMapper {

    public static Character getCharacterData(PeopleResult peopleResult){
        return new Character(peopleResult.getUid(),
                peopleResult.getProperties().getName(),
                peopleResult.getProperties().getUrl(),
                peopleResult.getProperties().getHeight(),
                peopleResult.getProperties().getMass(),
                peopleResult.getProperties().getHairColor(),
                peopleResult.getProperties().getSkinColor(),
                peopleResult.getProperties().getEyeColor(),
                peopleResult.getProperties().getBirthYear(),
                peopleResult.getProperties().getGender(),
                peopleResult.getProperties().getCreated(),
                peopleResult.getProperties().getEdited(),
                peopleResult.getProperties().getHomeworld());
    }
}
