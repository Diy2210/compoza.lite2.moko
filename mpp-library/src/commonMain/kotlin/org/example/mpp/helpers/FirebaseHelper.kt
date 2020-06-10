package org.example.mpp.helpers

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseApp
import dev.gitlive.firebase.firestore.FirebaseFirestore

abstract class FirebaseHelper {
    abstract val Firebase.firestore: FirebaseFirestore
    abstract fun Firebase.firestore(app: FirebaseApp): FirebaseFirestore

}