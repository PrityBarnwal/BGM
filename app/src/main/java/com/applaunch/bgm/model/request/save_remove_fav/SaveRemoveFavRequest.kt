package com.applaunch.bgm.model.request.save_remove_fav

data class SaveRemoveFavRequest(
    var collectionId: String = "",
    var type: String = "",
    var id: String = ""
)
