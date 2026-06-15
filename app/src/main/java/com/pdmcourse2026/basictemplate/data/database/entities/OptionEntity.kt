package com.pdmcourse2026.basictemplate.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pdmcourse2026.basictemplate.data.model.Option


@Entity(tableName = "options")
data class OptionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val imageUrl: String,
)

// Mappers: convertir entre Entity (de la BD) y modelo (de la app)
fun OptionEntity.toModel(): Option {
    return Option(
        id = id,
        name = name,
        imageUrl = imageUrl,
    )
}

fun Option.toEntity(): OptionEntity {
    return OptionEntity(
        id = id,
        name = name,
        imageUrl = imageUrl,
    )
}