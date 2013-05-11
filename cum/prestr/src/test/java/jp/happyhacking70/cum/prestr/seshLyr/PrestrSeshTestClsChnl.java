/**
 * 
 */
package jp.happyhacking70.cum.prestr.seshLyr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import jp.happyhacking70.cum.excp.prestr.CumExcpChnlNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PrestrSeshTestClsChnl extends PrestrSeshTestAbst {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_clsChnl_INIT() throws CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist {
		PrestrSesh sesh = getSesh();
		try {
			sesh.clsChnl(chnlNameA);
		} catch (CumExcpIllegalSeshStatus e) {
			assertNull(adptr.getSeshNameToClsChnl());
			throw e;
		}
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_clsChnl_REGING() throws CumExcpChnlNotExist, TestExcp,
			CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoReging();
		try {
			sesh.clsChnl(chnlNameA);
		} catch (CumExcpIllegalSeshStatus e) {
			assertNull(adptr.getSeshNameToClsChnl());
			throw e;
		}
	}

	@Test
	public void TEST_clsChnl_REGED() throws TestExcp, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoReged();

		sesh.clsChnl(chnlNameA);

		assertEquals(chnlNameA, adptr.getChnlNameToCls());
		assertEquals(seshName, adptr.getSeshNameToClsChnl());

	}

	@Test(expected = CumExcpChnlNotExist.class)
	public void TEST_clsChnl_REGED_NOCHNL() throws TestExcp,
			CumExcpChnlNotExist, CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoReged();

		sesh.clsChnl(chnlNameC);

	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_clsChnl_CLSING() throws TestExcp, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoClsing();
		try {
			sesh.clsChnl(chnlNameA);
		} catch (CumExcpIllegalSeshStatus e) {
			assertNull(adptr.getSeshNameToClsChnl());
			throw e;
		}
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_clsChnl_CLSED() throws TestExcp, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoClsed();
		try {
			sesh.clsChnl(chnlNameA);
		} catch (CumExcpIllegalSeshStatus e) {
			assertNull(adptr.getSeshNameToClsChnl());
			throw e;
		}
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_clsChnl_DISCNED() throws TestExcp, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus {
		PrestrSesh sesh = gotoDiscned();

		sesh.clsChnl(chnlNameA);
	}

}
