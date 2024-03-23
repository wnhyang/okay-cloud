package cn.wnhyang.okay.system.vo.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wnhyang
 * @date 2023/11/7
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MenuTreeRespVO extends MenuCreateVO {

    private Long id;

    private LocalDateTime createTime;

    private List<MenuTreeRespVO> children;
}

