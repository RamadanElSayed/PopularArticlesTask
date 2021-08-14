package com.task.populararticles.presentation.di

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type


class BooleanDeserializer : JsonDeserializer<Boolean> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Boolean? {
        if (json.isJsonPrimitive) {
            val primitive = json.asJsonPrimitive
            if (primitive.isBoolean) {
                return primitive.asBoolean
            }
            if (primitive.isNumber) {
                return primitive.asInt == 1
            }
            if (primitive.isString) {
                return "1" == primitive.asString
            }
        }
        throw JsonParseException("Wrong boolean")
    }
}