package com.trongdeptrai.soundcloud.utils;

import androidx.annotation.IntDef;

@IntDef({ LoopType.LOOP_ALL, LoopType.LOOP_ONE, LoopType.NO_LOOP })

public @interface LoopType {
    int LOOP_ALL = 0;
    int LOOP_ONE = 1;
    int NO_LOOP = 2;
}
