package com.nagwa.task.Domain.Model

import com.google.gson.annotations.SerializedName

class FileModel {

    var downloaded = false

    @SerializedName("name")
    lateinit var name: String

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("type")
    lateinit var type: String

    @SerializedName("url")
    lateinit var url: String
}