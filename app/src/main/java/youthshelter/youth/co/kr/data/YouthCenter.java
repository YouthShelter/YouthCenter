package youthshelter.youth.co.kr.data;

import java.io.Serializable;
import java.util.ArrayList;

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
    private ArrayList<String> imagePath = new ArrayList<>();
    private String centerTel;
    private String centerWebSite;

    public YouthCenter(String imageName, String centerName, String centerLocation, String centerPlayTime, int like) {
        this.imageName = imageName;
        this.centerName = centerName;
        this.centerLocation = centerLocation;
        this.centerPlayTime = centerPlayTime;
        this.like = like;
    }
}
