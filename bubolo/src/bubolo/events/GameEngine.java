package bubolo.events;

public class GameEngine {
	public GameEngine(){

	}
	public static void ExecuteActions(){
		while (ActionQueue.q.size() > 0)
		{
			ActionQueue.ExecuteFromQueue();
		}
	}
}
