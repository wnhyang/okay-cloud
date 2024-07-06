package cn.wnhyang.okay.system.vo.user;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author wnhyang
 * @date 2023/8/9
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
public class UserRespVO extends UserCreateVO {

    private Long id;

    private Set<String> roles;

    private String loginIp;

    private LocalDateTime loginDate;

    private LocalDateTime createTime;
}
