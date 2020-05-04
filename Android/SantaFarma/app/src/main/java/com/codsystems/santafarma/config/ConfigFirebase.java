package com.codsystems.santafarma.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfigFirebase {
    private static DatabaseReference database;
    private static FirebaseAuth auth;

    //Retorna a Instancia do firebaseDatabase
public static DatabaseReference getFirebaseDatabase(){
    if(database == null){
        database = FirebaseDatabase.getInstance().getReference();
    }
    return database;
}
    // retorna a Instancia do AUTH
    public static FirebaseAuth getFirebaseAutenticacao(){
if(auth == null){
    auth = FirebaseAuth.getInstance();
}
return auth;
    }
}
