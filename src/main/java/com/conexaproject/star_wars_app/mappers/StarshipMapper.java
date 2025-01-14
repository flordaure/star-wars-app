package com.conexaproject.star_wars_app.mappers;

import com.conexaproject.star_wars_app.dto.starships.StarshipsResult;
import com.conexaproject.star_wars_app.model.starships.Starship;

public class StarshipMapper {

    public static Starship getStarshipData(StarshipsResult starshipsResult) {
        return new Starship(starshipsResult.getUid(),
                starshipsResult.getProperties().getName(),
                starshipsResult.getProperties().getUrl(),
                starshipsResult.getProperties().getModel(),
                starshipsResult.getProperties().getStarshipClass(),
                starshipsResult.getProperties().getManufacturer(),
                starshipsResult.getProperties().getCostInCredits(),
                starshipsResult.getProperties().getLength(),
                starshipsResult.getProperties().getCrew(),
                starshipsResult.getProperties().getPassengers(),
                starshipsResult.getProperties().getMaxAtmospheringSpeed(),
                starshipsResult.getProperties().getHyperdriveRating(),
                starshipsResult.getProperties().getMglt(),
                starshipsResult.getProperties().getCargoCapacity(),
                starshipsResult.getProperties().getConsumables(),
                starshipsResult.getProperties().getPilots(),
                starshipsResult.getProperties().getCreated(),
                starshipsResult.getProperties().getEdited());
    }
}
