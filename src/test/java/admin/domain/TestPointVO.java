package admin.domain;

import com.exbyte.insurance.point.domain.PointVO;

public class TestPointVO extends PointVO{

	public TestPointVO(int point, String pointName) {
		setPointNo(point);
		setPointName(pointName);
	}
}
