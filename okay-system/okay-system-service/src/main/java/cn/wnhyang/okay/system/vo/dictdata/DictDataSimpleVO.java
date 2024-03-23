package cn.wnhyang.okay.system.vo.dictdata;

import lombok.Data;

/**
 * @author wnhyang
 * @date 2023/9/15
 **/
@Data
public class DictDataSimpleVO {

    private Long id;

    private String dictType;

    private String color;

    private String value;

    private String label;
}
