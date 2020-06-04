package com.codsystems.santafarma.helper;

import android.util.Base64;

public class Base64Custom {

public static String codificarBase64(String s){
return Base64.encodeToString(s.getBytes(),Base64.DEFAULT).replaceAll("(\\n|\\r)","");
}
public static String decodificarBase64(String sCod){
return new String(Base64.decode(sCod,Base64.DEFAULT));
}
}
