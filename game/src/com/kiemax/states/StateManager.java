package com.kiemax.states;

public class StateManager {
	
	private static State currentState = null;
	
	public static void setState(State state) {
		currentState = state;
	}
	public static State getState(){
		return currentState;
	}
	
}
