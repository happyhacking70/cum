/**
 * 
 */
package jp.happyhacking70.cum.cmd.rsc;

import jp.happyhacking70.cum.excp.cmd.rsc.CumExcpRscBinariseFailed;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface ChnlRscIntf {
	public String getMimeType();

	public String getName();

	public byte[] getBinary() throws CumExcpRscBinariseFailed;
}
