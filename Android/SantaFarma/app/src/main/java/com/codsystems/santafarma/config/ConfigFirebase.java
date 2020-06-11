package com.codsystems.santafarma.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;


public class ConfigFirebase {
    private static DatabaseReference database;
    private static FirebaseAuth auth;
    private static FirebaseFirestore firestore;


//RETORNA INSTANCIA DO FIRETORE
    public static FirebaseFirestore getFirebaseFirestore(){
        if(firestore == null){
            firestore = FirebaseFirestore.getInstance();
        }
        return firestore;
    }

    //Retorna a Instancia do firebaseDatabase
    public static DatabaseReference getFirebaseDatabase() {
        if (database == null) {
            database = FirebaseDatabase.getInstance().getReference();
        }
        return database;
    }

    // retorna a Instancia do AUTH
    public static FirebaseAuth getFirebaseAutenticacao() {
        if (auth == null) {
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }


}