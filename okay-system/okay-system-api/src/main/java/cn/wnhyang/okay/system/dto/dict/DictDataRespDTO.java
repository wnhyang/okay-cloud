package cn.wnhyang.okay.system.dto.dict;

import lombok.Data;

/**
 * @author wnhyang
 * @date 2023/9/21
 **/
@Data
public class DictDataRespDTO {

    private String label;

    private String value;

    private String dictType;

    private Integer status;
}
