package first.sample.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import first.sample.dao.SampleDAO;

@Service("sampleService")
public class SampleServiceImpl implements SampleService {

	/* throws 처리 */

	@Resource(name="sampleDAO")
    private SampleDAO sampleDAO;

	@Override
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception {
		//DB를 임의의 값으로 대체
		//return sampleDAO.selectBoardList(map);
		
		
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(); 
		Map<String,Object> test = new HashMap<String,Object>();
		test.put("IDX", "1");
		test.put("TITLE", "제목");
		test.put("HIT_CNT", "10");
		test.put("CREA_DTM", "2017-11-02");
		
		list.add(test);
		
		return list;
	}


}
