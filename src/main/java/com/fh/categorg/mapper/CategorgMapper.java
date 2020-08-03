package com.fh.categorg.mapper;





import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.categorg.model.Categorg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CategorgMapper extends BaseMapper<Categorg> {
    List<Map<String, Object>> queryCategorgList();

}
