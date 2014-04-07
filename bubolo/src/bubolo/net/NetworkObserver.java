/**
 *
 */

package bubolo.net;

/**
 * @author BU CS673 - Clone Productions
 */
public interface NetworkObserver
{
	void onConnect(String serverName);
	
	void onClientConnected(String clientName);
}
