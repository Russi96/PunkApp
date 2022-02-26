package com.app.data.database

import androidx.room.TypeConverter
import com.app.domain.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BeerTypeConvert {
    var gson = Gson()

    @TypeConverter
    fun beerToString(restaurants: BeerItem): String{
        return gson.toJson(restaurants)
    }
    @TypeConverter
    fun stringToBeer(data: String): BeerItem{
        val listType = object : TypeToken<BeerItem>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun listToJson(value: List<String>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun boilVolumeToJson(value: BoilVolume) : String? = Gson().toJson(value)

    @TypeConverter
    fun stringToBoilVolume(data: String): BoilVolume{
        val listType = object : TypeToken<BoilVolume>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun ingredientsToJson(value: Ingredients) : String? = Gson().toJson(value)

    @TypeConverter
    fun stringToIngredients(data: String): Ingredients{
        val listType = object : TypeToken<Ingredients>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun methodToJson(value: Method) : String? = Gson().toJson(value)

    @TypeConverter
    fun stringToMethod(data: String): Method{
        val listType = object : TypeToken<Method>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun volumeToJson(value: Volume) : String? = Gson().toJson(value)

    @TypeConverter
    fun stringToVolume(data: String): Volume{
        val listType = object : TypeToken<Volume>() {}.type
        return gson.fromJson(data, listType)
    }


}