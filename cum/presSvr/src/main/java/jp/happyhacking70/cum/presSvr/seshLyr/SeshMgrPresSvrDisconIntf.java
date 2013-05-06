/**
 * 
 */
package jp.happyhacking70.cum.presSvr.seshLyr;

import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;
import jp.happyhacking70.cum.presSvr.adptrLyr.excp.CumExcpSeshNotExist;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface SeshMgrPresSvrDisconIntf {
	void audDisconned(String seshName, String audName)
			throws CumExcpSeshNotExist, CumExcpXMLGenFailed;

	void prestrDisconned(String seshName) throws CumExcpSeshNotExist,
			CumExcpXMLGenFailed;
}
