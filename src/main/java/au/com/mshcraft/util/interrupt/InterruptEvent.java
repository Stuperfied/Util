package au.com.mshcraft.util.interrupt;

import java.lang.reflect.Method;
import java.util.EventObject;

public class InterruptEvent extends EventObject {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object receiver;
	private Method receiverMethod;
	private Object args;
	  
	  public InterruptEvent(Object source, Object receiver, Method receiverMethod) {
	    super(source);
	    this.receiver = receiver;
	    this.receiverMethod = receiverMethod;
	  }
	  
	  /** return the event source 
	 * @return */
	  public Object getSource() {
	    return source;
	  }
	  
	  /** return the event receiver 
	 * @return */
	  public Object getReciever() {
		return receiver;
	  }
	  
	  /** return the event receiver 
	 * @return */
	  public Object getRecieverMethod() {
		return receiverMethod;
	  }
	  
	  /** return the event arguments 
	 * @return */
	  public Object getArgs() {
		return args;
	  }

}

