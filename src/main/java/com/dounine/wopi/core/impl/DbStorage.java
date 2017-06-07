package com.dounine.wopi.core.impl;

import com.dounine.wopi.core.BlockInfo;
import com.dounine.wopi.core.Element;
import com.dounine.wopi.core.Storage;
import com.dounine.wopi.exception.BlockException;

import java.util.List;

/**
 * Created by lake on 17-4-21.
 */
public class DbStorage implements Storage {

    @Override
    public List<BlockInfo> list() {
        return null;
    }

    @Override
    public void saveBlock(BlockInfo blockInfo) throws BlockException {

    }

    @Override
    public void pushElement(String blockId, Element element) {

    }
}
