package com.nazwakhayla.sekaiprofileviewer.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.nazwakhayla.sekaiprofileviewer.entity.Character
import com.nazwakhayla.sekaiprofileviewer.util.FirebaseHelper

class CategoryRepository {
    private val ref = FirebaseHelper.categoryRef

    fun getAllCategories(onResult: (List<Character>) -> Unit){
        ref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                val categories = mutableListOf<Character>()
                snapshot.children.forEach { child ->
                    val category = child.getValue(Character::class.java)
                    category?.let {categories.add(it)}
                }
                onResult(categories)
            }

            override fun onCancelled(error: DatabaseError) {
                onResult(emptyList())
            }
        })
    }
}