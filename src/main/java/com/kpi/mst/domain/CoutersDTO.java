package com.kpi.mst.domain;

public class CoutersDTO {

    private Long counterAdditional;
    private Long counterNotAdditional;


    public CoutersDTO() {
    }

    public CoutersDTO(Long counterAdditional, Long counterNotAdditional) {
        this.counterAdditional = counterAdditional;
        this.counterNotAdditional = counterNotAdditional;
    }

    public Long getCounterAdditional() {
        return counterAdditional;
    }

    public void setCounterAdditional(Long counterAdditional) {
        this.counterAdditional = counterAdditional;
    }

    public Long getCounterNotAdditional() {
        return counterNotAdditional;
    }

    public void setCounterNotAdditional(Long counterNotAdditional) {
        this.counterNotAdditional = counterNotAdditional;
    }
}
