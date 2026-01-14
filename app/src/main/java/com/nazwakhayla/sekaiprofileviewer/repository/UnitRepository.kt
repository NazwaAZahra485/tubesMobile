package com.nazwakhayla.sekaiprofileviewer.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.nazwakhayla.sekaiprofileviewer.entity.SekaiUnit
import com.nazwakhayla.sekaiprofileviewer.util.FirebaseHelper

class UnitRepository {
    private val ref = FirebaseHelper.unitRef

    fun getAllUnits(onResult: (List<SekaiUnit>) -> Unit){
        ref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                val units = mutableListOf<SekaiUnit>()
                snapshot.children.forEach { child ->
                    val unit = child.getValue(SekaiUnit::class.java)
                    unit?.let {units.add(it)}
                }
                onResult(units)
            }

            override fun onCancelled(error: DatabaseError) {
                onResult(emptyList())
            }
        })
    }
}