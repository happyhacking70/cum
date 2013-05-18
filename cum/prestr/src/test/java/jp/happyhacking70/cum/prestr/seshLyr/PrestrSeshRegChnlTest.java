/**
 * 
 */
package jp.happyhacking70.cum.prestr.seshLyr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import jp.happyhacking70.cum.excp.prestr.CumExcpChnlExists;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PrestrSeshRegChnlTest extends PrestrSeshAbstTest {
	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_regChnl_INIT() throws CumExcpChnlExists,
			CumExcpIllegalSeshStatus {
		PrestrSesh sesh = getSesh();
		try {
			sesh.regChnl(chnlType, chnlNameA, rsces, chnlViewA);
		} catch (CumExcpIllegalSeshStatus e) {
			assertNull(adptr.getSeshNameToRegChnl());
			throw e;
		}
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_regChnl_REGING() throws TestExcp, CumExcpChnlExists,
			CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoReging();
		try {
			sesh.regChnl(chnlType, chnlNameA, rsces, chnlViewA);
		} catch (CumExcpIllegalSeshStatus e) {
			assertNull(adptr.getSeshNameToRegChnl());
			throw e;
		}
	}

	@Test
	public void TEST_regChnl_REGED() throws TestExcp, CumExcpChnlExists,
			CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoReged();

		sesh.regChnl(chnlType, chnlNameC, rsces, chnlViewC);

		assertEquals(chnlNameC, adptr.getChnlNameToReg());
		assertEquals(seshName, adptr.getSeshNameToRegChnl());
		assertEquals(chnlType, adptr.getChnlType());

	}

	@Test(expected = CumExcpChnlExists.class)
	public void TEST_regChnl_REGED_EXISTS() throws TestExcp, CumExcpChnlExists,
			CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoReged();

		sesh.regChnl(chnlType, chnlNameA, rsces, chnlViewA);

	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_regChnl_CLSING() throws TestExcp, CumExcpChnlExists,
			CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoClsing();

		sesh.regChnl(chnlType, chnlNameA, rsces, chnlViewA);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_regChnl_CLSED() throws TestExcp, CumExcpChnlExists,
			CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoClsed();
		sesh.regChnl(chnlType, chnlNameA, rsces, chnlViewA);

	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_regChnl_DISCNED() throws TestExcp, CumExcpChnlExists,
			CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoDiscned();
		try {
			sesh.regChnl(chnlType, chnlNameA, rsces, chnlViewA);
		} catch (CumExcpIllegalSeshStatus e) {
			assertNull(adptr.getSeshNameToRegChnl());
			throw e;
		}
	}

}
