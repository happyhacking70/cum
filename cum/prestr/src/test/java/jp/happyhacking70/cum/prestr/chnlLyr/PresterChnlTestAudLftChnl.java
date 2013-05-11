/**
 * 
 */
package jp.happyhacking70.cum.prestr.chnlLyr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudExists;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.prestr.seshLyr.TestExcp;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresterChnlTestAudLftChnl extends PrestrChnlTestAbst {
	@Test
	public void TEST_audLftChnl_REGING() throws CumExcpIllegalChnlStatus,
			CumExcpAudExists, CumExcpAudNotExist {
		PrestrChnl chnl = getChnl();

		chnl.audJoinedChnl(audName);
		chnl.audLftChnl(audName);
		assertEquals(audName, chnlView.getAudLft());
	}

	@Test(expected = CumExcpAudNotExist.class)
	public void TEST_audLftChnl_REGING_NOAUD() throws CumExcpIllegalChnlStatus,
			CumExcpAudNotExist {
		PrestrChnl chnl = getChnl();

		chnl.audLftChnl(audName);
	}

	@Test
	public void TEST_audLftChnl_REGED() throws TestExcp,
			CumExcpIllegalChnlStatus, CumExcpAudNotExist {
		PrestrChnl chnl = gotoReged();
		chnl.audLftChnl(audName);
		assertEquals(audName, chnlView.getAudLft());
	}

	@Test
	public void TEST_audLftChnl_CLSING() throws TestExcp,
			CumExcpIllegalChnlStatus, CumExcpAudNotExist {
		PrestrChnl chnl = gotoClsing();
		chnl.audLftChnl(audName);
		assertNull(chnlView.getAudLft());
	}

	@Test
	public void TEST_audLftChnl_CLSED() throws TestExcp,
			CumExcpIllegalChnlStatus, CumExcpAudNotExist {
		PrestrChnl chnl = gotoClsed();
		chnl.audLftChnl(audName);
		assertNull(chnlView.getAudLft());
	}

	@Test
	public void TEST_audLftChnl_DISCNED() throws CumExcpIllegalChnlStatus,
			CumExcpAudNotExist {
		PrestrChnl chnl = gotoDscned();
		chnl.audLftChnl(audName);
		assertNull(chnlView.getAudLft());
	}

}
