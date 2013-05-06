/**
 * 
 */
package jp.happyhacking70.cum.presSvr.seshLyr;

import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface SeshPresSvrDisconIntf {
	void audDisconned(String audName) throws CumExcpXMLGenFailed;

	void prestrDisconned() throws CumExcpXMLGenFailed;

}
