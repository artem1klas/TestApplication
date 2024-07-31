package com.example.testapplication.data.convertors

import com.example.testapplication.data.db.entities.PictureEntity
import com.example.testapplication.domain.models.Picture

class PictureDbConvertor {
    fun map(picture: Picture): PictureEntity {
        return PictureEntity(
            id = picture.id
        )
    }

    fun map(picture: PictureEntity): Picture {
        return Picture(
            id = picture.id
        )
    }
}