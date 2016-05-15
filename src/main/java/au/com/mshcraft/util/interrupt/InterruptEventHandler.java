package au.com.mshcraft.util.interrupt;

import java.util.EventListener;

public interface InterruptEventHandler extends EventListener {
	void interruptEvent(InterruptEvent e);
	void dispatchEvent(DispatchEvent e);
}