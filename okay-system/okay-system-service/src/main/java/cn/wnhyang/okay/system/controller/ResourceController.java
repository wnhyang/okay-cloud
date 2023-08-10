package cn.wnhyang.okay.system.controller;

import cn.wnhyang.okay.system.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 资源权限表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@RestController
@RequestMapping("/system/resource")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;



}
