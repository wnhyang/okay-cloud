package cn.wnhyang.okay.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wnhyang
 * @date 2023/11/3
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleSimpleVO {

    private Long id;

    private String name;

    private String value;
}
