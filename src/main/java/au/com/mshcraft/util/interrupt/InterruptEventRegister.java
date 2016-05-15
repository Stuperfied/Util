package au.com.mshcraft.util.interrupt;

import java.util.*;

public class InterruptEventRegister {
	private List<InterruptEventHandler> _handlers = new ArrayList<InterruptEventHandler>();
	
    public synchronized void register(InterruptEventHandler h) {
		_handlers.add( h );
    }
    public synchronized void deregister(InterruptEventHandler h) {
        _handlers.remove( h );
    }
    
    protected List<InterruptEventHandler> getList() {
    	return _handlers;
    }

}
