package dev.flash.aisim;

/**
 * Created by Flash on 28/03/2017.
 */

public class Handler {
	
	Instance instance;
	
	public Handler(Instance instance) {
		this.instance = instance;
	}
	
	public Instance getInstance(){
		return instance;
	}
	
}
