/**
 * 
 */
package jp.happyhacking70.cum.prestr.prestrLyr;

import jp.happyhacking70.cum.prestr.chnlLyr.PrestrChnl;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface PrestrChnlViewIntf {

	/**
	 * set up PrestrChannel so that view can ask channel to send channel
	 * commands or close channel
	 * 
	 * @param prestrChnl
	 */
	void setChnl(PrestrChnl prestrChnl);

	/**
	 * callback interface when audience joined channel
	 * 
	 * @param audName
	 */
	void audJoined(String audName);

	/**
	 * callback interface when audience rejected joining channel
	 * 
	 * @param audName
	 */
	void audRjcted(String audName);

	/**
	 * callback interface when audience left
	 * 
	 * @param audName
	 */
	void audLft(String audName);

	/**
	 * callback interface when audience is disconnected
	 * 
	 * @param audName
	 */
	void audDiscned(String audName);

	/**
	 * callback interface when channel is registered
	 */
	void chnlReged();

	void chnlClsed();

	/**
	 * callback interface when session disconnected
	 */
	void discned();

	/**
	 * callback interface when session is closing. view is supposed to finish
	 * all UI for channel. this.clsChnl() should not be called because
	 * presentation server will take care of that.
	 */
	void seshClsing();

	/**
	 * callback interface when session is closed. view is supposed to finish all
	 * UI for channel. Normally this should not happen because seshClsing() is
	 * called in advance. This is just for some weird situation.
	 */
	void seshClsed();

	/**
	 * callback interface when closing channel failed
	 * 
	 * @param rslt
	 */
	void clsChnlFailed(String rslt);

}
