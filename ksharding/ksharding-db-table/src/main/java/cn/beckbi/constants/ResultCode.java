package cn.beckbi.constants;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-31 17:45
 */
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  ResultCode {
    /**
     * result
     */
    SUCCESS("成功", 0);
    private String desc;
    private Integer code;

}
