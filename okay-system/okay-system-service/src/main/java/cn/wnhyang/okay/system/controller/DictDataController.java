package cn.wnhyang.okay.system.controller;

import cn.wnhyang.okay.system.service.DictDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 字典数据表
 *
 * @author wnhyang
 * @since 2023/09/13
 */
@RestController
@RequestMapping("/system/dictData")
@RequiredArgsConstructor
public class DictDataController {

    private final DictDataService dictDataService;

}
