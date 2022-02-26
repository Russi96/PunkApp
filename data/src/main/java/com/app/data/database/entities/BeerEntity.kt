package com.app.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.data.utils.Constants.BEER_TABLE
import com.app.domain.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = BEER_TABLE)
class BeerEntity(
    var beerItem: BeerItem
) {
    @PrimaryKey
    var id: Int = beerItem.id
    var name: String = beerItem.name
    var abv: Double = beerItem.abv
    var attenuationLevel: Double = beerItem.attenuationLevel
    var boilVolume: BoilVolume = beerItem.boilVolume
    var brewersTips: String = beerItem.brewersTips
    var contributedBy: String = beerItem.contributedBy
    var description: String = beerItem.description
    var ebc: Double = beerItem.ebc
    var firstBrewed: String = beerItem.firstBrewed
    var foodPairing: List<String> = beerItem.foodPairing
    var ibu: Double = beerItem.ibu
    var imageUrl: String = beerItem.imageUrl
    var ingredients: Ingredients = beerItem.ingredients
    var method: Method = beerItem.method
    var ph: Double = beerItem.ph
    var srm: Double = beerItem.srm
    var tagline: String = beerItem.tagline
    var targetFg: Double = beerItem.targetFg
    var targetOg: Double = beerItem.targetOg
    var volume: Volume = beerItem.volume


}