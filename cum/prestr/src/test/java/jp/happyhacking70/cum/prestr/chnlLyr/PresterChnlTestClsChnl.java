/**
 * 
 */
package jp.happyhacking70.cum.prestr.chnlLyr;

import static org.junit.Assert.assertEquals;
import jp.happyhacking70.cum.excp.prestr.CumExcpChnlNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;
import jp.happyhacking70.cum.prestr.seshLyr.TestExcp;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresterChnlTestClsChnl extends PrestrChnlTestAbst {
	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void TEST_clsChnl_REGING() throws CumExcpIllegalChnlStatus,
			CumExcpChnlNotExist, CumExcpIllegalSeshStatus {
		PrestrChnl chnl = getChnl();
		chnl.clsChnl();
	}

	@Test
	public void TEST_clsChnl_REGED() throws TestExcp, CumExcpIllegalChnlStatus,
			CumExcpChnlNotExist, CumExcpIllegalSeshStatus {
		PrestrChnl chnl = gotoReged();
		chnl.clsChnl();
		assertEquals(chnlName, sesh.getChnlToClose());
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void TEST_clsChnl_CLSING() throws TestExcp,
			CumExcpIllegalChnlStatus, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus {
		PrestrChnl chnl = gotoClsing();
		chnl.clsChnl();
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void TEST_clsChnl_CLSED() throws TestExcp, CumExcpIllegalChnlStatus,
			CumExcpChnlNotExist, CumExcpIllegalSeshStatus {
		PrestrChnl chnl = gotoClsed();
		chnl.clsChnl();
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void TEST_clsChnl_DISCNED() throws CumExcpIllegalChnlStatus,
			CumExcpChnlNotExist, CumExcpIllegalSeshStatus, TestExcp {
		PrestrChnl chnl = gotoDscned();
		chnl.clsChnl();
	}

}
