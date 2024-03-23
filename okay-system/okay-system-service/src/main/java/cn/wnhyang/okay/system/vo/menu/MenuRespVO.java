package cn.wnhyang.okay.system.vo.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author wnhyang
 * @date 2023/8/10
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MenuRespVO extends MenuCreateVO {

    private Long id;

    private LocalDateTime createTime;
}
