package cn.wnhyang.okay.system.controller;

import cn.wnhyang.okay.system.service.DictTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 字典类型表
 *
 * @author wnhyang
 * @since 2023/09/13
 */
@RestController
@RequestMapping("/system/dictType")
@RequiredArgsConstructor
public class DictTypeController {

    private final DictTypeService dictTypeService;

}
