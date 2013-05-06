/**
 * 
 */
package jp.happyhacking70.cum.cmd;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import jp.happyhacking70.cum.cmd.CmdAbst.CmdTypes;
import jp.happyhacking70.cum.cmd.res.ResCmdIntf;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
abstract public class ResCmdChnlAudTestAbst extends CmdChnlAudTestAbst
		implements ResCmdTestIntf {

	/**
	 * @throws IOException
	 */
	public ResCmdChnlAudTestAbst() throws IOException {
		super();
	}

	@Override
	final protected CmdTypes getCmdType() {
		return CmdTypes.RES;
	}

	@Override
	@Test
	final public void testGetRslt() {
		assertEquals(((ResCmdIntf) getCmdNormal()).getRslt(), getNormalRslt());

	}
}
