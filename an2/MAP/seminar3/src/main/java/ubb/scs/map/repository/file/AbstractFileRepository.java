package ubb.scs.map.repository.file;

import ubb.scs.map.domain.Entity;
import ubb.scs.map.domain.Utilizator;
import ubb.scs.map.domain.validators.Validator;
import ubb.scs.map.repository.Repository;
import ubb.scs.map.repository.memory.InMemoryRepository;

import java.io.*;
import java.nio.Buffer;

public abstract class AbstractFileRepository<ID, E extends Entity<ID>> extends InMemoryRepository<ID, E>{
    private String filename;

    public AbstractFileRepository(Validator<E> validator, String fileName) {
        super(validator);
        filename=fileName;
    }

    private void loadData(){
        try(BufferedReader reader = new BufferedReader(new FileReader(this.filename))){
            String line;
            while((line = reader.readLine()) != null){
                E entity = createEntity(line);
                super.save(entity);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public abstract E createEntity(String line);
    public abstract String saveEntity(E entity);
    @Override
    public E findOne(ID id) {
        return super.findOne(id);
    }

    @Override
    public Iterable<E> findAll() {
        return super.findAll();
    }

    @Override
    public E save(E entity) {
        E e = super.save(entity);
        if (e == null)
            writeToFile();
        return e;
    }

    private void writeToFile() {
        try  ( BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            for (E entity: entities.values()) {
                String ent = saveEntity(entity);
                writer.write(ent);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public E delete(ID id) {
        E deleted = super.delete(id);
        if(deleted != null)
            writeToFile();
        return deleted;
    }

    @Override
    public E update(E entity) {
        E updated = super.update(entity);
        if (updated == null)
            writeToFile();
        return null;
    }
}
