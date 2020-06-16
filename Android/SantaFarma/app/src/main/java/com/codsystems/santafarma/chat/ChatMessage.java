package com.codsystems.santafarma.chat;

public class ChatMessage {
	public boolean left = false;
	public String message;


	public ChatMessage(boolean left, String message) {
		super();
		this.left = left;
		this.message = message;
	}
}

/*
public void consultaMensagem(){
        FirebaseAuth auth = ConfigFirebase.getFirebaseAutenticacao();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
 db.collection("Clientes").document(auth.getCurrentUser().getUid())
            .collection("Chat").orderBy("timeStamp", Query.Direction.DESCENDING) //.document().getParent()
.get()

         .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){
     @Override
     public void onComplete(@NonNull Task<QuerySnapshot> task) {
         if (task.isSuccessful()) {
             for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                 String sp = (String) documentSnapshot.getData().get("Remetente");
                 if(sp.equalsIgnoreCase("SP")) {
                     String message = (String) documentSnapshot.getData().get("mensagem");
                     System.out.println("ELE mandou:"+message);
                    chatArrayAdapter.add(new ChatMessage(true, message));
                    chatArrayAdapter.clear();
                 }else if(!sp.equalsIgnoreCase("SP")){
                     String message2 = (String) documentSnapshot.getData().get("mensagem");
                     chatArrayAdapter.add(new ChatMessage(false,message2));
                     System.out.println("EU MANDEI"+message2);
                     chatArrayAdapter.clear();
                 }

             }
         }
     }
 });
 }



 */