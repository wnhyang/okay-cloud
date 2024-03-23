package cn.wnhyang.okay.system.vo.dictdata;

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
public class DictDataRespVO extends DictDataCreateVO {

    private Long id;

    private LocalDateTime createTime;
}
