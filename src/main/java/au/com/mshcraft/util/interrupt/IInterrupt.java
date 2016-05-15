package au.com.mshcraft.util.interrupt;

import java.lang.reflect.Method;

public interface IInterrupt {
	public void request(Method callerMethod, String receiverName, String receiverMethod);
	public Object process(Object... args);
}
