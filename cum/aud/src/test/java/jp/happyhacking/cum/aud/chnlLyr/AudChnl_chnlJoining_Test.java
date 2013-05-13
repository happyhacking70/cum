/**
 * 
 */
package jp.happyhacking.cum.aud.chnlLyr;

import static org.junit.Assert.assertEquals;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class AudChnl_chnlJoining_Test extends AudChnlTestAbst {

	@Test
	public void test_init() throws CumExcpIllegalChnlStatus {
		AudChnl chnl = getChnl();
		chnl.chnlJoining();
		assertEquals(AudChnl.Status.joining, chnl.chnlStatus);
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_joining() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_joining();
		chnl.chnlJoining();

	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_joined() throws TestExcp, CumExcpIllegalChnlStatus {
		// TODO Auto-generated method stub
		AudChnl chnl = goto_joined();
		chnl.chnlJoining();
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_rjcting() throws TestExcp, CumExcpIllegalChnlStatus {
		// TODO Auto-generated method stub
		AudChnl chnl = goto_rjcting();
		chnl.chnlJoining();
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_lving() throws TestExcp, CumExcpIllegalChnlStatus {
		// TODO Auto-generated method stub
		AudChnl chnl = goto_lving();
		chnl.chnlJoining();

	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_clsed() throws TestExcp, CumExcpIllegalChnlStatus {
		// TODO Auto-generated method stub
		AudChnl chnl = goto_clsed();
		chnl.chnlJoining();

	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_dscned() throws TestExcp, CumExcpIllegalChnlStatus {
		// TODO Auto-generated method stub
		AudChnl chnl = goto_dscned();
		chnl.chnlJoining();
	}

}
