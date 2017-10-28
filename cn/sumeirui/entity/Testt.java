package cn.sumeirui.entity;

import cn.sumeirui.base.annotation.Column;
import cn.sumeirui.base.annotation.Id;
import cn.sumeirui.base.annotation.Table;
import lombok.*;

/**
 * Created by sumei on 17/10/22.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(value = "test")
public class Testt {
    @Id(value = "id")
    private Integer id;
    @Column(value = "age")
    private Integer age;
    @Column(value = "name")
    private String name;
}
