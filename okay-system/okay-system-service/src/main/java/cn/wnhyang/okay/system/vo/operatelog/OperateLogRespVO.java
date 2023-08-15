package cn.wnhyang.okay.system.vo.operatelog;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author wnhyang
 * @date 2023/8/15
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OperateLogRespVO extends OperateLogBaseVO {

    private Long id;

    private String userNickname;
}
