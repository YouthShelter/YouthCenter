package youthshelter.youth.co.kr.data;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class YouthCenter implements Serializable {
    private String imageName;
    private String centerName;
    private String centerLocation;
    private String centerPlayTime;
    private int like;
}
