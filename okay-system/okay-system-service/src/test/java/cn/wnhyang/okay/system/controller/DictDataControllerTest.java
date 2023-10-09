package cn.wnhyang.okay.system.controller;

import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.vo.dictdata.DictDataPageReqVO;
import cn.wnhyang.okay.system.vo.dictdata.DictDataRespVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class DictDataControllerTest {

    @Resource
    private DictDataController dictDataController;

    @Test
    void getDictDataPage() {
        DictDataPageReqVO reqVO = new DictDataPageReqVO();
        reqVO.setPageNo(1).setPageSize(10);
        CommonResult<PageResult<DictDataRespVO>> dictDataPage = dictDataController.getDictDataPage(reqVO);
        log.info(dictDataPage.toString());

    }
}