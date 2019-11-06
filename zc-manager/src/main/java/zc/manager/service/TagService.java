package zc.manager.service;

import zc.commons.pojo.TTag;

import java.util.List;

public interface TagService {
    List<TTag> getAllTags();

    TTag getTagDetailById(int id);
}
