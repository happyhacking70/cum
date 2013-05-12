/**
 * 
 */
package jp.happyhacking70.cum.prestr.seshLyr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PrestrSeshRegSeshTest extends PrestrSeshAbstTest {

	@Test
	public void TEST_regSesh_INIT() throws CumExcpIllegalSeshStatus {
		PrestrSesh sesh = getSesh();
		sesh.regSesh();
		assertEquals(seshName, adptr.getSeshNameToReg());
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_regSesh_REGING() throws TestExcp, CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoReging();

		sesh.regSesh();
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_regSesh_REGED() throws TestExcp, CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoReged();
		sesh.regSesh();

	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_regSesh_CLSING() throws CumExcpIllegalSeshStatus, TestExcp {
		PrestrSesh sesh = gotoClsing();
		sesh.regSesh();
		assertNull(adptr.getSeshNameToReg());
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_regSesh_CLSED() throws TestExcp, CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoClsed();
		sesh.regSesh();
		assertNull(adptr.getSeshNameToReg());
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_regSesh_DISCNED() throws TestExcp,
			CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoDiscned();
		sesh.regSesh();
	}

}
