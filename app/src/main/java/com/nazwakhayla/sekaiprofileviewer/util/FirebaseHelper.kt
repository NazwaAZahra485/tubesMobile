package com.nazwakhayla.sekaiprofileviewer.util
import com.google.firebase.database.FirebaseDatabase

// Static Class

object FirebaseHelper {
    val firebaseDatabase = FirebaseDatabase.getInstance()
    val categoryRef = firebaseDatabase.getReference("categories")
}
