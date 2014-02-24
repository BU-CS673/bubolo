package bubolo.events;

import java.util.LinkedList;
import java.util.Queue;

public class ActionQueue {
	public static Queue<Command> q = new LinkedList<Command>();
	public ActionQueue() {
	   }
	 
	   public static void ExecuteFromQueue() {
	      ActionQueue.q.remove().execute();        
	   }
	
}
