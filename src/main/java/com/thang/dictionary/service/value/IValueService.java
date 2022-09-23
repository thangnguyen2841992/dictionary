package com.thang.dictionary.service.value;

import com.thang.dictionary.model.entity.Value;
import com.thang.dictionary.service.IGeneralService;

import java.util.List;

public interface IValueService extends IGeneralService<Value> {
    List<String> findValuesByKey_Id(Long keyId);

    List<Value> findValuesByTranslationContaining(String translation);


}
