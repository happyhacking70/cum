/**
 * 
 */
package jp.happyhacking70.cum.presSvr.comLyr;

import java.util.concurrent.ConcurrentLinkedQueue;

import jp.happyhacking70.cum.cmd.XMLableCmdIntf;
import jp.happyhacking70.cum.excp.cmd.CumExcpXMLGenFailed;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class CmdSender implements CmdSenderIntf {

	protected ConcurrentLinkedQueue<String> cmdQueue = new ConcurrentLinkedQueue<String>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.presSvr.comLyr.CmdSenderIntf#sendCmd(jp.happyhacking70
	 * .cum3.cmd.XMLableCmdIntf)
	 */
	@Override
	final public void sendCmd(XMLableCmdIntf cmd) throws CumExcpXMLGenFailed {
		String xmledCmd = null;

		xmledCmd = cmd.toXmlStr();

		cmdQueue.add(xmledCmd);
	}

	public String poll() {
		return cmdQueue.poll();
	}
}
