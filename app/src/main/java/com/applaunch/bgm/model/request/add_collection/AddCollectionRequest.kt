package com.applaunch.bgm.model.request.add_collection

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class AddCollectionRequest(
    collectionName: String = "",
    id: String = "",
    type: String = ""
) : BaseObservable() {

    @get:Bindable
    var collectionName = collectionName
        set(value) {
            if (value != collectionName) {
                field = value
                notifyChange()
            }

        }
    @get:Bindable
    var id = id
        set(value) {
            if (value != id) {
                field = value
                notifyChange()
            }

        }
    @get:Bindable
    var type = type
        set(value) {
            if (value != type) {
                field = value
                notifyChange()
            }

        }

    override fun toString(): String {
        return "Collection name $collectionName id $id type $type"
    }
}