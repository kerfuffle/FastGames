package net.kerfuffle.Utilities.GUI;

public abstract class KeyPressListener {

	public int key;
	public KeyPressListener(int key)
	{
		this.key=key;
	}
	public abstract void onPress();
}
