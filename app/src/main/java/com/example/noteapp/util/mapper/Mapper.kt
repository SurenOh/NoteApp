package com.example.noteapp.util.mapper

interface Mapper<Entity, Model> {

    fun mapFromEntity(entity: Entity): Model
    fun mapToEntity(model: Model): Entity

    fun mapFromEntity(entities: List<Entity>) = entities.map { mapFromEntity(it) }
    fun mapToEntity(models: List<Model>) = models.map { mapToEntity(it) }
}
