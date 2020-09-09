package com.cavista.leaveragesapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments_table")
data class CommentsModel(@ColumnInfo(name = "title") val title: String, @ColumnInfo(name = "comment") val comment: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

