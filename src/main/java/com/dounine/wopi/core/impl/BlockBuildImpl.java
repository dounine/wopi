package com.dounine.wopi.core.impl;

import com.dounine.wopi.core.BlockBuild;
import com.dounine.wopi.core.BlockInfo;
import com.dounine.wopi.core.Element;
import com.dounine.wopi.core.Storage;
import com.dounine.wopi.exception.BlockException;

/**
 * Created by lake on 17-4-21.
 */
public class BlockBuildImpl implements BlockBuild {

    private Storage storage;

    @Override
    public void create(BlockInfo blockInfo) throws BlockException {
        storage.saveBlock(blockInfo);
    }

    @Override
    public void push(String blockId,Element element) throws BlockException {
        storage.pushElement(blockId,element);
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
