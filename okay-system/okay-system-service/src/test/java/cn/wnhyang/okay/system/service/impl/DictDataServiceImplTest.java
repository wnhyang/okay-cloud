package cn.wnhyang.okay.system.service.impl;

import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.entity.DictDataPO;
import cn.wnhyang.okay.system.service.DictDataService;
import cn.wnhyang.okay.system.vo.dictdata.DictDataPageVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class DictDataServiceImplTest {

    @Resource
    private DictDataService dictDataService;

    @Test
    void getDictDataPage() {
        DictDataPageVO reqVO = new DictDataPageVO();
        reqVO.setPageNo(1).setPageSize(10);
        PageResult<DictDataPO> dictDataPage = dictDataService.getDictDataPage(reqVO);
        log.info(dictDataPage.toString());
    }
}