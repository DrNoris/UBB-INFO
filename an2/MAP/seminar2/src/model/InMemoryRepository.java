package model;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository<ID, E extends Entity<ID>> implements Repository<ID, E>{
    private final Map<ID, E> map;

    public InMemoryRepository(){
        map = new HashMap<ID, E>();
    }

    @Override
    public E findOne(ID id) {
        return null;
    }

    @Override
    public Iterable<E> findAll() {
        return map.values();
    }

    @Override
    public E save (E entity){
        if (entity == null)
            throw new IllegalArgumentException("Entity connot be null");

        if (map.get(entity.getId()) != null)
            return entity;
        else {
            map.put(entity.getId(), entity);
            return null;
        }
    }
}
