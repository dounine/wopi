package com.dounine.wopi.core;

import com.dounine.wopi.exception.BlockException;

import java.util.List;

/**
 * Created by lake on 17-4-21.
 */
public interface Storage {

    List<BlockInfo> list();

    void saveBlock(BlockInfo blockInfo) throws BlockException;

    void pushElement(String blockId,Element element) throws BlockException;
}
