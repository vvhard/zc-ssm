package zc.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zc.commons.pojo.TTag;
import zc.manager.dao.TTagMapper;
import zc.manager.service.TagService;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TTagMapper tagMapper;
    @Override
    public List<TTag> getAllTags() {
        return tagMapper.selectAll();
    }

    @Override
    public TTag getTagDetailById(int id) {
        return tagMapper.selectByPrimaryKey(id);
    }
}
