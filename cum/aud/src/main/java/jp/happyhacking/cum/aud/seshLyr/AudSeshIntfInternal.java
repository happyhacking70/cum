/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface AudSeshIntfInternal {
	public ChnlRscIntf fetchRsc(String seshName, String chnlType,
			String chnlName, String rscName);
}
