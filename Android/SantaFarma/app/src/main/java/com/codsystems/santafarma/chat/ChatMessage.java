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