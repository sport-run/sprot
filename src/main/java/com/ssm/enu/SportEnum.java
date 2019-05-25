package com.ssm.enu;

public enum SportEnum {
    ;
    public enum BaiduImageType{
        BASE64("BASE64"),
        FACE_TOKEN("FACE_TOKEN"),
        URL("URL");
        private String type;

        BaiduImageType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
