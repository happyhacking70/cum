/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import java.util.HashMap;

import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface AudSeshIntf {

	public abstract void chnlReged(String chnlName,
			HashMap<String, ChnlRscIntf> rsces);

	public abstract void joinChnl(String chnlName);

	public abstract void chnlJoined(String chnlName);

	public abstract void lvChnl(String chnlName);

}