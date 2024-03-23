package cn.wnhyang.okay.system.vo.dicttype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author wnhyang
 * @date 2023/9/14
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DictTypeRespVO extends DictTypeCreateVO {

    private Long id;

    private Boolean standard;

    private LocalDateTime createTime;

}
