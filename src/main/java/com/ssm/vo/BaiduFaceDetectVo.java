package com.ssm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;

@Data
public class BaiduFaceDetectVo {
    //    "quality":
//
//    {
//        "occlusion":{
//        "left_eye":0,
//                "right_eye":0,
//                "nose":0,
//                "mouth":0,
//                "left_cheek":0.0064102564938366,
//                "right_cheek":0.0057411273010075,
//                "chin":0
//    },
//        "blur":1.1886881756684e-10,
//            "illumination":141,
//            "completeness":1
//    }

    //    ++blur	否	double	人脸模糊程度，范围[0~1]，0表示清晰，1表示模糊
//++illumination	否	double	取值范围在[0~255], 表示脸部区域的光照程度 越大表示光照越好
//++completeness	否	int64	人脸完整度，0或1, 0为人脸溢出图像边界，1为人脸都在图像边界内
    @JsonProperty("face_num")
    private int faceNum;
    private Quality quality;

    @Data
    public class Quality {
        private BigDecimal blur;
        private BigDecimal illumination;
        private BigDecimal completeness;
    }

    public boolean check() {
        if (this.quality != null) {
            boolean blur = false;
            if (quality.blur != null && quality.blur.compareTo(new BigDecimal("0.1")) <= 0) {
                blur = true;
            }
            boolean illumination = false;
            if (quality.illumination != null && quality.illumination.compareTo(new BigDecimal("200")) >= 0) {
                illumination = true;
            }
            boolean completeness = false;
            if (quality.completeness != null && quality.completeness.compareTo(new BigDecimal("0.95")) >= 0) {
                completeness = true;
            }
            if (blur && illumination && completeness) {
                return true;
            }
        }
        return false;
    }
}
