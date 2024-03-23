package cn.wnhyang.okay.system.vo.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wnhyang
 * @date 2023/9/6
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuSimpleTreeVO {

    private Long id;

    private String title;

    private Long parentId;

    private Integer type;

    private Integer orderNo;

    private List<MenuSimpleTreeVO> children;

}
