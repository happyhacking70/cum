/**
 * 
 */
package jp.happyhacking70.cum.prestr.chnlLyr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudExists;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.prestr.seshLyr.TestExcp;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PresterChnlAudRjctedChnlTest extends PrestrChnlTestAbst {
	@Test
	public void TEST_audRjctedChnl_REGING() throws TestExcp,
			CumExcpIllegalChnlStatus, CumExcpAudExists {
		PrestrChnl chnl = getChnl();

		chnl.audRjctedChnl(audName);
		assertEquals(audName, chnlView.getAudRjcted());
	}

	@Test(expected = CumExcpAudExists.class)
	public void TEST_audRjctedChnl_REGING_AUDEXISTS() throws TestExcp,
			CumExcpIllegalChnlStatus, CumExcpAudExists {
		PrestrChnl chnl = getChnl();
		chnl.audJoinedChnl(audName);
		chnl.audRjctedChnl(audName);
	}

	@Test
	public void TEST_audRjctedChnl_REGED() throws TestExcp,
			CumExcpIllegalChnlStatus, CumExcpAudExists {
		PrestrChnl chnl = gotoReged();

		chnl.audRjctedChnl(audNameB);
		assertEquals(audNameB, chnlView.getAudRjcted());
	}

	@Test
	public void TEST_audRjctedChnl_CLSING() throws TestExcp,
			CumExcpIllegalChnlStatus, CumExcpAudExists {
		PrestrChnl chnl = gotoClsing();

		chnl.audRjctedChnl(audName);
		assertNull(chnlView.getAudRjcted());
	}

	@Test
	public void TEST_audRjctedChnl_CLSED() throws TestExcp,
			CumExcpIllegalChnlStatus, CumExcpAudExists {
		PrestrChnl chnl = gotoClsed();

		chnl.audRjctedChnl(audName);
		assertNull(chnlView.getAudRjcted());
	}

	@Test
	public void TEST_audRjctedChnl_DISCNED() throws CumExcpIllegalChnlStatus,
			TestExcp, CumExcpAudExists {
		PrestrChnl chnl = gotoDscned();

		chnl.audRjctedChnl(audName);
		assertNull(chnlView.getAudRjcted());
	}

}
