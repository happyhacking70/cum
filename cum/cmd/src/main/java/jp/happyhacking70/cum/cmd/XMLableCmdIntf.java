package jp.happyhacking70.cum.cmd;

/**
 * 
 */

import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;

import org.w3c.dom.Document;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface XMLableCmdIntf {
	String toXmlStr() throws CumExcpXMLGenFailed;

	Document toXmlDom() throws CumExcpXMLGenFailed;

}
