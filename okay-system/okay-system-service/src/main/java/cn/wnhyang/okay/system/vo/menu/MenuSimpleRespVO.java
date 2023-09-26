package cn.wnhyang.okay.system.vo.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wnhyang
 * @date 2023/9/6
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuSimpleRespVO {

    private Long id;

    private String title;

    private Long parentId;

    private Integer type;

}
