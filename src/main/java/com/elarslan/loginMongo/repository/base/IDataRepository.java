package com.elarslan.loginMongo.repository.base;



import com.elarslan.loginMongo.model.base.DataEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface IDataRepository<T extends DataEntity, ID extends Serializable> extends MongoRepository<T, ID> {
}
