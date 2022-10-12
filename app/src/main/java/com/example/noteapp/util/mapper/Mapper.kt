package com.example.noteapp.util.mapper

interface Mapper<Entity, Model> {

    fun mapFromEntity (entity: Entity): Model
    fun mapToEntity (model: Model): Entity

    fun listMapFromEntity (entities: List<Entity>) = entities.map { mapFromEntity(it) }
    fun listMapToEntity (models: List<Model>) = models.map { mapToEntity (it) }

}