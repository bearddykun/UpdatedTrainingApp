package com.example.updatedtrainingapp.dataBase.objects

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.updatedtrainingapp.dataBase.DatabaseContract

@Entity(tableName = DatabaseContract.TABLE_EXERCISE)
data class ExerciseObject(
        @PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo(name = DatabaseContract.ExerciseColumns.EXERCISE_NAME) val exerciseName: String,
        @ColumnInfo(name = DatabaseContract.ExerciseColumns.EXERCISE_GROUP) val exerciseGroup: String,
        @ColumnInfo(name = DatabaseContract.ExerciseColumns.EXERCISE_IMAGE) val exerciseImage: String
) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readValue(Long::class.java.classLoader) as? Long,
                parcel.readString().toString(),
                parcel.readString().toString(),
                parcel.readString().toString()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeValue(id)
                parcel.writeString(exerciseName)
                parcel.writeString(exerciseGroup)
                parcel.writeString(exerciseImage)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<ExerciseObject> {
                override fun createFromParcel(parcel: Parcel): ExerciseObject {
                        return ExerciseObject(parcel)
                }

                override fun newArray(size: Int): Array<ExerciseObject?> {
                        return arrayOfNulls(size)
                }
        }

}